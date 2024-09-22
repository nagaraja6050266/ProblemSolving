//import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDao {

    public void addEmployee(String name, int age, String position) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "insert into employee (name,age,position) values (?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, name);
        statement.setInt(2, age);
        statement.setString(3, position);
        statement.executeUpdate();
    }

    public Employee getEmployee(int id) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "select * from employee where id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            return new Employee(result.getInt("id"), result.getString("name"), result.getInt("age"), result.getString("position"));
        } else {
            return null;
        }
    }

    public void updateEmployee(int id, String name, int age, String position) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "update employee set name=?,age=?,position=? where id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, name);
        statement.setInt(2, age);
        statement.setString(3, position);
        statement.setInt(4, id);
        statement.executeUpdate();
    }

    public void deleteEmployee(int id) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "delete from employee where id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

}
