package supermarket.dao;

import supermarket.database.Database;
import supermarket.models.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomersDao {

    private final Connection connection = Database.getConnection();

    public Customer addCustomer(Customer customer) throws SQLException {
        String query = "insert into customers values(?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, customer.getName());
        statement.setString(2, customer.getPhNumber());
        statement.executeUpdate();
        return customer;
    }

    public Customer getCustomerById(int customerId) throws SQLException {
        String query = "select * from customers where customerId=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, customerId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return createCustomer(resultSet);
        }
        return null;
    }

    public List<Customer> getAllCustomers() throws SQLException {
        String query = "select * from customers";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        List<Customer> customers = new ArrayList<>();
        while (resultSet.next()) {
            customers.add(createCustomer(resultSet));
        }
        return customers;
    }

    public int editCustomer(int id, Customer customer) throws SQLException {
        String query = "update customers set name=?,phNumber=? where customerId=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, customer.getCustomerId());
        statement.setString(2, customer.getName());
        statement.setString(3, customer.getPhNumber());
        statement.setInt(4, id);
        return statement.executeUpdate();
    }

    public int deleteCustomer(int id) throws SQLException {
        String query = "delete from customers where customerId=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        return statement.executeUpdate();
    }

    private Customer createCustomer(ResultSet resultSet) throws SQLException {
        return new Customer(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("phNumber"));
    }
}
