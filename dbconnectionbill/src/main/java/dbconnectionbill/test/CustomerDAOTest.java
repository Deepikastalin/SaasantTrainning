package dbconnectionbill.test;

import static org.junit.jupiter.api.Assertions.*;
import dbconnectionbill.models.Customer;
import dbconnectionbill.controllers.CustomerDAO;
import dbconnectionbill.utils.DatabaseUtil;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Optional;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CustomerDAOTest {

    private CustomerDAO customerDAO;

    @BeforeAll
    public void setupDatabase() {
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute("CREATE TABLE IF NOT EXISTS customers (" +
                    "customer_id VARCHAR(10) PRIMARY KEY, " +
                    "name VARCHAR(50), phone VARCHAR(10) UNIQUE)");

            stmt.execute("INSERT INTO customers (customer_id, name, phone) VALUES " +
                    "('C101', 'John Doe', '9876543210')," +
                    "('C102', 'Alice', '9123456789')");

            customerDAO = new CustomerDAO();

        } catch (Exception e) {
            fail("Database setup failed: " + e.getMessage());
        }
    }

    @Test
    public void testFindByPhone_ExistingCustomer() {
        Optional<Customer> customer = customerDAO.findByPhone("9876543210");
        assertTrue(customer.isPresent(), "Customer should exist");
        assertEquals("John Doe", customer.get().getCustomerName());
    }

    @Test
    public void testFindByPhone_NonExistingCustomer() {
        Optional<Customer> customer = customerDAO.findByPhone("0000000000");
        assertFalse(customer.isPresent(), "Customer should not exist");
    }

    @Test
    public void testInsertCustomer() {
        Customer newCustomer = new Customer("C103", "Bob", "9998887776");
        customerDAO.insertCustomer(newCustomer);
        Optional<Customer> retrievedCustomer = customerDAO.findByPhone("9998887776");
        assertTrue(retrievedCustomer.isPresent(), "Customer should be added");
        assertEquals("Bob", retrievedCustomer.get().getCustomerName());
    }

    @AfterAll
    public void cleanupDatabase() {
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("DROP TABLE customers"); // Clean test data
        } catch (Exception e) {
            fail("Database cleanup failed: " + e.getMessage());
        }
    }
}
