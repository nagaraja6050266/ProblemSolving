package supermarket.dao;

import jakarta.ws.rs.PUT;
import supermarket.database.Database;
import supermarket.dto.BatchDto;
import supermarket.dto.PurchaseDto;
import supermarket.models.Item;
import supermarket.models.PurchaseLineItem;
import supermarket.models.Purchase;
import supermarket.publicUtilities.Utilities;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PurchasesDao {

    Connection connection = Database.getConnection();
    BatchesDao batchesDao = DaoDistributor.getBatchesDao();
    ItemsDao itemsDao = DaoDistributor.getItemsDao();

    public PurchasesDao() throws SQLException {
    }

    public Purchase createPurchase(Purchase purchase) throws SQLException {

        connection.setAutoCommit(false);
        try {
            LocalDate localDate = LocalDate.now();
            purchase.setPurchaseDate(Date.valueOf(localDate));
            int purchaseId = insertPurchasesGetId(purchase);
            purchase.setPurchaseId(purchaseId);

            List<PurchaseLineItem> preparedLineItems = new ArrayList<>();
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
            }

            purchase.setPurchaseItems(preparedLineItems);

            if(updateTotal(purchase.calculateTotalAmount()) == 0) {
                throw new SQLException("Can't Update Total");
            }

            connection.commit();  // Commit transaction here
        } catch (SQLException e) {
            System.out.println(e.toString());
            connection.rollback();  // Rollback in case of error
            throw e;
        } finally {
            try {
                connection.setAutoCommit(true);  // Ensure auto-commit is enabled afterward
            } catch (SQLException e) {
                System.out.println("Error resetting auto-commit: " + e.toString());
            }
        }

        return purchase;
    }


    public List<PurchaseDto> getAllPurchases() throws SQLException{
        List<PurchaseDto> purchases =new ArrayList<>();
        String query="SELECT \n" +
                "    s.purchaseId,\n" +
                "    s.date,\n" +
                "    s.supplierId,\n" +
                "    c.supplierName,\n" +
                "    s.totalAmount,\n" +
                "    si.itemId,\n" +
                "    si.expiryDate,\n" +
                "    si.quantity,\n" +
                "    si.amount,\n" +
                "    s.warehouseId\n" +
                "FROM \n" +
                "    purchases AS s \n" +
                "JOIN \n" +
                "    purchaseItems AS si \n" +
                "    ON si.purchaseId = s.purchaseId \n" +
                "JOIN \n" +
                "    suppliers AS c \n" +
                "    ON c.supplierId = s.supplierId \n" +
                "ORDER BY \n" +
                "    s.purchaseId ASC;\n";
        PreparedStatement stmt= connection.prepareStatement(query);
        ResultSet rs=stmt.executeQuery();
        int prevId=-1;
        PurchaseDto currPurchase =new PurchaseDto();

        while(rs.next()){
            if(prevId!=rs.getInt("purchaseId")){
                if(prevId!=-1){
                    purchases.add(currPurchase);
                }
                currPurchase =new PurchaseDto(rs);
            }
            currPurchase.addItem(new PurchaseLineItem(Utilities.createLineItem(rs),rs.getDate("expiryDate")));
            prevId=rs.getInt("purchaseId");
        }

        purchases.add(currPurchase);
        return purchases;
    }

    private PurchaseLineItem insertPurchaseItemGetItem(int purchaseId, PurchaseLineItem lineItem) throws SQLException {
        String purchaseItemsQuery = "insert into purchaseItems (purchaseId, itemId, quantity, amount,expiryDate) values (?, ?, ?, ?,?)";
        PreparedStatement piStmt = connection.prepareStatement(purchaseItemsQuery);
        piStmt.setInt(1, purchaseId);
        piStmt.setInt(2, lineItem.getItemId());
        piStmt.setFloat(3, lineItem.getQuantity());

        lineItem.setAmount(itemsDao.getItemById(lineItem.getItemId()).getCostPrice() * lineItem.getQuantity());
        piStmt.setFloat(4, lineItem.getAmount());
        piStmt.setDate(5,lineItem.getExpiryDate());

        piStmt.executeUpdate();
        return lineItem;
    }

    private int insertPurchasesGetId(Purchase purchase) throws SQLException {
        String purchaseQuery = "insert into purchases (supplierId, date, totalAmount,warehouseId) values (?, ?, ?,?)";
        PreparedStatement purchaseStmt = connection.prepareStatement(purchaseQuery, Statement.RETURN_GENERATED_KEYS);
        purchaseStmt.setInt(1, purchase.getSupplierId());
        purchaseStmt.setDate(2, purchase.getPurchaseDate());
        purchaseStmt.setFloat(3, purchase.getTotalAmount());
        purchaseStmt.setInt(4,purchase.getWarehouseId());
        purchaseStmt.executeUpdate();

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

