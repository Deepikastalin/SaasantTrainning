package com.dbconnection_billingsystem;

import java.sql.*;
import java.util.*;

public class ProductDAO {

    public Map<Integer, Product> getAllProducts() {
        Map<Integer, Product> productMap = new HashMap<>();
        try (Connection conn = DatabaseUtil.getConnection()) {
            String sql = "SELECT * FROM products";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("tax_percent")
                );
                productMap.put(product.getProductId(), product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productMap;
    }
}
