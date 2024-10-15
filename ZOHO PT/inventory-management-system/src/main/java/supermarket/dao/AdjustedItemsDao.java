package supermarket.dao;

import supermarket.database.Database;
import supermarket.dto.BatchDto;
import supermarket.dto.ExpiredBatch;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdjustedItemsDao {

    private final Connection connection = Database.getConnection();

    public List<ExpiredBatch> getAllAdjustedItems() throws SQLException{
        String query="SELECT \n" +
                "    ag.batchId,\n" +
                "    ag.itemId,\n" +
                "    i.itemName,\n" +
                "    ag.quantity,\n" +
                "    b.expiryDate,\n" +
                "    b.warehouseId,\n" +
                "    w.warehouseName,\n" +
                "    ag.reason\n" +
                "FROM \n" +
                "    adjustedgoods AS ag\n" +
                "JOIN \n" +
                "    batches AS b ON ag.batchId = b.batchId\n" +
                "JOIN \n" +
                "    items AS i ON ag.itemId = i.itemId\n" +
                "JOIN \n" +
                "    warehouses AS w ON b.warehouseId = w.warehouseId\n" +
                "ORDER BY \n" +
                "    ag.batchId";
        PreparedStatement stmt=connection.prepareStatement(query);
        ResultSet rs=stmt.executeQuery();

        List<ExpiredBatch> expiredBatches=new ArrayList<>();
        while(rs.next()){
            expiredBatches.add(new ExpiredBatch(rs));
        }
        return expiredBatches;
    }

    public List<BatchDto> reviewExpiredBatches() throws SQLException {
        String query="SELECT \n" +
                "    b.batchId,\n" +
                "    b.expiryDate,\n" +
                "    b.itemId,\n" +
                "    i.itemName,\n" +
                "    b.quantity,\n" +
                "    b.warehouseId\n" +
                "FROM \n" +
                "    batches AS b\n" +
                "JOIN \n" +
                "    items AS i \n" +
                "    ON b.itemId = i.itemId\n" +
                "WHERE \n" +
                "    b.expiryDate < CURDATE() and b.quantity>0\n";
        PreparedStatement stmt=connection.prepareStatement(query);
        ResultSet rs=stmt.executeQuery();
        List<BatchDto> expiredBatches=new ArrayList<>();
        while(rs.next()){
            expiredBatches.add(new BatchDto(rs));
        }
        return expiredBatches;
    }

    public void adjust(ExpiredBatch batch) throws SQLException{
        connection.setAutoCommit(false);
        try {
            if(addToAdjustments(batch)==0){
                throw new SQLException("Error adding to adjustments");
            }
            if(adjustBatches(batch)==0){
                throw new SQLException("Error adjusting the batch");
            }
        } catch (Exception e) {
            throw new SQLException(e.toString());
        } finally{
            connection.commit();
            connection.setAutoCommit(true);
        }
    }

    private int addToAdjustments(ExpiredBatch batch) throws SQLException {
        String query="insert into adjustedgoods (batchId,itemId,quantity,dateRemoved,reason) values(?,?,?,?,?)";
        PreparedStatement stmt=connection.prepareStatement(query);
        stmt.setInt(1,batch.getBatchId());
        stmt.setInt(2,batch.getItemId());
        stmt.setFloat(3,batch.getQuantity());
        stmt.setDate(4, Date.valueOf(LocalDate.now()));
        stmt.setString(5,batch.getReason());
        return stmt.executeUpdate();
    }

    private int adjustBatches(ExpiredBatch batch) throws SQLException{
        String query="Update batches set quantity=quantity-? where batchId=?";
        PreparedStatement stmt=connection.prepareStatement(query);
        stmt.setFloat(1,batch.getQuantity());
        stmt.setInt(2,batch.getBatchId());
        return stmt.executeUpdate();
    }

}
