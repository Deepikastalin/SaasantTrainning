package com.inventory.controller;

import com.inventory.dao.ProductDAO;
import com.inventory.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/SearchProductServlet")
public class SearchProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String query = request.getParameter("query");
        response.setContentType("text/html;charset=UTF-8");

        ProductDAO dao = new ProductDAO();
        List<Product> products;

        if (query == null || query.trim().isEmpty()) {
            products = dao.getAllProducts(); // Returns all products
        } else {
            products = dao.searchProducts(query.trim()); // Implement searchProducts(String query) in DAO
        }

        PrintWriter out = response.getWriter();
        if (products != null && !products.isEmpty()) {
            for (Product p : products) {
                out.println("<tr>");
                out.println("<td>" + p.getProduct_id() + "</td>");
                out.println("<td>" + escapeHtml(p.getName()) + "</td>");
                out.println("<td>" + String.format("%.2f", p.getPrice()) + "</td>");
                out.println("<td>" + p.getTaxPercent() + "</td>");
                out.println("<td>"
                        + "<a href='edit-product.jsp?id=" + p.getProduct_id() + "' class='action-link'>Edit</a> | "
                        + "<a href='#' class='action-link delete-btn' data-id='" + p.getProduct_id() + "'>Delete</a>"
                        + "</td>");
                out.println("</tr>");
            }
        } else {
            out.println("<tr><td colspan='5'>No products found</td></tr>");
        }
    }

    // Basic HTML escape to avoid issues with product names
    private String escapeHtml(String input) {
        if (input == null) return "";
        return input.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                    .replace("\"", "&quot;").replace("'", "&#x27;");
    }
}
