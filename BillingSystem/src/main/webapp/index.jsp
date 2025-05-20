<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, com.inventory.model.Product" %>
<!DOCTYPE html>
<html>
<head>
    <title>Inventory Details</title>
    <link rel="stylesheet" href="styles.css" />
</head>
<body>
<div class="container">
    <h2>INVENTORY DETAILS</h2>

    <div class="top-bar">
        <label for="search">SEARCH :</label>
        <input type="text" id="search" onkeyup="searchProduct()" placeholder="Search by name...">
        <a href="add-product.jsp"><button class="add-btn">ADD PRODUCT</button></a>
    </div>

    <table id="productTable">
        <thead>
            <tr>
                <th>ID</th><th>Name</th><th>Description</th>
                <th>Price</th><th>Quantity</th><th>Category</th><th>Action</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Product> products = (List<Product>) request.getAttribute("productList");
                if (products != null) {
                    for (Product p : products) {
            %>
            <tr>
                <td><%= p.getId() %></td>
                <td><%= p.getName() %></td>
                <td><%= p.getDescription() %></td>
                <td><%= p.getPrice() %></td>
                <td><%= p.getQuantity() %></td>
                <td><%= p.getCategory() %></td>
                <td>
                    <a href="edit-product.jsp?id=<%= p.getId() %>">Edit</a> |
                    <a href="DeleteProductServlet?id=<%= p.getId() %>" onclick="return confirm('Are you sure?')">Delete</a>
                </td>
            </tr>
            <%
                    }
                }
            %>
        </tbody>
    </table>
</div>

<script>
function searchProduct() {
    let input = document.getElementById("search").value.toUpperCase();
    let rows = document.getElementById("productTable").getElementsByTagName("tr");
    for (let i = 1; i < rows.length; i++) {
        let name = rows[i].getElementsByTagName("td")[1];
        if (name) {
            let value = name.textContent || name.innerText;
            rows[i].style.display = value.toUpperCase().includes(input) ? "" : "none";
        }
    }
}
</script>
</body>
</html>
