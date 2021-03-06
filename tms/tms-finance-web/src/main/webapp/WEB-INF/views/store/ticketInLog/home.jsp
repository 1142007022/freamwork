<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>TMS - 系统管理 - 年票管理</title>
    <%@include file="../../include/css.jsp" %>
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

    <%@include file="../../include/header.jsp" %>

    <!-- =============================================== -->

    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="ticket_in"/>
    </jsp:include>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                年票管理
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">入库记录列表</h3>
                    <div class="box-tools">
                        <a href="/store/ticket/in/add" class="btn btn-success btn-sm"><i class="fa fa-plus"></i>年票入库</a>

                    </div>

                </div>
                <div class="box-body">
                    <table class="table table-bordered table-hover">
                        <thead>
                        <tr id="roles">
                            <th>入库时间</th>
                            <th>起始票号</th>
                            <th>截至票号</th>
                            <th>状态</th>
                            <th>数量</th>
                            <th>入库人</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach items="${pageInfo.list}" var="ticketInLog">
                            <tr>

                                <td>${ticketInLog.createTime}</td>
                                <td>${ticketInLog.startNum}</td>
                                <td>${ticketInLog.endNum}</td>
                                <td>${ticketInLog.state}</td>
                                <td>${ticketInLog.totalNum}</td>
                                <td>${ticketInLog.inAccountName}</td>

                                <c:choose>
                                    <c:when test="${ticketInLog.state == '已下发'}">
                                        <td>
                                            <strong>已下发不可操作</strong>
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>
                                            <strong><a href="javascript:;" class="del" rel="${ticketInLog.id}">删除</a></strong>
                                            <strong><a href="/store/ticketInLog/update/${ticketInLog.id}">修改</a></strong>
                                        </td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                        </c:forEach>
                        <label ><strong>${pageInfo.total}条数据</strong></label>
                        <ul id="pagination-demo" class="pagination pull-right"></ul>
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

<%@include file="../../include/js.jsp" %>
<script src="/static/plugins/treegrid/js/jquery.treegrid.min.js"></script>
<script src="/static/plugins/treegrid/js/jquery.treegrid.bootstrap3.js"></script>
<script>
    $(function () {

        $('#pagination-demo').twbsPagination({
            totalPages:${pageInfo.pages},
            visiblePages: 5,
            first:'首页',
            last:'末页',
            prev:'上一页',
            next:'下一页',
            href:"/store/ticket/in?p={{number}}"
        });


        $(".del").click(function () {
            var id = $(this).attr("rel");
            layer.confirm("你确定要删除么？",function(){
                $.ajax({
                    url: "/store/ticketInLog/del/" + id,
                    type: "get",
                    success: function (data) {
                        window.location.href='/store/ticket/in';
                    }
                })
            })
        })


    })


</script>
</body>
</html>
