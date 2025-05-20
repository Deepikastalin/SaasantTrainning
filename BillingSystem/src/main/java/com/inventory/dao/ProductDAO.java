package com.inventory.dao;

import com.inventory.model.Product;
import java.sql.*;
import java.util.*;

public class ProductDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/inventorydb";
    private String jdbcUsername = "root";
    private String jdbcPassword = "asinstalin";

    private static final String INSERT_SQL = "INSERT INTO products (name, description, price, quantity, category) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM products";
    private static final String SELECT_BY_ID = "SELECT * FROM products WHERE id=?";
    private static final String UPDATE_SQL = "UPDATE products SET name=?, description=?, price=?, quantity=?, category=? WHERE id=?";
    private static final String DELETE_SQL = "DELETE FROM products WHERE id=?";

    protected Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Ensure driver is loaded
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found", e);
        }
        return connection;
    }

    public List<Product> listAll() throws SQLException {
        List<Product> products = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
            	Product p1 = new Product();
            	p1.setId(rs.getInt("id"));
                p1.setId( rs.getString("description"));
                p1.setId(rs.getDouble("price"));
                p1.setId(rs.getInt("quantity"));
                p1.setId(rs.getString("category"));
                
            }
        }
        return products;
    }

    // Add product method
    public void addProduct(Product product) throws SQLException {
        String sql = "INSERT INTO products (name, description, price, quantity, category) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getQuantity());
            stmt.setString(5, product.getCategory());
            stmt.executeUpdate();
        }
    }

    public Product get(int id) throws SQLException {
        String sql = "SELECT * FROM products WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    double price = rs.getDouble("price");
                    int quantity = rs.getInt("quantity");
                    String category = rs.getString("category");
                    return new Product( name, description, price, quantity, category);
                }
            }
        }
        return null;
    }


    public Product getProductById(int id) throws SQLException {
        Product p = null;
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                p = new Product( rs.getString("name"), rs.getString("description"),
                        rs.getDouble("price"), rs.getInt("quantity"), rs.getString("category"));
            }
        }
        return p;
    }

    // Update product method
    public void updateProduct(Product p) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_SQL)) {
            stmt.setString(1, p.getName());
            stmt.setString(2, p.getDescription());
            stmt.setDouble(3, p.getPrice());
            stmt.setInt(4, p.getQuantity());
            stmt.setString(5, p.getCategory());
            stmt.setInt(6, p.getId());
            stmt.executeUpdate();
        }
    }
  

    // Delete product method
    public void deleteProduct(int id) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_SQL)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
