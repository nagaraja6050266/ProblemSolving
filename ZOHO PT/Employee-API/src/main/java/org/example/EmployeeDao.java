package org.example;//import javax.servlet.http.HttpServletRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

    public void addEmployee(int id, String name, int age, String position) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "insert into employee (id,name,age,position) values (?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        statement.setString(2, name);
        statement.setInt(3, age);
        statement.setString(4, position);
        statement.executeUpdate();
    }

    public Employee getEmployee(int id) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "select * from employee where id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            return createEmployeeObject(result);
        } else {
            return null;
        }
    }

    public void updateEmployee(int idToFind, int idToChange, String name, int age, String position) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "update employee set name=?,age=?,position=?, id=? where id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, name);
        statement.setInt(2, age);
        statement.setString(3, position);
        statement.setInt(4, idToChange);
        statement.setInt(5,idToFind);
        statement.executeUpdate();
    }

    public int deleteEmployee(int id) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "delete from employee where id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        return statement.executeUpdate();
    }

    public List<Employee> getAllEmployee() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "select * from employee";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet result = statement.executeQuery();
        List<Employee> employees = new ArrayList<>();
        while (result.next()) {
            employees.add(createEmployeeObject(result));
        }
        return employees;
    }

    private static Employee createEmployeeObject(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String name = result.getString("name");
        int age = result.getInt("age");
        String position = result.getString("position");
        return new Employee(id, name, age, position);
    }
}
