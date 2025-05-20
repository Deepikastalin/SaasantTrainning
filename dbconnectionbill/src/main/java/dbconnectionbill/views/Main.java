package dbconnectionbill.views;

import dbconnectionbill.models.*;
import dbconnectionbill.controllers.*;

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
        String phoneNumber = getValidPhoneNumber(sc);

        // --- Search or Register Customer ---
        Customer customer = findOrRegisterCustomer(customerDAO, phoneNumber, sc);

        // --- Add Products to Cart ---
        List<CartItem> cartItems = getCartItems(sc, productDatabase);

        if (cartItems.isEmpty()) {
            System.out.println("No products selected. Billing cancelled.");
            return;
        }

        Invoice invoice = new Invoice(merchant, customer, cartItems);
        invoice.printInvoice();
        sc.close();
    }

    public static String getValidPhoneNumber(Scanner sc) {
        String inputPhoneNumber;
        do {
            System.out.print("Enter Customer Phone Number (10 digits): ");
            inputPhoneNumber = sc.nextLine().trim();
            if (!inputPhoneNumber.matches("\\d{10}")) {
                System.out.println("Invalid phone number! Please enter a 10-digit number.");
            }
        } while (!inputPhoneNumber.matches("\\d{10}"));
        return inputPhoneNumber;
    }

    public static Customer findOrRegisterCustomer(CustomerDAO customerDAO, String phoneNumber, Scanner sc) {
        Optional<Customer> optionalCustomer = customerDAO.findByPhone(phoneNumber);

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            System.out.println("Existing Customer Found: " + customer.getCustomerName() + ", " + customer.getContactDetails());
            return customer;
        } else {
            System.out.println("Customer not found! Please register:");
            String newCustomerId = "C" + customerIdCounter++;
            System.out.print("Enter Customer Name: ");
            String name = sc.nextLine().trim();
            Customer customer = new Customer(newCustomerId, name, phoneNumber);
            customerDAO.insertCustomer(customer);
            System.out.println("Customer registered successfully! Assigned ID: " + newCustomerId);
            return customer;
        }
    }

    public static List<CartItem> getCartItems(Scanner sc, Map<Integer, Product> productDatabase) {
        List<CartItem> cartItems = new ArrayList<>();
        while (true) {
            System.out.println("\nAvailable Products:");
            productDatabase.values().forEach(p ->
                    System.out.printf("ID: %d | %s | ₹%.2f | GST: %.1f%%\n",
                            p.getProductId(), p.getDescription(), p.getPricePerUnit(), p.getGstPercentage()));

            System.out.print("Enter Product ID to add to cart (or 0 to finish): ");
            int prodId = getIntInput(sc);

            if (prodId == 0) break;

            Product selectedProduct = productDatabase.get(prodId);
            if (selectedProduct == null) {
                System.out.println("Invalid Product ID! Try again.");
                continue;
            }

            System.out.print("Enter Quantity: ");
            int qty = getIntInput(sc);

            // Check if product already exists in cart
            boolean productExists = false;
            for (CartItem item : cartItems) {
                if (item.getProduct().getProductId() == selectedProduct.getProductId()) {
                    item.setQuantity(item.getQuantity() + qty);  // Update existing quantity
                    productExists = true;
                    break;
                }
            }

            if (!productExists) {
                cartItems.add(new CartItem(selectedProduct, qty));  // Add as new item
            }
        }
        return cartItems;
    }

    public static int getIntInput(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input! Please enter a number.");
            sc.next(); // Clear invalid input
        }
        return sc.nextInt();
    }
}
