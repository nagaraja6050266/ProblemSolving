package supermarket.dao;

import supermarket.database.Database;

import java.sql.*;

public class BatchesDao {

    Connection connection= Database.getConnection();

    public int updateExistingBatch(int warehouseId, int itemId, Date expiryDate,float quantity) throws SQLException {
        String query="UPDATE batches set quantity=quantity+? where warehouseId=? and itemId=? and expiryDate=?";
        PreparedStatement stmt=connection.prepareStatement(query);
        stmt.setInt(2,warehouseId);
        stmt.setInt(3,itemId);
        stmt.setDate(4,expiryDate);
        stmt.setFloat(1,quantity);
        return stmt.executeUpdate();
    }

    public void getItemsFromBatch(int itemId,float neededQuantity) throws SQLException{
        String selectBatchQuery = "SELECT batchId, quantity FROM batches WHERE itemId = ? AND expiryDate >= CURDATE() ORDER BY expiryDate ASC LIMIT 1";
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

            // If the entire batch is depleted, you may want to log or handle it here
            if (availableQuantity <= decreaseQuantity) {
                throw new SQLException("Items less in stock");
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
