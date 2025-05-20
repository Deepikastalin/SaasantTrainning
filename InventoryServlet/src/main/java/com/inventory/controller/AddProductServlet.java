package com.inventory.controller;

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
            double price = Double.parseDouble(request.getParameter("price"));
            int taxPercent = Integer.parseInt(request.getParameter("taxPercent"));

            Product product = new Product();
            product.setName(name);
            product.setPrice(price);
            product.setTaxPercent(taxPercent);

            ProductDAO productDAO = new ProductDAO();
            productDAO.addProduct(product);

            response.sendRedirect("products");  // redirect to product list page
        } catch (Exception e) {
            throw new ServletException("Error adding product", e);
        }
    }
}
