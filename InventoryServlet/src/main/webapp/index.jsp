<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, com.inventory.model.Product" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>Inventory Details</title>
    <style>
        * {
            box-sizing: border-box;
        }
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: #f9fafb;
            margin: 0;
            padding: 20px;
            color: #333;
        }
        .container {
            max-width: 960px;
            margin: auto;
            background: #fff;
            padding: 30px 40px;
            border-radius: 8px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
        }
        h2 {
            text-align: center;
            margin-bottom: 25px;
            color: #222;
            font-weight: 700;
        }
        .top-bar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 25px;
            flex-wrap: wrap;
            gap: 10px;
        }
        .top-bar label {
            font-weight: 600;
            font-size: 1rem;
            color: #555;
        }
        #search {
            flex-grow: 1;
            max-width: 300px;
            padding: 8px 12px;
            font-size: 1rem;
            border: 2px solid #ddd;
            border-radius: 6px;
        }
        #search:focus {
            border-color: #4CAF50;
            outline: none;
        }
        .add-btn {
            padding: 10px 18px;
            background: #4CAF50;
            color: white;
            font-weight: 600;
            border-radius: 6px;
            text-decoration: none;
            transition: background-color 0.3s ease;
        }
        .add-btn:hover {
            background: #45a049;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        thead {
            background: #4CAF50;
            color: white;
            font-weight: 700;
        }
        th, td {
            padding: 14px 20px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }
        tbody tr:nth-child(even) {
            background-color: #f5f7fa;
        }
        tbody tr:hover {
            background-color: #e8f5e9;
        }
        a.action-link {
            color: #4CAF50;
            font-weight: 600;
            text-decoration: none;
            margin: 0 6px;
        }
        a.action-link:hover {
            color: #388e3c;
        }
        @media(max-width: 600px) {
            .top-bar {
                flex-direction: column;
                align-items: stretch;
            }
            #search {
                max-width: 100%;
            }
            .add-btn {
                width: 100%;
                text-align: center;
            }
            th, td {
                padding: 10px 8px;
                font-size: 0.9rem;
            }
        }
    </style>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<div class="container">
    <h2>INVENTORY DETAILS</h2>

    <div class="top-bar">
        <label for="search">SEARCH:</label>
        <input type="text" id="search" onkeyup="searchProduct()" placeholder="Search by product name..." />
        <a href="add-product.jsp" class="add-btn">+ Add Product</a>
    </div>

    <table id="productTable">
        <thead>
            <tr>
                <th>ID</th><th>Name</th><th>Price (₹)</th><th>Tax (%)</th><th>Action</th>
            </tr>
        </thead>
        <tbody>
        <%
            List<Product> products = (List<Product>) request.getAttribute("productList");
            if (products != null && !products.isEmpty()) {
                for (Product p : products) {
        %>
            <tr>
                <td><%= p.getProduct_id() %></td>
                <td><%= p.getName() %></td>
                <td><%= String.format("%.2f", p.getPrice()) %></td>
                <td><%= p.getTaxPercent() %></td>
                <td>
                    <a href="edit-product.jsp?id=<%= p.getProduct_id() %>" class="action-link">Edit</a> |
                    <a href="#" class="action-link delete-btn" data-id="<%= p.getProduct_id() %>">Delete</a>
                </td>
            </tr>
        <%
                }
            } else {
        %>
            <tr><td colspan="5">No products available</td></tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>

<script>
    function searchProduct() {
        const query = document.getElementById("search").value;
        const xhr = new XMLHttpRequest();
        xhr.open("GET", "SearchProductServlet?query=" + encodeURIComponent(query), true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                document.querySelector("#productTable tbody").innerHTML = xhr.responseText;
            }
        };
        xhr.send();
    }

    $(document).ready(function () {
        $('#productTable').on('click', '.delete-btn', function (e) {
            e.preventDefault();
            if (!confirm("Are you sure you want to delete this product?")) return;

            const button = $(this);
            const id = button.data('id');

            $.ajax({
                url: 'DeleteProductServlet?id=' + id,
                type: 'GET',
                success: function (response) {
                    if (response.trim() === "success") {
                        button.closest('tr').remove();
                    } else {
                        alert("Delete failed. Try again.");
                    }
                },
                error: function () {
                    alert("Error occurred while deleting.");
                }
            });
        });
    });
</script>
</body>
</html>
