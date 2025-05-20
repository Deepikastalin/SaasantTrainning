<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Product</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f7f9fc;
            margin: 40px;
        }
        .container {
            max-width: 500px;
            margin: auto;
            padding: 25px;
            background: white;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }
        label {
            display: block;
            margin-top: 15px;
            font-weight: bold;
            color: #555;
        }
        input[type="text"], input[type="number"] {
            width: 100%;
            padding: 8px 10px;
            margin-top: 6px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        button {
            margin-top: 20px;
            background-color: #28a745;
            color: white;
            border: none;
            padding: 12px;
            width: 100%;
            font-size: 16px;
            cursor: pointer;
            border-radius: 4px;
        }
        button:hover {
            background-color: #218838;
        }
        .back-link {
            margin-top: 15px;
            display: block;
            text-align: center;
            text-decoration: none;
            color: #007bff;
        }
        .back-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Add New Product</h2>
    <form action="addProduct" method="post">
        <label for="name">Product Name:</label>
        <input type="text" id="name" name="name" required />

        <label for="price">Price (₹):</label>
        <input type="number" step="0.01" id="price" name="price" required />

        <label for="taxPercent">Tax Percentage (%):</label>
        <input type="number" id="taxPercent" name="taxPercent" required />

        <button type="submit">Add Product</button>
    </form>
    <a href="list" class="back-link">← Back to Product List</a>
</div>
</body>
</html>
