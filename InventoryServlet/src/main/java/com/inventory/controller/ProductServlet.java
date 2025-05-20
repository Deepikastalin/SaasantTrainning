package com.inventory.controller;

import com.inventory.dao.ProductDAO;
import com.inventory.model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;
@WebServlet("/products")
 // Change this URL pattern as needed
public class ProductServlet extends HttpServlet {

    private ProductDAO productDAO;

    @Override
    public void init() {
        // Initialize your DAO here
        productDAO = new ProductDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Fetch list of products from DAO
            List<Product> productList = productDAO.getAllProducts();

            // Debug print - check server console/logs
            System.out.println("DEBUG: Number of products fetched = " + productList.size());

            // Set the product list as a request attribute
            request.setAttribute("productList", productList);

            // Forward the request to the JSP page
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error retrieving products", e);
        }
    }
}
