package org.inr.supermarket.dao;

import org.inr.supermarket.database.Database;
import org.inr.supermarket.models.Purchase;
import org.inr.supermarket.models.Invoice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InvoicesDao {

    private final Connection connection = Database.getConnection();

    public Invoice addInvoice(Invoice invoice) throws SQLException {

        invoice.setDate(new Date());

        String invoiceQuery = "insert into invoices values(?,?,?,?)";

        PreparedStatement invoiceStmt = connection.prepareStatement(invoiceQuery);
        invoiceStmt.setInt(1, invoice.getId());
        invoiceStmt.setFloat(2, invoice.getCustomerId());
        invoiceStmt.setDate(3, new java.sql.Date(invoice.getDate().getTime()));
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

        String invoiceQuery = "select * from invoices where id=?";
        PreparedStatement invoiceStmt = connection.prepareStatement(invoiceQuery);
        invoiceStmt.setInt(1, id);
        ResultSet invoiceRs = invoiceStmt.executeQuery();

        if (invoiceRs.next()) {
            Invoice invoice = createInvoice(invoiceRs);
            String purchaseQuery = "select * from purchases where invoiceId=?";
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
            String purchaseQuery = "select * from purchases where invoiceId=?";
            PreparedStatement purchaseStmt = connection.prepareStatement(purchaseQuery);
            purchaseStmt.setInt(1, invoice.getId());
            ResultSet purchaseRs = purchaseStmt.executeQuery();
            invoice.setPurchases(createPurchaseList(purchaseRs));
            invoiceList.add(invoice);
        }
        return invoiceList;
    }

    public Invoice editInvoice(int id, Invoice invoice) throws SQLException {
        String invoiceQuery = "update invoices set customerId=?,date=?,totalAmount=?";
        PreparedStatement invoiceStmt = connection.prepareStatement(invoiceQuery);
        invoiceStmt.setInt(1, invoice.getId());
        invoiceStmt.setFloat(2, invoice.getCustomerId());
        invoiceStmt.setDate(3, new java.sql.Date(invoice.getDate().getTime()));
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
        String invoiceQuery = "delete from invoices where id=?";
        String purchaseQuery = "delete from purchases where invoiceId=?";
        deleteById(purchaseQuery,id);
        return deleteById(invoiceQuery,id);
    }

    public int deleteById(String query,int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        return statement.executeUpdate();
    }

    private Invoice createInvoice(ResultSet resultSet) throws SQLException {
        return new Invoice(resultSet.getInt("id"), resultSet.getInt("customerId"), resultSet.getDate("date"), resultSet.getFloat("totalAmount"));
    }

    private List<Purchase> createPurchaseList(ResultSet resultSet) throws SQLException {
        List<Purchase> purchaseList = new ArrayList<>();
        while (resultSet.next()) {
            purchaseList.add(new Purchase(resultSet.getInt("itemId"), resultSet.getFloat("quantity"), resultSet.getFloat("amount")));
        }
        return purchaseList;
    }
}
