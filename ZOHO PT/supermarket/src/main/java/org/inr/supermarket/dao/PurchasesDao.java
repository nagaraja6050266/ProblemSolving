package org.inr.supermarket.dao;

import org.inr.supermarket.database.Database;
import org.inr.supermarket.models.Purchase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PurchasesDao {

    ItemsDao itemsDao = new ItemsDao();
    private final Connection connection = Database.getConnection();

    public Purchase addPurchase(Purchase purchase) throws SQLException {
        String query = "insert into purchases values(?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, purchase.getInvoiceId());
        statement.setInt(2, purchase.getItemId());
        statement.setFloat(3, purchase.getQuantity());
        statement.setFloat(4, purchase.getAmount());
        statement.setInt(5, purchase.getPurchaseId());
        statement.executeUpdate();

        return purchase;
    }

    public Purchase getPurchaseById(int invoiceId, int purchaseId) throws SQLException {
        String query = "select * from purchases where purchaseId=? and invoiceId=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, purchaseId);
        statement.setInt(2, invoiceId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return createPurchase(resultSet);
        }
        return null;
    }

    public List<Purchase> getAllPurchases(int invoiceId) throws SQLException {
        String query = "select * from purchases where invoiceId=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, invoiceId);
        ResultSet resultSet = statement.executeQuery();
        List<Purchase> purchases = new ArrayList<>();
        while (resultSet.next()) {
            purchases.add(createPurchase(resultSet));
        }
        return purchases;
    }

    public int editPurchase(int invoiceId, Purchase purchase) throws SQLException {
        String query = "update purchases set invoiceId=?,itemId=?,quantity=?,amount=?,purchaseId=? where purchaseId=? and invoiceId=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, purchase.getInvoiceId());
        statement.setInt(2, purchase.getItemId());
        statement.setFloat(3, purchase.getQuantity());
        statement.setFloat(4, purchase.getAmount());
        statement.setInt(5, purchase.getPurchaseId());
        statement.setInt(6, purchase.getPurchaseId());
        statement.setInt(7, invoiceId);
        return statement.executeUpdate();
    }

    public int deletePurchase(int invoiceId, int purchaseId) throws SQLException {
        String query = "delete from purchases where invoiceId=? and purchaseId=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, invoiceId);
        statement.setInt(2, purchaseId);
        return statement.executeUpdate();
    }

    public List<Integer> getPurchaseIds(int invoiceId) throws SQLException{
        String query = "select purchaseId from purchases where invoiceId=?";
        PreparedStatement stmt=connection.prepareStatement(query);
        stmt.setInt(1,invoiceId);
        ResultSet result=stmt.executeQuery();
        List<Integer> purchaseIdList=new ArrayList<>();
        while(result.next()){
            purchaseIdList.add(result.getInt("purchaseId"));
        }
        return purchaseIdList;
    }

    private Purchase createPurchase(ResultSet resultSet) throws SQLException {
        return new Purchase(resultSet.getInt("invoiceId"), resultSet.getInt("itemId"), resultSet.getFloat("quantity"), resultSet.getFloat("amount"), resultSet.getInt("purchaseId"));
    }

}
