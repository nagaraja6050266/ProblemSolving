package supermarket.publicUtilities;

import supermarket.models.Item;
import supermarket.models.Warehouse;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public static Warehouse createWarehouse(ResultSet resultSet) throws SQLException {
        return new Warehouse(resultSet.getInt("warehouseId"), resultSet.getString("warehouseName"), resultSet.getString("location"), resultSet.getInt("capacity"));
    }

}

