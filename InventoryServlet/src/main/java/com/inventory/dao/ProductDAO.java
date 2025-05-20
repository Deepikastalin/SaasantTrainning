package com.inventory.dao;

import com.inventory.model.Product;
import java.sql.*;
import java.util.*;

public class ProductDAO {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/saasant_billing";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "asinstalin";

    private static final String SELECT_ALL = "SELECT * FROM products";
    private static final String INSERT_PRODUCT = "INSERT INTO products (name, price, tax_percent) VALUES (?, ?, ?)";

    protected Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found", e);
        }
    }public List<Product> searchProducts(String keyword) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE name LIKE ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProduct_id(rs.getInt("product_id"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getDouble("price"));
                p.setTaxPercent(rs.getInt("tax_percent"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    // Fetch all products
    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Product p = new Product();
                p.setProduct_id(rs.getInt("product_id"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getDouble("price"));
                p.setTaxPercent(rs.getInt("tax_percent"));

                System.out.println("DEBUG: Product fetched -> " + p.getProduct_id() + ", " + p.getName() + ", " + p.getPrice() + ", " + p.getTaxPercent());
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Add new product
    private static final String INSERT_PRODUCT_SQL = "INSERT INTO products (name, price, tax_percent) VALUES (?, ?, ?)";

    public void addProduct(Product product) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_PRODUCT_SQL)) {
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, product.getTaxPercent());
            ps.executeUpdate();
        }
    }
    public boolean deleteProduct(int productId) {
        boolean rowDeleted = false;
        String sql = "DELETE FROM products WHERE product_id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            rowDeleted = stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }


}
