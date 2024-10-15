package supermarket.dao;

import supermarket.database.Database;
import supermarket.models.PurchaseLineItem;
import supermarket.models.Purchase;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PurchasesDao {

    Connection connection = Database.getConnection();
    BatchesDao batchesDao = DaoDistributor.getBatchesDao();
    ItemsDao itemsDao = DaoDistributor.getItemsDao();

    public Purchase createPurchase(Purchase purchase) throws SQLException {

        connection.setAutoCommit(false);
        try {
            LocalDate localDate = LocalDate.now();
            purchase.setPurchaseDate(Date.valueOf(localDate));
            int purchaseId = insertPurchasesGetId(purchase);
            purchase.setPurchaseId(purchaseId);

            List<PurchaseLineItem> preparedLineItems = new ArrayList<>();
            float totalAmount=0;
            for (PurchaseLineItem lineItem : purchase.getPurchaseItems()) {
                // Add to purchase Items table
                PurchaseLineItem updatedLineItem = insertPurchaseItemGetItem(purchase.getPurchaseId(), lineItem);


                // If expiry date and itemId matches existing batch then add quantity
                int updatedBatches = batchesDao.updateExistingBatch(purchase.getWarehouseId(), lineItem.getItemId(), lineItem.getExpiryDate(), lineItem.getQuantity());
                if (updatedBatches == 0) {
                    // Create new batch if none found
                    batchesDao.createBatch(purchase.getWarehouseId(), lineItem.getItemId(), lineItem.getQuantity(), lineItem.getExpiryDate());
                }
                preparedLineItems.add(updatedLineItem);

                totalAmount+= lineItem.getAmount();
            }

            purchase.setPurchaseItems(preparedLineItems);
            purchase.setTotalAmount(totalAmount);

            if(updateTotal(totalAmount)==0){
                throw new SQLException("Can't Update Total");
            }

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }

        return purchase;
    }

    private PurchaseLineItem insertPurchaseItemGetItem(int purchaseId, PurchaseLineItem lineItem) throws SQLException {
        String purchaseItemsQuery = "insert into purchaseItems (purchaseId, itemId, quantity, amount) values (?, ?, ?, ?)";
        PreparedStatement piStmt = connection.prepareStatement(purchaseItemsQuery);
        piStmt.setInt(1, purchaseId);
        piStmt.setInt(2, lineItem.getItemId());
        piStmt.setFloat(3, lineItem.getQuantity());

        lineItem.setAmount(itemsDao.getItemById(lineItem.getItemId()).getCostPrice() * lineItem.getQuantity());
        piStmt.setFloat(4, lineItem.getAmount());

        piStmt.executeUpdate();
        System.out.println("check");
        return lineItem;
    }

    private int insertPurchasesGetId(Purchase purchase) throws SQLException {
        String purchaseQuery = "insert into purchases (supplierId, date, totalAmount) values (?, ?, ?)";
        PreparedStatement purchaseStmt = connection.prepareStatement(purchaseQuery, Statement.RETURN_GENERATED_KEYS);
        purchaseStmt.setInt(1, purchase.getSupplierId());
        purchaseStmt.setDate(2, purchase.getPurchaseDate());
        purchaseStmt.setFloat(3, purchase.getTotalAmount());
        System.out.println(purchaseStmt.executeUpdate());

        ResultSet rs = purchaseStmt.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        } else {
            throw new SQLException("Failed to retrieve purchase ID");
        }
    }

    private int updateTotal(float totalAmount) throws SQLException{
        String query="update purchases set totalAmount=?";
        PreparedStatement stmt=connection.prepareStatement(query);
        stmt.setFloat(1,totalAmount);
        return stmt.executeUpdate();
    }
}

