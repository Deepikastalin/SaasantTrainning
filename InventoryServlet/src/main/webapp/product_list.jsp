<%@ page import="com.inventory.model.Product" %>
<%@ page import="java.util.List" %>

<%
    List<Product> products = (List<Product>) request.getAttribute("productList");
    if (products != null && !products.isEmpty()) {
%>
    <h3>Product List</h3>
    <ul>
        <% for (Product p : products) { %>
            <li>
                <p><strong>ID:</strong> <%= p.getProduct_id() %></p>
                <p><strong>Name:</strong> <%= p.getName() %></p>
                <p><strong>Price:</strong> ₹<%= String.format("%.2f", p.getPrice()) %></p>
                <p><strong>Tax (%):</strong> <%= p.getTaxPercent() %></p>
            </li>
            <hr/>
        <% } %>
    </ul>
<%
    } else {
%>
    <p>No products found.</p>
<%
    }
%>
