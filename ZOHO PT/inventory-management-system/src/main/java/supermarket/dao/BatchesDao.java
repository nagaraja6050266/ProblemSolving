package supermarket.dao;

import supermarket.database.Database;
import supermarket.dto.BatchDto;
import supermarket.dto.ExpiredBatch;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BatchesDao {

    Connection connection= Database.getConnection();

    public BatchesDao() throws SQLException {
    }

    public int updateExistingBatch(int warehouseId, int itemId, Date expiryDate,float quantity) throws SQLException {
        try{
            String query="UPDATE batches set quantity=quantity+? where warehouseId=? and itemId=? and expiryDate=?";
            PreparedStatement stmt=connection.prepareStatement(query);
            stmt.setFloat(1,quantity);
            stmt.setInt(2,warehouseId);
            stmt.setInt(3,itemId);
            stmt.setDate(4,expiryDate);
            System.out.println("done");
            int result =  stmt.executeUpdate();
            System.out.println(result);
        return result;
        } catch (Exception e){
            System.out.println(e.toString());
            return 0;
        }
    }

    public void getItemsFromBatch(int itemId, float neededQuantity) throws SQLException {
        String selectBatchQuery = "SELECT batchId, quantity FROM batches WHERE itemId = ? AND expiryDate >= CURDATE() AND quantity > 0 ORDER BY expiryDate ASC LIMIT 1";
        String updateBatchQuery = "UPDATE batches SET quantity = quantity - ? WHERE batchId = ?";

        while (neededQuantity > 0) {
            int batchId = 0;
            float availableQuantity = 0;

            // Fetch the batch with the most recent expiry date
            try (PreparedStatement selectStmt = connection.prepareStatement(selectBatchQuery)) {
                selectStmt.setInt(1, itemId);
                ResultSet rs = selectStmt.executeQuery();

                if (rs.next()) {
                    batchId = rs.getInt("batchId");
                    availableQuantity = rs.getFloat("quantity");
                } else {
                    throw new SQLException("Items out of stock for itemId: " + itemId);
                }
            }

            // Calculate how much to decrease
            float decreaseQuantity = Math.min(neededQuantity, availableQuantity);

            // Update the batch quantity
            try (PreparedStatement updateStmt = connection.prepareStatement(updateBatchQuery)) {
                updateStmt.setFloat(1, decreaseQuantity);
                updateStmt.setInt(2, batchId);
                updateStmt.executeUpdate();
            }

            // Decrease the needed quantity
            neededQuantity -= decreaseQuantity;

            // Log if the batch is depleted
            if (availableQuantity <= decreaseQuantity) {
                System.out.println("Batch " + batchId + " depleted.");
            }
        }
    }

    public int createBatch(int warehouseId,int itemId,float quantity,Date expiryDate) throws SQLException {
        String query = "insert into batches (warehouseId,itemId,quantity,expiryDate) values(?,?,?,?)";
        PreparedStatement stmt=connection.prepareStatement(query);
        stmt.setInt(1,warehouseId);
        stmt.setInt(2,itemId);
        stmt.setFloat(3,quantity);
        stmt.setDate(4,expiryDate);
        return stmt.executeUpdate();
    }

}
