package supermarket.dao;

import supermarket.database.Database;
import supermarket.models.Warehouse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WarehousesDao {

    private final Connection connection = Database.getConnection();

    public Warehouse addWarehouse(Warehouse warehouse) throws SQLException {
        String query = "insert into warehouses (name,location,capacity) values(?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1,warehouse.getName());
        statement.setString(2,warehouse.getLocation());
        statement.setInt(3,warehouse.getCapacity());
        statement.executeUpdate();
        return warehouse;
    }

    public Warehouse getWarehouseById(int warehouseId) throws SQLException {
        String query = "select * from warehouses where warehouseId=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, warehouseId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return createWarehouse(resultSet);
        }
        return null;
    }

    public List<Warehouse> getAllWarehouses() throws SQLException {
        String query = "select * from warehouses";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        List<Warehouse> warehouses = new ArrayList<>();
        while (resultSet.next()) {
            warehouses.add(createWarehouse(resultSet));
        }
        return warehouses;
    }

    public int editWarehouse(int warehouseId, Warehouse warehouse) throws SQLException {
        String query = "update warehouses set name=?,location=?,capacity=? where warehouseId=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, warehouse.getName());
        statement.setInt(2, warehouseId);
        return statement.executeUpdate();
    }

    public int deleteWarehouse(int warehouseId) throws SQLException {
        String query = "delete from warehouses where warehouseId=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, warehouseId);
        return statement.executeUpdate();
    }

    private Warehouse createWarehouse(ResultSet resultSet) throws SQLException {
        return new Warehouse(resultSet.getInt("warehouseId"), resultSet.getString("name"),resultSet.getString("location"),resultSet.getInt("capacity"));
    }

}