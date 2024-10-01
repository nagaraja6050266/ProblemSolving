package org.inr.tutorial.messenger.dao;

import org.inr.tutorial.messenger.database.Database;
import org.inr.tutorial.messenger.model.Customer;

import javax.xml.transform.Result;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomersDao {

    public Customer addCustomer(Customer customer) throws SQLException {
        Connection connection = Database.getConnection();
        String query = "insert into customers values(?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, customer.getId());
        statement.setString(2, customer.getName());
        statement.setString(3, customer.getPhNumber());
        statement.executeUpdate();
        return customer;
    }

    public Customer getCustomerById(int id) throws SQLException {
        Connection connection = Database.getConnection();
        String query = "select * from customers where id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
//        System.out.println(resultSet.toString());
        if (resultSet.next()) {
            return createCustomer(resultSet);
        }
        return null;
    }

    public List<Customer> getAllCustomers() throws SQLException {
        Connection connection = Database.getConnection();
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
        Connection connection = Database.getConnection();
        String query = "update customers set id=?,name=?,phNumber=? where id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, customer.getId());
        statement.setString(2, customer.getName());
        statement.setString(3, customer.getPhNumber());
        statement.setInt(4,id);
        System.out.println(statement.toString());
        return statement.executeUpdate();
    }

    public int deleteCustomer(int id) throws SQLException {
        Connection connection = Database.getConnection();
        String query = "delete from customers where id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        return statement.executeUpdate();
    }

    private Customer createCustomer(ResultSet resultSet) throws SQLException {
        return new Customer(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("phNumber"));
    }
}
