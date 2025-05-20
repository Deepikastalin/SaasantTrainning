import com.dbconnection_billingsystem.CustomerDAO;
import com.dbconnection_billingsystem.ProductDAO;
import com.dbconnection_billingsystem.Customer;
import com.dbconnection_billingsystem.Product;
import com.dbconnection_billingsystem.CartItem;
import com.dbconnection_billingsystem.Invoice;
import com.dbconnection_billingsystem.Merchant;

import java.util.*;
public class Main {
    private static int customerIdCounter = 103;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Merchant merchant = new Merchant("ABC Traders");

        CustomerDAO customerDAO = new CustomerDAO();
        ProductDAO productDAO = new ProductDAO();

        // Load products from DB
        Map<Integer, Product> productDatabase = productDAO.getAllProducts();

        // --- Get and Validate Phone Number ---
        String inputPhoneNumber;
        do {
            System.out.print("Enter Customer Phone Number (10 digits): ");
            inputPhoneNumber = sc.nextLine();
            if (!inputPhoneNumber.matches("\\d{10}")) {
                System.out.println("Invalid phone number! Please enter a 10-digit number.");
            }
        } while (!inputPhoneNumber.matches("\\d{10}"));

        final String phoneNumber = inputPhoneNumber;

        // --- Search or Register Customer ---
        Optional<Customer> optionalCustomer = customerDAO.findByPhone(phoneNumber);

        Customer customer;
        if (optionalCustomer.isPresent()) {
            customer = optionalCustomer.get();
            System.out.println("Existing Customer Found: " + customer.getCustomerName() + ", " + customer.getContactDetails());
        } else {
            System.out.println("Customer not found! Please register:");
            String newCustomerId = "C" + customerIdCounter++;
            System.out.print("Enter Customer Name: ");
            String name = sc.nextLine();
            customer = new Customer(newCustomerId, name, phoneNumber);
            customerDAO.insertCustomer(customer);
            System.out.println("Customer registered successfully! Assigned ID: " + newCustomerId);
        }

        // --- Add Products to Cart ---
        List<CartItem> cartItems = new ArrayList<>();
        while (true) {
            System.out.println("\nAvailable Products:");
            productDatabase.values().forEach(p ->
                    System.out.printf("ID: %d | %s | ₹%.2f | GST: %.1f%%\n",
                            p.getProductId(), p.getDescription(), p.getPricePerUnit(), p.getGstPercentage()));

            System.out.print("Enter Product ID to add to cart (or 0 to finish): ");
            int prodId = sc.nextInt();
            if (prodId == 0) break;

            Product selectedProduct = productDatabase.get(prodId);
            if (selectedProduct == null) {
                System.out.println("Invalid Product ID! Try again.");
                continue;
            }

            System.out.print("Enter Quantity: ");
            int qty = sc.nextInt();
            cartItems.add(new CartItem(selectedProduct, qty));
        }

        if (cartItems.isEmpty()) {
            System.out.println("No products selected. Billing cancelled.");
            return;
        }

        Invoice invoice = new Invoice(merchant, customer, cartItems);
        invoice.printInvoice();
    }
}
