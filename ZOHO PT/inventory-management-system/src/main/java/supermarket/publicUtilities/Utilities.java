package supermarket.publicUtilities;

import supermarket.dto.BatchDto;
import supermarket.dto.SaleDto;
import supermarket.models.Item;
import supermarket.models.LineItem;
import supermarket.models.Sale;
import supermarket.models.Warehouse;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Utilities {
    public static String jsonMessage(String message) {
        return "{\"message\":\"" + message + "\"}";
    }

    public static int getGeneratedKey(PreparedStatement statement) throws SQLException {
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            return generatedKeys.getInt(1);
        }
        return -1;
    }

    public static Item createItem(ResultSet resultSet) throws SQLException {
        return new Item(
                resultSet.getInt("itemId"),
                resultSet.getString("itemName"),
                resultSet.getInt("categoryId"),
                resultSet.getFloat("costPrice"),
                resultSet.getFloat("sellingPrice")
        );
    }

    public static LineItem createLineItem(ResultSet rs) throws SQLException {
        return new LineItem(
                rs.getInt("itemId"),
                rs.getFloat("quantity"),
                rs.getFloat("amount")
        );
    }

    public static Warehouse createWarehouse(ResultSet resultSet) throws SQLException {
        return new Warehouse(
                resultSet.getInt("warehouseId"),
                resultSet.getString("warehouseName"),
                resultSet.getString("location"),
                resultSet.getInt("capacity"));
    }

    public static BatchDto createBatch(ResultSet resultSet) throws SQLException {
        return new BatchDto(resultSet.getInt("batchId"),
                resultSet.getInt("itemId"),
                resultSet.getString("itemName"),
                resultSet.getFloat("quantity"),
                resultSet.getDate("expiryDate"));
    }

    public static Sale createSale(ResultSet resultSet) throws SQLException {
        return new Sale(
                resultSet.getInt("salesId"),
                resultSet.getDate("date"),
                resultSet.getInt("customerId"),
                resultSet.getFloat("totalAmount"));
    }

}

