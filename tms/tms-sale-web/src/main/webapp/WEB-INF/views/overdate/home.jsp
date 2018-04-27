<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>TMS - 系统管理 - 年票续费</title>
    <%@include file="../include/css.jsp"%>
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
                可续费年票列表
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">可续费年票列表</h3>
                        <div class="box-tools">
                            <a href="/ticket/sale/add" class="btn btn-success btn-sm"><i class="fa fa-plus"></i> 年票销售</a>
                        </div>

                </div>
                <div class="box-body">
                    <table class="table table-bordered table-hover">
                        <tbody>
                            <c:forEach items="${pageInfo.list}" var="ticketState">
                                <tr id="roles">
                                    <td>票号：<strong>${ticketState.ticketNum}</strong></td>
                                    <c:forEach items="${ticketList}" var="ticket">
                                        <c:if test="${ticket.num == ticketState.ticketNum}">
                                            <td>有效期至：<strong>${ticket.overDataTime}</strong></td>
                                            <c:forEach items="${ticketofficeList}" var="ticketoffice">
                                                <c:if test="${ticketoffice.id == ticket.ticketofficeId}">
                                                    <td>销售网点：<strong>${ticketoffice.name}</strong></td>
                                                </c:if>
                                            </c:forEach>
                                        </c:if>
                                    </c:forEach>
                                    <td><a href="javascript:;" rel="${ticketState.ticketNum}" class="overdate"><strong >续费</strong></a></td>
                                </tr>
                            </c:forEach>
                            <label ><strong>${pageInfo.total}条数据</strong></label>

                        </tbody>
                        <ul id="pagination-demo" class="pagination pull-right"></ul>
                    </table>
                </div>
            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
</div>
<!-- ./wrapper -->

<%@include file="../include/js.jsp"%>
<script src="../../../static/plugins/jQuery/jquery.twbsPagination.min.js"></script>
<script src="../../../static/plugins/treegrid/js/jquery.treegrid.min.js"></script>
<script src="../../../static/plugins/treegrid/js/jquery.treegrid.bootstrap3.js"></script>
<script>
        $(function () {
        $('.tree').treegrid();

            $('#pagination-demo').twbsPagination({
                totalPages:${pageInfo.pages},
                visiblePages: 5,
                first:'首页',
                last:'末页',
                prev:'上一页',
                next:'下一页',
                href:"/ticket/overdate?p={{number}}"
            });

            $(".overdate").click(function(){
                var ticketNum = $(this).attr("rel");
                layer.confirm("确定要给该年票续费一年么？",function(){
                    layer.close();
                    $.ajax({
                        url : "/ticket/renew/" + ticketNum,
                        type : "get",
                        success : function () {
                            window.location.href='/ticket/overdate';
                        }
                    })
                })
            })


    });
</script>
</body>
</html>
