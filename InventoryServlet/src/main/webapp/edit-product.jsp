<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.inventory.model.Product" %>
<%
    Product product = (Product) request.getAttribute("product");
    if (product == null) {
%>
    <h2>Product not found.</h2>
<%
    return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Product</title>
    <link rel="stylesheet" href="styles.css">
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 500px;
            margin: 60px auto;
            background: #fff;
            padding: 30px 40px;
            box-shadow: 0 0 15px rgba(0,0,0,0.1);
            border-radius: 8px;
        }

        .form-title {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin: 15px 0 5px;
            font-weight: 600;
        }

        input[type="text"],
        input[type="number"],
        textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 15px;
        }

        .btn-submit {
            margin-top: 25px;
            padding: 12px 20px;
            background-color: #5a67d8;
            border: none;
            color: white;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
            width: 100%;
        }

        .btn-submit:hover {
            background-color: #434190;
        }

        a.back-link {
            display: inline-block;
            margin-top: 15px;
            text-align: center;
            color: #555;
            text-decoration: none;
        }

        a.back-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2 class="form-title">EDIT PRODUCT</h2>

        <form action="EditProductServlet" method="post" class="product-form">
            <input type="hidden" name="id" value="<%= product.() %>">

            <label>Name:</label>
            <input type="text" name="name" value="<%= product.getName() %>" required>

            <label>Description:</label>
            <textarea name="description" required><%= product.getDescription() %></textarea>

            <label>Price:</label>
            <input type="number" step="0.01" name="price" value="<%= product.getPrice() %>" required>

            <label>Quantity:</label>
            <input type="number" name="quantity" value="<%= product.getQuantity() %>" required>

            <label>Category:</label>
            <input type="text" name="category" value="<%= product.getCategory() %>">

            <button type="submit" class="btn-submit">Update Product</button>
        </form>

        <a href="product-list.jsp" class="back-link">← Back to Product List</a>
    </div>
</body>
</html>
