package supermarket.dao;

import supermarket.database.Database;
import supermarket.dto.BatchDto;
import supermarket.dto.ItemWarehouseDto;
import supermarket.models.Item;
import supermarket.publicUtilities.Utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemsDao {

    private final Connection connection = Database.getConnection();

    public Item addItem(ItemWarehouseDto itemWarehouseDto) throws SQLException {

        connection.setAutoCommit(false);

        try {
            String itemQuery = "insert into items (itemName,categoryId,costPrice,sellingPrice) values(?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(itemQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, itemWarehouseDto.getName());
            statement.setInt(2, itemWarehouseDto.getCategoryId());
            statement.setFloat(3, itemWarehouseDto.getCostPrice());
            statement.setFloat(4, itemWarehouseDto.getSellingPrice());
            statement.executeUpdate();

            itemWarehouseDto.setItemId(Utilities.getGeneratedKey(statement));

            String wareHouseQuery = "insert into warehouseitems (warehouseId,itemId,quantity) values (?,?,?)";
            statement = connection.prepareStatement(wareHouseQuery);
            statement.setInt(1, itemWarehouseDto.getWarehouseId());
            statement.setInt(2, itemWarehouseDto.getItemId());
            statement.setFloat(3, itemWarehouseDto.getQuantity());
            statement.executeUpdate();

        } catch (Exception e){
            connection.rollback();
            throw new SQLException(e.toString());
        } finally {
            connection.commit();
            connection.setAutoCommit(true);
        }
        return itemWarehouseDto;
    }

    public Item getItemById(int itemId) throws SQLException {
        String query = "select * from items where itemId=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, itemId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return Utilities.createItem(resultSet);
        }
        return null;
    }

    public List<Item> getAllItems() throws SQLException {
        String query = "select * from items";
        PreparedStatement statement = connection.prepareStatement(query);
        return getItems(statement);
    }

    public List<Item> getAllItems(int categoryId) throws SQLException {
        String query = "select * from items where categoryId=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, categoryId);
        return getItems(statement);
    }

    private List<Item> getItems(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        List<Item> items = new ArrayList<>();
        while (resultSet.next()) {
            items.add(Utilities.createItem(resultSet));
        }
        return items;
    }

    public int editItem(int itemId, Item item) throws SQLException {
        String query = "update items set itemName=?,costPrice=?,sellingPrice=? where itemId=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, item.getName());
        statement.setFloat(2, item.getCostPrice());
        statement.setFloat(3, item.getSellingPrice());
        statement.setInt(4, itemId);
        return statement.executeUpdate();
    }

    public int deleteItem(int itemId) throws SQLException {
        String query = "delete from items where itemId=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, itemId);
        return statement.executeUpdate();
    }

}