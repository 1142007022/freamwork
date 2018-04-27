<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>TMS | 编辑账号</title>
    <%@include file="../../include/css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../include/header.jsp"%>

    <!-- =============================================== -->

    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="manage_account"/>
    </jsp:include>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                账号管理
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">编辑账号</h3>
                </div>
                <div class="box-body">
                    <form method="post" class="saveForm">
                        <input type="hidden" name="id" value="${account.id}">
                        <div class="form-group">
                            <label>账号</label>
                            <input type="text" class="form-control" name="accName" value="${account.accName}">
                        </div>
                        <div class="form-group">
                            <label>手机号码</label>
                            <input type="text" class="form-control" name="mobile" value="${account.mobile}">
                        </div>
                        <div class="form-group">
                            <label>角色</label>
                            <div>
                                <c:forEach items="${rolesList}" var="roles">
                                    <c:set var="flag" value="false"/>
                                    <c:forEach items="${account.roles}" var="accountRoles">
                                        <c:choose>
                                            <c:when test="${roles.rolesName == accountRoles.rolesName}">
                                                <c:set var="flag" value="true"/>
                                            </c:when>
                                        </c:choose>
                                    </c:forEach>
                                    <div class="checkbox-inline">
                                        <input type="checkbox" ${flag ? 'checked' : ''} value="${roles.id}" name="rolesIds"> ${roles.rolesName}
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="box-footer">
                    <button class="btn btn-primary pull-right" id="saveBtn">保存</button>
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
            $(".saveForm").submit();
        });
    });
</script>
</body>
</html>