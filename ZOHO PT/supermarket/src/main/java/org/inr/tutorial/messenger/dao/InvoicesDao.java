package org.inr.tutorial.messenger.dao;

import org.inr.tutorial.messenger.database.Database;
import org.inr.tutorial.messenger.model.Invoice;
import org.inr.tutorial.messenger.model.Item;
import org.inr.tutorial.messenger.model.Purchase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class InvoicesDao {
//    private Map<Integer, Invoice> invoices = Database.getInvoices();

    public Invoice addInvoice(Invoice invoice) throws SQLException {

        invoice.setDate(new Date());

        Connection connection = Database.getConnection();
        String invoiceQuery = "insert into invoices values(?,?,?,?)";

        PreparedStatement invoiceStmt = connection.prepareStatement(invoiceQuery);
        invoiceStmt.setInt(1, invoice.getId());
        invoiceStmt.setFloat(2, invoice.getCustomerId());
        invoiceStmt.setString(3, invoice.getDate().toString());
        invoiceStmt.setFloat(4, invoice.getTotalAmount());
        invoiceStmt.executeUpdate();

        for (Purchase purchase : invoice.getPurchases()) {
            String purchaseQuery = "insert into purchases values(?, ?, ?, ?)";
            PreparedStatement purchaseStmt = connection.prepareStatement(purchaseQuery);
            purchaseStmt.setInt(1, invoice.getId());
            purchaseStmt.setInt(2, purchase.getItemId());
            purchaseStmt.setFloat(3, purchase.getQuantity());
            purchaseStmt.setFloat(4, purchase.getAmount());
            purchaseStmt.executeUpdate();
        }

        return invoice;
    }

    public Invoice getInvoiceById(int id) throws SQLException {

        Connection connection = Database.getConnection();
        String invoiceQuery = "select * from invoices where id=?";
        PreparedStatement invoiceStmt = connection.prepareStatement(invoiceQuery);
        invoiceStmt.setInt(1, id);
        ResultSet invoiceRs = invoiceStmt.executeQuery();

        if (invoiceRs.next()) {
            Invoice invoice = createInvoice(invoiceRs);
            String purchaseQuery = "select * from purchases where invoice_id=?";
            PreparedStatement purchaseStmt = connection.prepareStatement(purchaseQuery);
            purchaseStmt.setInt(1, invoice.getId());
            ResultSet purchaseRs = purchaseStmt.executeQuery();
            invoice.setPurchases(createPurchaseList(purchaseRs));
            return invoice;
        } else {
            return null;
        }
    }

    public List<Invoice> getAllInvoices() throws SQLException {

        Connection connection = Database.getConnection();
        String invoiceQuery = "select * from invoices";
        PreparedStatement invoiceStmt = connection.prepareStatement(invoiceQuery);
        ResultSet invoiceRs = invoiceStmt.executeQuery();

        List<Invoice> invoiceList = new ArrayList<>();
        while (invoiceRs.next()) {
            Invoice invoice = createInvoice(invoiceRs);
            String purchaseQuery = "select * from purchases where invoice_id=?";
            PreparedStatement purchaseStmt = connection.prepareStatement(purchaseQuery);
            purchaseStmt.setInt(1, invoice.getId());
            ResultSet purchaseRs = purchaseStmt.executeQuery();
            invoice.setPurchases(createPurchaseList(purchaseRs));
            invoiceList.add(invoice);
        }
        return invoiceList;
    }

    public Invoice editInvoice(int id, Invoice invoice) throws SQLException {
        Connection connection = Database.getConnection();
        String invoiceQuery = "update invoices set id=?,customer_id=?,date=?,total_amount=?";
        PreparedStatement invoiceStmt = connection.prepareStatement(invoiceQuery);
        invoiceStmt.setInt(1, invoice.getId());
        invoiceStmt.setFloat(2, invoice.getCustomerId());
        invoiceStmt.setString(3, invoice.getDate().toString());
        invoiceStmt.setFloat(4, invoice.getTotalAmount());
        invoiceStmt.executeUpdate();

        for (Purchase purchase : invoice.getPurchases()) {
            String purchaseQuery = "update purchases set itemId=?,quantity=?,amount=?";
            PreparedStatement purchaseStmt = connection.prepareStatement(purchaseQuery);
            purchaseStmt.setInt(1, purchase.getItemId());
            purchaseStmt.setFloat(2, purchase.getQuantity());
            purchaseStmt.setFloat(3, purchase.getAmount());
            purchaseStmt.executeUpdate();
        }

        return invoice;
    }

    public int deleteInvoice(int id) throws SQLException {
        Connection connection = Database.getConnection();
        String query = "delete from invoices where id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        return statement.executeUpdate();
    }

    private Invoice createInvoice(ResultSet resultSet) throws SQLException {
        return new Invoice(resultSet.getInt("id"), resultSet.getInt("customerId"), java.sql.Date.valueOf(resultSet.getString("date")), resultSet.getFloat("total_amount"));
    }

    private List<Purchase> createPurchaseList(ResultSet resultSet) throws SQLException {
        List<Purchase> purchaseList = new ArrayList<>();
        while (resultSet.next()) {
            purchaseList.add(new Purchase(resultSet.getInt("item_id"), resultSet.getFloat("quantity"), resultSet.getFloat("amount")));
        }
        return purchaseList;
    }
}
