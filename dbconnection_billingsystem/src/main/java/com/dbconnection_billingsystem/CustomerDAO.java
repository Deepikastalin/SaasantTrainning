package com.dbconnection_billingsystem;

import java.sql.*;
import java.util.Optional;

public class CustomerDAO {

    public Optional<Customer> findByPhone(String phone) {
        try (Connection conn = DatabaseUtil.getConnection()) {
            String sql = "SELECT * FROM customers WHERE phone = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, phone);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(new Customer(
                        rs.getString("customer_id"),
                        rs.getString("name"),
                        rs.getString("phone")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void insertCustomer(Customer customer) {
        try (Connection conn = DatabaseUtil.getConnection()) {
            String sql = "INSERT INTO customers (customer_id, name, phone) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, customer.getCustomerId());
            stmt.setString(2, customer.getCustomerName());
            stmt.setString(3, customer.getContactDetails());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
