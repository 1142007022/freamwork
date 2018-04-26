<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>TMS - 售票系统管理 - 年票销售</title>
    <%@include file="../include/css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/treegrid/css/jquery.treegrid.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../include/header.jsp"%>

    <!-- =============================================== -->

    <jsp:include page="../include/sider.jsp">
        <jsp:param name="menu" value="manager_account"/>
    </jsp:include>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                年票销售
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">年票销售</h3>
                    <div class="box-tools">
                        <a href="/ticket/sale" class="btn btn-success btn-sm">返回</a>
                        <p class="login-box-msg text-danger">${message}</p>
                    </div>
                </div>
                <div class="box-body">
                    <form method="post" id="saveForm">
                        <div class="form-group">
                            <label>顾客身份证号</label>
                            <input type="text" name="customerId" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>顾客姓名</label>
                            <input type="text" name="customerName" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>请选择年票号</label>
                            <select name="ticketNum"class="form-control">
                                <c:forEach items="${ticketStateList}" var="ticketState">
                                    <option value="${ticketState.ticketNum}">${ticketState.ticketNum}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="box-footer">
                            <button class="btn pull-right btn-primary"  id="saveBtn">保存</button>
                        </div>
                    </form>
                </div>

            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
</div>
<!-- ./wrapper -->

<%@include file="../include/js.jsp"%>
<script src="/static/plugins/treegrid/js/jquery.treegrid.min.js"></script>
<script src="/static/plugins/treegrid/js/jquery.treegrid.bootstrap3.js"></script>
<script>
</script>
</body>
</html>
