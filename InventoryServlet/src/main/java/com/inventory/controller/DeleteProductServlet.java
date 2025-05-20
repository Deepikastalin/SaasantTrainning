package com.inventory.controller;

import com.inventory.dao.ProductDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/DeleteProductServlet")
public class DeleteProductServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ProductDAO dao = new ProductDAO();

        boolean result = dao.deleteProduct(id); // Implement this method in your DAO

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        if (result) {
            out.write("success");
        } else {
            out.write("failure");
        }
    }
}
