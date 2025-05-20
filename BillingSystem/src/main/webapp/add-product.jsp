<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Product</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="container form-container">
        <h2 class="form-title">ADD PRODUCT</h2>

        <form action="AddProductServlet" method="post" class="product-form">
            <label for="name">Name:</label>
            <input type="text" name="name" id="name" required>

            <label for="description">Description:</label>
            <textarea name="description" id="description" required></textarea>

            <label for="price">Price:</label>
            <input type="number" step="0.01" name="price" id="price" required>

            <label for="quantity">Quantity:</label>
            <input type="number" name="quantity" id="quantity" required>

            <label for="category">Category:</label>
            <input type="text" name="category" id="category">

            <button type="submit" class="btn btn-add">Submit</button>
            <a href="product-inventory.jsp" class="btn btn-cancel">Back</a>
        </form>
    </div>
</body>
</html>
