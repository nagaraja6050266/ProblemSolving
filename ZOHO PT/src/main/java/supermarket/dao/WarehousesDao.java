package supermarket.dao;

import supermarket.database.Database;
import supermarket.dto.ItemDto;
import supermarket.dto.WarehouseDto;
import supermarket.models.Item;
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

    public List<Item> getWarehouseItems(int warehouseId) throws SQLException {
        String query = "select i.itemId, i.itemName,wi.quantity,i.categoryId,i.costPrice,i.sellingPrice from warehouseitems as wi join items as i on wi.itemId=i.itemId where warehouseId=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, warehouseId);
        ResultSet resultSet = statement.executeQuery();
        List<Item> warehouseItems=new ArrayList<>();
        while(resultSet.next()){
            warehouseItems.add(Utilities.createItem(resultSet));
        }
        return warehouseItems;
    }

    public List<WarehouseDto> getWarehouseItems() throws SQLException {
        String query = "SELECT * FROM warehouses AS w " +
                "JOIN (SELECT wi.warehouseId, i.itemId, wi.quantity, i.itemName, i.categoryId, i.costPrice, i.sellingPrice " +
                "FROM warehouseitems AS wi " +
                "JOIN items AS i ON wi.itemId = i.itemId) AS r " +
                "ON w.warehouseId = r.warehouseId " +
                "ORDER BY w.warehouseId";

        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        List<WarehouseDto> warehouseDtoList = new ArrayList<>();
        Warehouse currentWarehouse = null;
        List<ItemDto> warehouseItems = null;  // Changed to ItemDto to store item with quantity
        int prevId = -1;  // Initialize to -1 to handle the first warehouse properly

        while (resultSet.next()) {
            int warehouseId = resultSet.getInt("warehouseId");

            // If this is a new warehouse, add the previous warehouseDto to the list
            if (warehouseId != prevId) {
                // Add the previous warehouse to the list
                if (currentWarehouse != null && warehouseItems != null) {
                    warehouseDtoList.add(new WarehouseDto(currentWarehouse, warehouseItems));
                }
                // Create a new warehouse and item list
                currentWarehouse = Utilities.createWarehouse(resultSet);
                warehouseItems = new ArrayList<>();
                prevId = warehouseId;
            }

            // Create the item and itemDto
            Item item = Utilities.createItem(resultSet);
            ItemDto itemDto = new ItemDto(item,resultSet.getFloat("quantity"));
            warehouseItems.add(itemDto); // Add itemDto to the list for the current warehouse
        }

        // Add the last warehouseDto after the loop ends
        if (currentWarehouse != null && warehouseItems != null) {
            warehouseDtoList.add(new WarehouseDto(currentWarehouse, warehouseItems));
        }

        return warehouseDtoList;
    }


}