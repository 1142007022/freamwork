<%--
  Created by IntelliJ IDEA.
  User: NullPointerException
  Date: 2018/4/10 0010
  Time: 上午 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="../../../static/bootstrap/css/bootstrap.css" rel="stylesheet">
</head>
<body>


    <div class="container">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>商品名称</th>
                <th>价格</th>
                <th>数量</th>
            </tr>
            </thead>
            <tbody>
            <tr class="datarow">
                <td>${pro.productName}</td>
                <td>${pro.price}</td>
                <td>${pro.commentNum}</td>
            </tr>

            </tbody>
        </table>
    </div>


</body>
</html>
