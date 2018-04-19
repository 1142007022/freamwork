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
                权限管理
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">修改权限</h3>
                    <div class="box-tools">
                        <a href="/manager/power" class="btn btn-success btn-sm">返回</a>
                    </div>
                </div>
                <div class="box-body">
                    <form method="post" id="saveForm">
                        <div class="form-group">
                            <label>权限名称</label>
                            <input type="text" name="powerName" class="form-control" value="${power.powerName}">
                        </div>
                        <div class="form-group">
                        <label>权限代号</label>
                        <input type="text" name="powerCode" class="form-control" value="${power.powerCode}">
                    </div>
                        <div class="form-group">
                            <label>权限路径(URL)</label>
                            <input type="text" name="url" class="form-control" value="${power.url}">
                        </div>
                        <div class="form-group">
                            <label>权限类型</label>
                            <select name="powerType" class="form-control">
                                <option value="${power.powerType}">${power.powerType}</option>
                                <c:if test="${power.powerType  == '菜单'}">
                                    <option value="按钮">按钮</option>
                                </c:if>
                                <c:if test="${power.powerType == '按钮'}">
                                    <option value="菜单">菜单</option>
                                </c:if>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>父权限</label>
                            <select name="parentId" class="form-control">
                                <option value="0">顶级菜单</option>
                                <c:forEach items="${powerList}" var="power1">
                                    <option value="${power1.id}" ${power1.id == power.parentId ? 'selected' : ''}>${power1.powerName}</option>
                                </c:forEach>
                            </select>
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
