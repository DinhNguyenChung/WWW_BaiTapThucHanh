<%@ page import="vn.edu.iuh.fit.backend.repositories.entites.Product" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
            font-size: 24px;
        }
        .container {
            width: 80%;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .table-container {
            width: 100%;
            max-width: 800px;
            background-color: white;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
        }

        .table-scroll {
            display: block;
            height: 300px; /* Cố định chiều cao */
            overflow-y: auto; /* Thanh cuộn dọc */
            width: 100%;
        }

        table {
            border-collapse: collapse;
            width: 80%;
            margin: 0 auto;
            background-color: white;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            overflow: hidden;
        }

        th, td {
            padding: 12px 15px;
            text-align: center;
            border: 1px solid #ddd;
        }

        thead {
            background-color: #4CAF50;
            color: white;
        }

        tbody tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tbody tr:hover {
            background-color: #f1f1f1;
        }

        th {
            font-weight: bold;
        }

        td {
            font-size: 16px;
        }

        tbody td[colspan="4"] {
            text-align: center;
            color: #999;
        }

        /* Styling for table borders */
        table, th, td {
            border: 1px solid #ddd;
        }

        /* Add a slight hover effect for rows */
        tbody tr:hover {
            background-color: #e0f7fa;
        }
        input[type="text"] {
            padding: 8px;
            border-radius: 5px;
            border: 1px solid #ccc;
            font-size: 16px;
            width: 60%;
            margin-bottom: 10px;
        }
        input[type="submit"] {
            padding: 8px;
            border-radius: 5px;
            border: 0px solid #ccc;
            font-size: 16px;
            margin-bottom: 10px;
            cursor: pointer;
            background-color: rgb(108, 121, 245);
            /* border: 1px solid #000; */
            color: white;
        }
    </style>
</head>
<body>
<h2>List of Products</h2>
<form action="controller" method="get">
    <div style="display: flex; flex-direction: row;" >
       <div>
           <p>Search:</p>
           <input type="hidden" name="action" value="search"> <br/>
           <input type="text" name="search" id="search" placeholder="Search products...">
       </div>
        <div style="display: flex; align-items: center; ">
            <input type="submit" value="Search" style="height: 40px; margin: 10px;">
            <input type="submit" value="Delete" style="height: 40px; margin: 10px;"   >
            <input type="submit" value="Update" style="height: 40px; margin: 10px;"   >
        </div>
    </div>
</form>
<div style=" display: flex;flex-direction: row; ">
    <!-- Thông tin
      -->
    <input id="productId" type="text" disabled="disabled" value="id" >
    <input id="productName" type="text" disabled="disabled" value="name" >
    <input id="productDescription" type="text" disabled="disabled" value="des">
    <input id="productImage" type="text" disabled="disabled" value="img">
</div>
<div class="table-container">
    <table border="1" >
        <thead>
        <tr>
            <th>Product ID</th>
            <th>Full Name</th>
            <th>Description</th>
            <th>Image</th>
        </tr>
        </thead>

    </table>
    <div class="table-scroll">
        <table>
            <tbody id="productTable">
            <%
                List<Product> products = (List<Product>) session.getAttribute("products");
                if (products != null && !products.isEmpty()) {
                    for (Product product1 : products) {
            %>
            <tr data-id=<%= product1.getId() %> data-name= <%= product1.getName() %>
                data-description=<%= product1.getDescription() %> data-img=<%= product1.getImgPath() %>>
                <td><%= product1.getId() %></td>
                <td><%= product1.getName() %></td>
                <td><%= product1.getDescription() %></td>
                <td>
                    <img src=<%= product1.getImgPath() %>, alt=<%= product1.getImgPath() %>></td>
            </tr>
            <%
                }
            } else {
                String err = session.getAttribute("errorMessage").toString();
                if (err != null) {
            %>
            <tr>
                <td colspan="4" style="color: #fdfdfd"><%=err%></td>
            </tr>
            <%
                        session.removeAttribute("errorMessage"); // Xóa thông báo sau khi hiển thị
                    }
                }
            %>
            </tbody>
        </table>
    </div>
</div>
<script>
    // Lấy tất cả các hàng của bảng
    const rows = document.querySelectorAll('#productTable tr');

    // Xử lý khi nhấn vào hàng
    rows.forEach(row => {
        row.addEventListener('click', function() {
            const productId = this.getAttribute('data-id');
            const productName = this.getAttribute('data-name');
            const productDescription = this.getAttribute('data-description');
            const productImage = this.getAttribute('data-img');

            // Cập nhật các ô input
            document.getElementById('productId').value = productId;
            document.getElementById('productName').value = productName;
            document.getElementById('productDescription').value = productDescription;
            document.getElementById('productImage').value = productImage;
        });
    });
</script>
</body>
</html>
