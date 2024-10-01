package org.inr.tutorial.messenger.dao;

import org.inr.tutorial.messenger.database.Database;
import org.inr.tutorial.messenger.model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemsDao {

    public Item addItem(Item item) throws SQLException {
        Connection connection = Database.getConnection();
        String query = "insert into items values(?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, item.getId());
        statement.setFloat(2,item.getPrice());
        statement.setString(3, item.getName());
        statement.setFloat(4, item.getQuantity());
        statement.executeUpdate();
        return item;
    }

    public Item getItemById(int id) throws SQLException {
        Connection connection = Database.getConnection();
        String query = "select * from items where id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
//        System.out.println(resultSet.toString());
        if (resultSet.next()) {
            return createItem(resultSet);
        }
        return null;
    }

    public List<Item> getAllItems() throws SQLException {
        Connection connection = Database.getConnection();
        String query = "select * from items";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        List<Item> items = new ArrayList<>();
        while (resultSet.next()) {
            items.add(createItem(resultSet));
        }
        return items;
    }

    public int editItem(int id, Item item) throws SQLException {
        Connection connection = Database.getConnection();
        String query = "update items set id=?,name=?,price=?,quantity=? where id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, item.getId());
        statement.setString(2, item.getName());
        statement.setFloat(3, item.getPrice());
        statement.setFloat(4,item.getQuantity());
        statement.setInt(5,id);
//        System.out.println(statement.toString());
        return statement.executeUpdate();
    }

    public int deleteItem(int id) throws SQLException {
        Connection connection = Database.getConnection();
        String query = "delete from items where id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        return statement.executeUpdate();
    }

    private Item createItem(ResultSet resultSet) throws SQLException {
        return new Item(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getFloat("price"), resultSet.getFloat("quantity"));
    }
}
