<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>TMS - 系统管理 - 年票统计</title>
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
                年票统计
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="box">
                <div class="box-body">

                    <div class="row">
                        <div class="col-md-2">
                            <div class="description-block border-right">
                                <h5 class="description-header">${map.total}</h5>
                                <span class="description-text">总领取数量</span>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="description-block border-right">
                                <h5 class="description-header">${map.now_num}</h5>
                                <span class="description-text">库存数量</span>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="description-block border-right">
                                <h5 class="description-header">${map.sale_num}</h5>
                                <span class="description-text">已销售</span>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="description-block border-right">
                                <h5 class="description-header">${map.miss_num}</h5>
                                <span class="description-text">已挂失</span>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="description-block border-right">
                                <h5 class="description-header">${map.over_data_num}</h5>
                                <span class="description-text">已过期</span>
                            </div>
                        </div>
                    </div>



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
</body>
</html>
