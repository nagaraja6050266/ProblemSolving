package org.inr.supermarket.dao;

import org.inr.supermarket.database.Database;
import org.inr.supermarket.models.Invoice;
import org.inr.supermarket.models.InvoiceStatus;
import org.inr.supermarket.models.Item;
import org.inr.supermarket.models.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PaymentsDao {

    private final Connection connection = Database.getConnection();

    public int addPayment(Payment payment) throws SQLException {
        payment.setDate(new Date());
        String query = "insert into payments values(?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, payment.getInvoiceId());
        statement.setDate(2, new java.sql.Date(payment.getDate().getTime()));
        statement.setFloat(3, payment.getAmount());
        return statement.executeUpdate();
    }


    public Payment getPaymentById(int id) throws SQLException {
        String query = "select * from payments where invoiceId=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return createPayment(resultSet);
        }
        return null;
    }

    public List<Payment> getAllPayments() throws SQLException {
        String query = "select * from payments";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        List<Payment> payments = new ArrayList<>();
        while (resultSet.next()) {
            payments.add(createPayment(resultSet));
        }
        return payments;
    }

    public int editPayment(int id, Payment payment) throws SQLException {
        String query = "update payments set date=?,amount=? where invoiceId=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, payment.getInvoiceId());
        statement.setFloat(2, payment.getAmount());
        statement.setInt(3, id);
        return statement.executeUpdate();
    }

    public int deletePayment(int id) throws SQLException {
        String query = "delete from payments where invoiceId=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        return statement.executeUpdate();
    }

    private Payment createPayment(ResultSet resultSet) throws SQLException {
        return new Payment(resultSet.getInt("invoiceId"), resultSet.getFloat("amount"), resultSet.getDate("date"));
    }

}
