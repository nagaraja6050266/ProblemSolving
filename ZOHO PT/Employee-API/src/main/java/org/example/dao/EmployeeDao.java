package org.example.dao;//import javax.servlet.http.HttpServletRequest;

import org.example.DatabaseConnection;
import org.example.models.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

    public Employee addEmployee(Employee employee) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "insert into employees (name,age,position) values (?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, employee.getName());
        statement.setInt(2, employee.getAge());
        statement.setString(3, employee.getPosition());
        if(statement.executeUpdate()==0){
            return null;
        }
        return employee;
    }

    public Employee getEmployee(int id) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "select * from employees where id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            return createEmployeeObject(result);
        } else {
            return null;
        }
    }

    public void updateEmployee(int id, Employee employee) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "update employees set name=?,age=?,position=? where id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, employee.getName());
        statement.setInt(2, employee.getAge());
        statement.setString(3, employee.getPosition());
        statement.setInt(4, id);
        statement.executeUpdate();
    }

    public int deleteEmployee(int id) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "delete from employees where id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        return statement.executeUpdate();
    }

    public List<Employee> getAllEmployee() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "select * from employees";
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
