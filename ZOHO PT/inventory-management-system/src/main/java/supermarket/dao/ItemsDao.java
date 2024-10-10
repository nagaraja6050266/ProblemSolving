package supermarket.dao;

import supermarket.database.Database;
import supermarket.models.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemsDao {

    private final Connection connection = Database.getConnection();

    public Item addItem(Item item) throws SQLException {
        String query = "insert into items (name,quantity,categoryId,costPrice,sellingPrice,expirationDays) values(?,?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, item.getName());
        statement.setFloat(2, item.getQuantity());
        statement.setInt(3, item.getCategoryId());
        statement.setFloat(4, item.getCostPrice());
        statement.setFloat(5, item.getSellingPrice());
        statement.setInt(6, item.getExpirationDays());
        statement.executeUpdate();
        return item;
    }

    public Item getItemById(int itemId) throws SQLException {
        String query = "select * from items where itemId=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, itemId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return createItem(resultSet);
        }
        return null;
    }

    public List<Item> getAllItems() throws SQLException {
        String query = "select * from items";
        PreparedStatement statement = connection.prepareStatement(query);
        return getItems(statement);
    }

    public List<Item> getAllItems(int categoryId) throws SQLException{
        String query = "select * from items where categoryId=?";
        PreparedStatement statement=connection.prepareStatement(query);
        statement.setInt(1,categoryId);
        return getItems(statement);
    }

    private List<Item> getItems(PreparedStatement statement) throws SQLException{
        ResultSet resultSet = statement.executeQuery();
        List<Item> items = new ArrayList<>();
        while (resultSet.next()) {
            items.add(createItem(resultSet));
        }
        return items;
    }

    public int editItem(int itemId, Item item) throws SQLException {
        String query = "update items set name=?,quantity=?,costPrice=?,sellingPrice=?,expirationDays=? where itemId=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, item.getName());
        statement.setFloat(2, item.getQuantity());
        statement.setFloat(3, item.getCostPrice());
        statement.setFloat(4, item.getSellingPrice());
        statement.setInt(5, item.getExpirationDays());
        statement.setInt(6, itemId);
        return statement.executeUpdate();
    }

    public int deleteItem(int itemId) throws SQLException {
        String query = "delete from items where itemId=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, itemId);
        return statement.executeUpdate();
    }

    private Item createItem(ResultSet resultSet) throws SQLException {
        return new Item(
                resultSet.getInt("itemId"),
                resultSet.getString("name"),
                resultSet.getFloat("quantity"),
                resultSet.getInt("categoryId"),
                resultSet.getFloat("costPrice"),
                resultSet.getFloat("sellingPrice"),
                resultSet.getInt("expirationDays")
        );
    }

}