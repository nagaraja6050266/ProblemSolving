package supermarket.dao;

import supermarket.database.Database;
import supermarket.models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InvoicesDao {

    private final Connection connection = Database.getConnection();
    private ItemsDao itemsDao = DaoDistributor.getItemsDao();
    private PurchasesDao purchasesDao = DaoDistributor.getPurchasesDao();
    private PaymentsDao paymentsDao = DaoDistributor.getPaymentsDao();

    public Invoice addInvoice(Invoice invoice) throws SQLException, RuntimeException {

        invoice.setDate(new Date());
        invoice.setInvoiceStatus(InvoiceStatus.OPEN);

        String invoiceQuery = "insert into invoices values(?,?,?,?,?)";

        PreparedStatement invoiceStmt = connection.prepareStatement(invoiceQuery);
        invoiceStmt.setInt(1, invoice.getId());
        invoiceStmt.setFloat(2, invoice.getCustomerId());
        invoiceStmt.setDate(3, new java.sql.Date(invoice.getDate().getTime()));

        int purchaseId = 1;
        List<Purchase> currPurchases = invoice.getPurchases();
        for (Purchase purchase : currPurchases) {
            Item currentItem = itemsDao.getItemById(purchase.getItemId());
            balanceItems(currentItem, purchase.getQuantity());

            purchase.setAmount(calculatePurchaseAmount(purchase, currentItem));
            purchase.setInvoiceId(invoice.getId());
            purchase.setPurchaseId(purchaseId++);

            invoice.setTotalAmount(invoice.getTotalAmount() + purchase.getAmount());
        }
        invoice.setPurchases(currPurchases);
        invoiceStmt.setFloat(4, invoice.getTotalAmount());
        invoiceStmt.setString(5, String.valueOf(invoice.getInvoiceStatus()));
        invoiceStmt.executeUpdate();

        for (Purchase purchase : currPurchases) {
            try {
                purchasesDao.addPurchase(purchase);
            } catch (SQLException e) {
                deleteInvoice(invoice.getId());
            }
        }
        return invoice;
    }

    public Invoice getInvoiceById(int id) throws SQLException {

        String invoiceQuery = "select * from invoices where id=?";
        PreparedStatement invoiceStmt = connection.prepareStatement(invoiceQuery);
        invoiceStmt.setInt(1, id);
        ResultSet invoiceRs = invoiceStmt.executeQuery();

        if (invoiceRs.next()) {
            Invoice invoice = createInvoice(invoiceRs);
            invoice.setPurchases(purchasesDao.getAllPurchases(invoiceRs.getInt("id")));
            return invoice;
        } else {
            return null;
        }
    }

    public List<Invoice> getAllInvoices() throws SQLException {
        String invoiceQuery = "select * from invoices";
        PreparedStatement invoiceStmt = connection.prepareStatement(invoiceQuery);
        return getInvoices(invoiceStmt);
    }

    public List<Invoice> getAllInvoices(int customerId) throws SQLException {
        String invoiceQuery = "select * from invoices where customerId=?";
        PreparedStatement invoiceStmt = connection.prepareStatement(invoiceQuery);
        invoiceStmt.setInt(1, customerId);
        return getInvoices(invoiceStmt);
    }

    public List<Invoice> getInvoices(PreparedStatement invoiceStmt) throws SQLException {

        ResultSet invoiceRs = invoiceStmt.executeQuery();

        List<Invoice> invoiceList = new ArrayList<>();
        while (invoiceRs.next()) {
            Invoice invoice = createInvoice(invoiceRs);
            invoice.setPurchases(purchasesDao.getAllPurchases(invoice.getId()));
            invoiceList.add(invoice);
        }
        return invoiceList;
    }

    public Invoice editInvoice(int id, Invoice invoice) throws SQLException {

        invoice.setTotalAmount(editPurchases(id, invoice.getPurchases()));
        String invoiceQuery = "update invoices set id=?,customerId=?,date=?,totalAmount=? where id=?";
        PreparedStatement invoiceStmt = connection.prepareStatement(invoiceQuery);
        invoiceStmt.setInt(1, invoice.getId());
        invoiceStmt.setFloat(2, invoice.getCustomerId());
        invoiceStmt.setDate(3, new java.sql.Date(invoice.getDate().getTime()));
        invoiceStmt.setFloat(4, invoice.getTotalAmount());
        invoiceStmt.setInt(5, id);
        invoiceStmt.executeUpdate();

        return getInvoiceById(id);
    }

    public void updatePaymentStatus(int id, InvoiceStatus invoiceStatus) throws SQLException {
        String query = "update invoices set invoiceStatus=? where id=?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, String.valueOf(invoiceStatus));
        stmt.setInt(2, id);
        stmt.executeUpdate();
    }

    public int deleteInvoice(int id) throws SQLException {
        String query = "delete from invoices where id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        return statement.executeUpdate();
    }

    public Invoice recordPayment(int id, Payment payment) throws SQLException {
        payment.setInvoiceId(id);
        if (paymentsDao.addPayment(payment) == 1) {
            updateInvoiceStatus(payment);
            Invoice currInvoice = getInvoiceById(id);
            return currInvoice;
        }
        return null;
    }

    private float editPurchases(int id, List<Purchase> purchases) throws SQLException {
        List<Integer> purchaseIdList = purchasesDao.getPurchaseIds(id);
        float totalAmount = 0;
        for (Purchase purchase : purchases) {
            purchase.setAmount(calculatePurchaseAmount(purchase, itemsDao.getItemById(purchase.getItemId())));
            purchase.setInvoiceId(id);
            int currPurchaseId = purchase.getPurchaseId();
            if (purchaseIdList.contains(currPurchaseId)) {
                purchasesDao.editPurchase(id, purchase);
            } else {
                purchasesDao.addPurchase(purchase);
            }
            purchaseIdList.remove(Integer.valueOf(currPurchaseId));
            totalAmount += purchase.getAmount();
        }

        for (int remainingPurchaseId : purchaseIdList) {
            purchasesDao.deletePurchase(id, remainingPurchaseId);
        }

        return totalAmount;
    }


    private Invoice createInvoice(ResultSet resultSet) throws SQLException {
        return new Invoice(resultSet.getInt("id"), resultSet.getInt("customerId"), resultSet.getDate("date"), resultSet.getFloat("totalAmount"), InvoiceStatus.valueOf(resultSet.getString("invoiceStatus")));
    }

    private float calculatePurchaseAmount(Purchase purchase, Item currentItem) throws SQLException {
        float quantity = purchase.getQuantity();
        return currentItem.getPrice() * quantity;
    }

    private void balanceItems(Item currentItem, float quantity) throws SQLException {
        if (currentItem == null) {
            throw new RuntimeException("Item with ID " + currentItem.getId() + " not found.");
        }
        if (currentItem.getQuantity() < quantity) {
            throw new RuntimeException("Quantity Exceeds the availability");
        }
        currentItem.setQuantity(currentItem.getQuantity() - quantity);
        itemsDao.editItem(currentItem.getId(), currentItem);
    }

    private void updateInvoiceStatus(Payment payment) throws SQLException {
        Invoice currInvoice=getInvoiceById(payment.getInvoiceId());
        InvoiceStatus invoiceStatus;
        if(payment.getAmount()<currInvoice.getTotalAmount()){
            invoiceStatus=InvoiceStatus.PARTIALLY_PAID;
        } else if(payment.getAmount()>currInvoice.getTotalAmount()) {
            invoiceStatus=InvoiceStatus.PAID_EXCESS_AMOUNT;
        } else {
            invoiceStatus=InvoiceStatus.PAID;
        }
        updatePaymentStatus(payment.getInvoiceId(), invoiceStatus);
    }
}
