package com.inventory.servlet;

import com.inventory.dao.ProductDAO;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class DeleteProductServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
          
            ProductDAO productDAO = new ProductDAO();
            
            // Use the instance to call the delete method
            productDAO.deleteProduct(id);

            response.sendRedirect("list");
        } catch (Exception e) {
            throw new ServletException("Error deleting product", e);
        }
    }
}
