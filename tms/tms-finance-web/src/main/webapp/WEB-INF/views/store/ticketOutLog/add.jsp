<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>TMS - 系统管理 - 年票下发</title>
    <%@include file="../../include/css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/treegrid/css/jquery.treegrid.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../include/header.jsp"%>

    <!-- =============================================== -->

    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="manager_account"/>
    </jsp:include>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                年票下发
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">新增下发记录</h3>
                    <div class="box-tools">
                        <a href="/manager/account" class="btn btn-success btn-sm">返回</a>
                        <p class="login-box-msg text-danger">${message}</p>
                    </div>
                </div>
                <div class="box-body">
                    <form method="post" id="saveForm">
                        <div class="form-group">
                            <label>添加日期</label>
                            <input type="text" value="${date}" name="accName" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>起始票号</label>
                            <input type="text" name="startNum" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>结束票号</label>
                            <input type="text" name="endNum" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>单价</label>
                            <input type="text" name="price" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>所属售票点</label>
                            <select name="ticketofficeId"class="form-control">
                                <c:forEach items="${ticketofficeList}" var="ticketoffice">
                                    <option value="${ticketoffice.id}">${ticketoffice.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </form>
                </div>
                <div class="box-footer">
                    <button class="btn pull-right btn-primary" type="button" id="saveBtn">保存</button>
                </div>
            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
</div>
<!-- ./wrapper -->

<%@include file="../../include/js.jsp"%>
<script src="/static/plugins/treegrid/js/jquery.treegrid.min.js"></script>
<script src="/static/plugins/treegrid/js/jquery.treegrid.bootstrap3.js"></script>
<script>
    $(function () {
        $("#saveBtn").click(function () {
            $("#saveForm").submit();
        })
    })
</script>
</body>
</html>
