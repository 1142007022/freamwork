<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>TMS | 售票点详情</title>
    <%@include file="../../include/css.jsp"%>
    <style>
        .photo {
            width: 100%;
            height: 320px;
            border: 2px dashed #ccc;
            margin-top: 20px;
            text-align: center;
            line-height: 320px;
        }
    </style>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../include/header.jsp"%>

    <!-- =============================================== -->

    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="ticket_store"/>
    </jsp:include>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                售票点详情
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">售票点信息</h3>
                    <div class="box-tools">
                        <a href="/manager/ticketoffice/update/${ticketoffice.id}" class="btn btn-primary btn-sm"><i class="fa fa-pencil"></i> 编辑</a>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table">
                        <tbody>
                        <tr>
                            <td class="text-muted">售票点名称</td>
                            <td>${ticketoffice.name}</td>
                            <td class="text-muted">联系人</td>
                            <td>${saleAccount.accountName}</td>
                            <td class="text-muted">联系电话</td>
                            <td>${saleAccount.mobile}</td>
                            <td class="text-muted">年票数量</td>
                            <td>${ticketoffice.ticketNum}</td>
                        </tr>
                        <tr>
                            <td class="text-muted">地址</td>
                            <td colspan="3">${ticketoffice.place}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">关联账号</h3>
                    <div class="box-tools">
                        <a href="javascript:;" rel="${saleAccount.id}" class="btn btn-danger btn-sm"><i class="fa fa-ban"></i> 禁用账号</a>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table">
                        <tr>
                            <td class="text-muted">账号</td>
                            <td>${saleAccount.mobile}</td>
                            <td class="text-muted">状态</td>
                            <td>${ticketoffice.status}</td>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">关联资质</h3>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-6">
                            <div id="picker">联系人身份证照片</div>
                            <div class="photo" id="idCardKey">
                                <img src="http://p7kwh2p7p.bkt.clouddn.com/${ticketoffice.idCardKey}-photo" alt="">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div id="picker2">营业执照照片</div>
                            <div class="photo" id="storePhoto">
                                <img src="http://p7kwh2p7p.bkt.clouddn.com/${ticketoffice.businessLicenceKey}-photo" alt="">
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

<%@include file="../../include/js.jsp"%>
<script>
    $(function () {
    });
</script>
</body>
</html>