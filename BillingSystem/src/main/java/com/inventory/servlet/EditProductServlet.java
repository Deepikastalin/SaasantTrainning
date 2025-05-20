package com.inventory.servlet;

import com.inventory.dao.ProductDAO;
import com.inventory.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/EditProductServlet")
public class EditProductServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // 1. Retrieve parameters from form
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String category = request.getParameter("category");
            int id = Integer.parseInt(request.getParameter("id"));

            // 2. Fetch the product from DB to ensure it exists
            ProductDAO dao = new ProductDAO();
            Product existingProduct = dao.getProductById(id);
            
            if (existingProduct == null) {
                // Product not found, show an error
                request.setAttribute("errorMessage", "Product not found with ID: " + id);
                request.getRequestDispatcher("edit-product.jsp").forward(request, response);
                return;
            }

            // 3. Create a new Product object to update
            Product product = new Product(id, name, description, price, quantity, category);
            
            // 4. Update product in DB
            dao.updateProduct(product);

            // 5. Redirect to product list
            response.sendRedirect("product-list.jsp");

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error updating product: " + e.getMessage());
            request.getRequestDispatcher("edit-product.jsp").forward(request, response);
        }
    }
}
