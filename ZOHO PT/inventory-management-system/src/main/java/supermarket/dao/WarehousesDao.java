package supermarket.dao;

import supermarket.database.Database;
import supermarket.dto.BatchDto;
import supermarket.dto.WarehouseDto;
import supermarket.models.Warehouse;
import supermarket.publicUtilities.Utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WarehousesDao {

    private final Connection connection = Database.getConnection();

    public Warehouse addWarehouse(Warehouse warehouse) throws SQLException {
        String query = "insert into warehouses (warehouseName,location,capacity) values(?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, warehouse.getName());
        statement.setString(2, warehouse.getLocation());
        statement.setInt(3, warehouse.getCapacity());
        statement.executeUpdate();
        return warehouse;
    }

    public Warehouse getWarehouseById(int warehouseId) throws SQLException {
        String query = "select * from warehouses where warehouseId=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, warehouseId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return Utilities.createWarehouse(resultSet);
        }
        return null;
    }

    public List<Warehouse> getAllWarehouses() throws SQLException {
        String query = "select * from warehouses";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        List<Warehouse> warehouses = new ArrayList<>();
        while (resultSet.next()) {
            warehouses.add(Utilities.createWarehouse(resultSet));
        }
        return warehouses;
    }

    public int editWarehouse(int warehouseId, Warehouse warehouse) throws SQLException {
        String query = "update warehouses set warehouseName=?,location=?,capacity=? where warehouseId=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, warehouse.getName());
        statement.setString(2, warehouse.getLocation());
        statement.setFloat(3, warehouse.getCapacity());
        statement.setInt(4, warehouseId);
        return statement.executeUpdate();
    }

    public int deleteWarehouse(int warehouseId) throws SQLException {
        String query = "delete from warehouses where warehouseId=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, warehouseId);
        return statement.executeUpdate();
    }

    public List<BatchDto> getWarehouseItems(int warehouseId) throws SQLException {
        String query = "SELECT i.itemId, i.itemName, w.warehouseId, w.warehouseName, w.location, w.capacity, b.batchId, b.quantity, b.expiryDate\n" +
                "FROM items AS i\n" +
                "JOIN batches AS b ON i.itemId = b.itemId\n" +
                "JOIN warehouses AS w ON b.warehouseId = w.warehouseId\n" +
                "WHERE w.warehouseId = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, warehouseId);
        ResultSet resultSet = statement.executeQuery();
        List<BatchDto> warehouseBatches=new ArrayList<>();
        while(resultSet.next()){
            warehouseBatches.add(new BatchDto(resultSet));
        }
        return warehouseBatches;
    }

    public List<WarehouseDto> getWarehouseItems() throws SQLException {
        String query = "SELECT i.*, w.warehouseId, w.warehouseName, w.location, w.capacity, b.batchId, b.quantity, b.expiryDate\n" +
                "FROM items AS i\n" +
                "JOIN batches AS b ON i.itemId = b.itemId\n" +
                "JOIN warehouses AS w ON b.warehouseId = w.warehouseId;\n";

        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        List<WarehouseDto> warehouses = new ArrayList<>();
        int prevId=-1;
        WarehouseDto currWarehouse=new WarehouseDto();
        while(resultSet.next()){
            if(prevId!=resultSet.getInt("warehouseId")){
                if(prevId!=-1){
                    warehouses.add(currWarehouse);
                }
                currWarehouse=new WarehouseDto(Utilities.createWarehouse(resultSet),new ArrayList<>());
            }
            currWarehouse.addBatch(new BatchDto(resultSet));
            prevId=resultSet.getInt("warehouseId");
        }
        warehouses.add(currWarehouse);
        return warehouses;
    }


}