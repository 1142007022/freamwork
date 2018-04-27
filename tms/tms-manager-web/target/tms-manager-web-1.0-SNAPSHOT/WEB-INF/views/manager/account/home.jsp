<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>TMS - 系统管理 - 用户管理</title>
    <%@include file="../../include/css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/treegrid/css/jquery.treegrid.css">
    <style>
   /*     #roles{
            background-color: #3C8DBC;
        }*/
    </style>
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
                帐号管理
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="box no-border">
                <div class="box-body">
                    <form class="form-inline">
                        <input type="text" name="mobile" placeholder="手机号码" class="form-control" value="${param.mobile}">
                        <select name="rolesId" class="form-control">
                            <option value="">所有账号</option>
                            <c:forEach items="${rolesList}" var="roles">
                                <option value="${roles.id}" ${param.relesId == roles.id ? 'selected' : ''}>${roles.rolesName}</option>
                            </c:forEach>
                        </select>
                        <button class="btn btn-default">搜索</button>
                    </form>
                </div>
            </div>
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">帐号列表</h3>
                    <shiro:hasPermission name="account:add">
                        <div class="box-tools">
                            <a href="/manager/account/add" class="btn btn-success btn-sm"><i class="fa fa-plus"></i> 新增用户</a>
                        </div>
                    </shiro:hasPermission>

                </div>
                <div class="box-body">
                    <table class="table table-bordered table-hover">
                        <tbody>
                            <c:forEach items="${accountList}" var="account">
                                <tr id="roles">
                                    <td>用户名称：<strong>${account.accName}</strong></td>
                                    <td>用户手机号：<strong>${account.mobile}</strong></td>
                                    <td>角色:
                                        <c:forEach items="${account.roles}" var="roles">
                                            <strong>${roles.rolesName}</strong>
                                        </c:forEach>
                                    </td>
                                    <td>用户状态：<strong>${account.status}</strong></td>
                                    <shiro:hasPermission name="account:del">
                                        <td><strong><a href="javascript:;" class="del" rel="${account.id}">删除</a></strong></td>
                                    </shiro:hasPermission>
                                    <shiro:hasPermission name="account:update">
                                        <td><strong><a href="/manager/account/update/${account.id}">修改</a></strong></td>
                                    </shiro:hasPermission>


                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
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
        $('.tree').treegrid();
    });

    $(".del").click(function () {
        var id = $(this).attr("rel");
        layer.confirm("你确定要删除么？",function (){
            $.ajax({
                url : "/manager/account/del/"+id,
                type : "get",
                data : {
                    "id": id
                },
                success : function (data) {
                    if(data.state == 'success'){
                        history.go(0);
                    }else{
                        layer.confirm(data.message,function () {
                            history.go(0);
                        })
                    }

                },
                error : function(){
                    alert("系统繁忙")
                }
            })
        })
    })

</script>
</body>
</html>
