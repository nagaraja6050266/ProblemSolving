package supermarket.dao;

import supermarket.database.Database;
import supermarket.models.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriesDao {

    private final Connection connection = Database.getConnection();

    public Category addCategory(Category category) throws SQLException {
        String query = "insert into categories (name) values(?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1,category.getName());
        statement.executeUpdate();
        return category;
    }

    public Category getCategoryById(int categoryId) throws SQLException {
        String query = "select * from categories where categoryId=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, categoryId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return createCategory(resultSet);
        }
        return null;
    }

    public List<Category> getAllCategories() throws SQLException {
        String query = "select * from categories";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        List<Category> categories = new ArrayList<>();
        while (resultSet.next()) {
            categories.add(createCategory(resultSet));
        }
        return categories;
    }

    public int editCategory(int categoryId, Category category) throws SQLException {
        String query = "update categories set name=? where categoryId=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, category.getName());
        statement.setInt(2, categoryId);
        return statement.executeUpdate();
    }

    public int deleteCategory(int categoryId) throws SQLException {
        String query = "delete from categories where categoryId=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, categoryId);
        return statement.executeUpdate();
    }

    private Category createCategory(ResultSet resultSet) throws SQLException {
        return new Category(resultSet.getInt("categoryId"), resultSet.getString("name"));
    }

}