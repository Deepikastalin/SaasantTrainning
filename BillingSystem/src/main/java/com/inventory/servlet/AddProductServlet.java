package com.inventory.servlet;

import com.inventory.dao.ProductDAO;
import com.inventory.model.Product;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AddProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
        	
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String category = request.getParameter("category");

            Product product = new Product(name, description, price, quantity, category);

            ProductDAO productDAO = new ProductDAO();
            productDAO.addProduct(product);

            response.sendRedirect("list");
        } catch (Exception e) {
            throw new ServletException("Error adding product", e);
        }
    }
}
