package supermarket.dao;

import supermarket.database.Database;
import supermarket.models.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SuppliersDao {

    private final Connection connection = Database.getConnection();

    public Supplier addSupplier(Supplier supplier) throws SQLException {
        String query = "insert into suppliers (supplierName,phNumber) values(?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, supplier.getSupplierName());
        statement.setString(2, supplier.getPhNumber());
        statement.executeUpdate();
        return supplier;
    }

    public Supplier getSupplierById(int supplierId) throws SQLException {
        String query = "select * from suppliers where supplierId=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, supplierId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return createSupplier(resultSet);
        }
        return null;
    }

    public List<Supplier> getAllSuppliers() throws SQLException {
        String query = "select * from suppliers";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        List<Supplier> suppliers = new ArrayList<>();
        while (resultSet.next()) {
            suppliers.add(createSupplier(resultSet));
        }
        return suppliers;
    }

    public int editSupplier(int id, Supplier supplier) throws SQLException {
        String query = "update suppliers set supplierName=?,phNumber=? where supplierId=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, supplier.getSupplierName());
        statement.setString(2, supplier.getPhNumber());
        statement.setInt(3, id);
        return statement.executeUpdate();
    }

    public int deleteSupplier(int id) throws SQLException {
        String query = "delete from suppliers where supplierId=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        return statement.executeUpdate();
    }

    private Supplier createSupplier(ResultSet resultSet) throws SQLException {
        return new Supplier(resultSet.getInt("supplierId"), resultSet.getString("supplierName"), resultSet.getString("phNumber"));
    }
}
