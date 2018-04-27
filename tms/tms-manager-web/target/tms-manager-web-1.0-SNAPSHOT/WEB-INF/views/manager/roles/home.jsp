<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>TMS - 系统管理 - 角色管理</title>
    <%@include file="../../include/css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/treegrid/css/jquery.treegrid.css">
    <style>
        #roles{
            background-color:#ECF0F5;
        }
        .td-left{
            border-radius: 7px 0px 0px 7px;
        }
        .td-right{
            border-radius: 0px 7px 7px 0px;
        }
    </style>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../include/header.jsp"%>

    <!-- =============================================== -->

    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="manager_roles"/>
    </jsp:include>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                角色管理
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">角色列表</h3>
                    <shiro:hasPermission name="roles:add">
                        <div class="box-tools">
                            <a href="/manager/roles/add" class="btn btn-success btn-sm"><i class="fa fa-plus"></i> 新增角色</a>
                        </div>
                    </shiro:hasPermission>

                </div>
                <div class="box-body">
                    <table class="table tree">
                        <tbody>
                        <c:forEach items="${rolesList}" var="roles">
                            <tr id="roles" >
                                <td class="td-left">角色名称：<strong>${roles.rolesName}</strong></td>
                                <td>
                                    <shiro:hasPermission name="roles:del">
                                        <a href="javascript:;" class="del" rel="${roles.id}">删除</a>
                                    </shiro:hasPermission>
                                    <shiro:hasPermission name="roles:update">
                                        <a href="/manager/roles/update/${roles.id}">修改</a>
                                    </shiro:hasPermission>


                                </td>
                            </tr>
                            <tr>
                                <td class="td-right">
                                    <c:forEach items="${roles.power}" var="power">
                                        <i class="fa fa-circle"></i> ${power.powerName}
                                    </c:forEach>
                                </td>
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
                url : "/manager/roles/del/"+id,
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
