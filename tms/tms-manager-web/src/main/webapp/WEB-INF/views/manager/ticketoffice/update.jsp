<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>TMS - 系统管理 - 修改</title>
    <%@include file="../../include/css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../include/header.jsp"%>

    <!-- =============================================== -->

    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="manage_permission"/>
    </jsp:include>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                销售点管理
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">修改销售点</h3>
                    <div class="box-tools">
                        <a href="/manager/ticketoffice" class="btn btn-success btn-sm">返回</a>
                    </div>
                </div>
                <div class="box-body">
                    <form method="post" id="saveForm">
                        <div class="form-group">
                            <label>销售点名称</label>
                            <input type="text" name="name" class="form-control" value="${ticketoffice.name}">
                        </div>
                        <div class="form-group">
                        <label>销售点所在地</label>
                        <input type="text" name="place" class="form-control" value="${ticketoffice.place}">
                    </div>
                        <div class="form-group">
                            <label>营业状态</label>
                            <input type="text" name="status" value="${ticketoffice.status}" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>法人姓名</label>
                            <input type="text" name="accountName" value="${saleAccount.accountName}" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>年票数量</label>
                            <input type="text" name="ticketNum" value="${ticketoffice.ticketNum}" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>法人手机号</label>
                            <input type="text" name="mobile" value="${saleAccount.mobile}" class="form-control">
                        </div>
                    </form>
                </div>
                <div class="box-footer">
                    <button class="btn pull-right btn-primary" id="saveBtn">保存</button>
                </div>
            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
</div>
<!-- ./wrapper -->

<%@include file="../../include/js.jsp"%>
<script>
    $(function () {
        $("#saveBtn").click(function () {
            $("#saveForm").submit();
        });
    })
</script>
</body>
</html>
