<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>TMS | 售票点缴费</title>
    <%@include file="../../include/css.jsp"%>
</head>
<body class="hold-transition skin-purple sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../include/header.jsp"%>

    <!-- =============================================== -->

    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="finance_ticket"/>
    </jsp:include>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">售票点缴费</h3>
                </div>
                <div class="box-body">
                    <form id="saveForm" method="post">
                        <input type="hidden" name="id" value="${ticketOutLog.id}">
                        <div class="form-group">
                            <label>缴费金额</label>
                            <input type="text" class="form-control" disabled value="${ticketOutLog.toatlPrice}">
                        </div>
                        <div class="form-group">
                            <label>支付方式</label>
                            <select name="payType" class="form-control">
                                <option value="现金">现金</option>
                                <option value="刷卡">刷卡</option>
                            </select>
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
<script src="/static/plugins/layer/layer.js"></script>
<script>
    $(function () {
        $("#saveBtn").click(function () {
            layer.confirm("确定要支付吗?",function (index) {
                layer.close(index);
                $("#saveForm").submit();
            });
        });
    });
</script>
</body>
</html>