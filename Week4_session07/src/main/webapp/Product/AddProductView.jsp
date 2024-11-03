<%--&lt;%&ndash;--%>
<%--  Created by IntelliJ IDEA.--%>
<%--  User: DELL--%>
<%--  Date: 21/09/2024--%>
<%--  Time: 19:51--%>
<%--  To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Add Product</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--&lt;%&ndash;    <form action="controller" method="get">&ndash;%&gt;--%>
<%--&lt;%&ndash;        <input type="hidden" name="action" value="add"> <br/>&ndash;%&gt;--%>
<%--&lt;%&ndash;        <input name="name"  /> <br/>&ndash;%&gt;--%>
<%--&lt;%&ndash;        <input name="desc"  /> <br/>&ndash;%&gt;--%>
<%--&lt;%&ndash;        <input name="img"  /> <br/>&ndash;%&gt;--%>
<%--&lt;%&ndash;        <input type="submit" value="insert"/>&ndash;%&gt;--%>
<%--&lt;%&ndash;        <input type="reset"/>&ndash;%&gt;--%>
<%--&lt;%&ndash;    </form>&ndash;%&gt;--%>
<%--        <form action="controller" method="get">--%>
<%--            <input type="hidden" name="action" value="add"> <br/>--%>
<%--            Name: <input name="name" /> <br/>--%>
<%--            Description: <input name="desc" /> <br/>--%>
<%--            Image Path: <input name="img" /> <br/>--%>
<%--            <input type="submit" value="insert"/>--%>
<%--            <input type="reset"/>--%>
<%--        </form>--%>

<%--</body>--%>
<%--</html>--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-color: #f0f0f0;
        }

        h3 {
            text-align: center;
            margin-bottom: 20px;
            font-size: 24px;
        }

        form {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            background-color: aqua;
            padding-left: 80px;
            padding-right: 80px;
            padding-top: 50px;
            padding-bottom: 50px;
            border: 1px solid black;
            border-radius: 20px;
            width: 80%;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        form div {
            display: flex;
            flex-direction: column;
            width: 80%;
            margin-bottom: 15px;
        }

        form div p {
            margin: 0;
            font-size: 16px;
            margin-bottom: 5px;
        }

        form div input[type="text"],
        form div input[type="submit"],
        form div input[type="reset"] {
            padding: 8px;
            border-radius: 5px;
            border: 1px solid #ccc;
            font-size: 16px;
            width: 100%;
        }

        form div input[type="submit"],
        form div input[type="reset"] {
            width: 100px;
            margin: 10px;
            cursor: pointer;
            background-color: white;
            border: 1px solid #000;
            width: 100%;
        }

        form div:last-child {
            display: flex;
            justify-content: space-around;
            width: 50%;
        }
    </style>
</head>
<body>
<div>
    <h3>ADD NEW PRODUCT</h3>
    <form action="controller" method="get">
        <input type="hidden" name="action" value="add"> <br/>
        <div>
            <p>Name:</p>
            <input type="text" name="name" />
        </div>
        <div>
            <p>Description:</p>
            <input type="text" name="desc" />
        </div>
        <div>
            <p>Image Path:</p>
            <input type="text" name="img" />
        </div>
        <div>
            <input type="submit" value="Insert"/>
            <input type="reset" value="Reset"/>
        </div>
    </form>
</div>
</body>
</html>
