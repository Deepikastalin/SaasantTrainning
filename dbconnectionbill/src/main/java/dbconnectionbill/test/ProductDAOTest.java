package dbconnectionbill.test;

import static org.junit.jupiter.api.Assertions.*;
import dbconnectionbill.models.Product;
import dbconnectionbill.controllers.ProductDAO;
import dbconnectionbill.utils.DatabaseUtil;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductDAOTest {

    private ProductDAO productDAO;

    @BeforeAll
    public void setupDatabase() {
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute("CREATE TABLE IF NOT EXISTS products (" +
                    "product_id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(50), price DOUBLE, tax_percent INT)");
            
            stmt.execute("INSERT INTO products (product_id, name, price, tax_percent) VALUES " +
                    "(1, 'Laptop', 50000.00, 18), " +
                    "(4, 'Monitor', 12000.00, 18), " +
                    "(2, 'Mouse', 500.00, 12), " +
                    "(3, 'Keyboard', 1500.00, 12)");

            productDAO = new ProductDAO();

        } catch (Exception e) {
            fail("Database setup failed: " + e.getMessage());
        }
    }

    @Test
    public void testGetAllProducts_NotEmpty() {
        Map<Integer, Product> products = productDAO.getAllProducts();
        assertNotNull(products, "Product list should not be null");
        assertFalse(products.isEmpty(), "Product list should not be empty");
    }

    @Test
    public void testProductRetrieval_Laptop() {
        Map<Integer, Product> products = productDAO.getAllProducts();
        Product laptop = products.get(1);
        assertNotNull(laptop, "Laptop should exist");
        assertEquals("Laptop", laptop.getDescription());
        assertEquals(50000.00, laptop.getPricePerUnit());
        assertEquals(18, laptop.getGstPercentage());
    }

    @Test
    public void testProductRetrieval_Keyboard() {
        Map<Integer, Product> products = productDAO.getAllProducts();
        Product keyboard = products.get(3);
        assertNotNull(keyboard, "Keyboard should exist");
        assertEquals("Keyboard", keyboard.getDescription());
        assertEquals(1500.00, keyboard.getPricePerUnit());
        assertEquals(12, keyboard.getGstPercentage());
    }

    @AfterAll
    public void cleanupDatabase() {
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("DROP TABLE products"); // Clean test data
        } catch (Exception e) {
            fail("Database cleanup failed: " + e.getMessage());
        }
    }
}
