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
<body class="hold-transition skin-blue sidebar-mini">
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
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                售票点缴费
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="box">
                <div class="box-body">
                    <form class="form-inline">
                        <select name="state" class="form-control">
                            <option ${param.payType == '未支付' ? 'selected' : ''} value="未支付">未支付</option>
                            <option ${param.payType == '已支付' ? 'selected' : ''} value="已支付">已支付</option>
                        </select>
                        <button class="btn btn-default">搜索</button>
                    </form>
                </div>
            </div>
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">缴费列表</h3>
                </div>
                <div class="box-body">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>下发时间</th>
                            <th>下发网点</th>
                            <th>起始票号</th>
                            <th>截至票号</th>
                            <th>数量</th>
                            <th>单价</th>
                            <th>总价</th>
                            <th>支付方式</th>
                            <th>收款人</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${empty pageInfo}">
                            <tr>
                                <td colspan="13">暂无记录</td>
                            </tr>
                        </c:if>
                        <c:forEach items="${pageInfo.list}" var="ticketOutLog">
                            <tr>
                                <td><fmt:formatDate value="${ticketOutLog.createTime}"/></td>
                                <c:forEach items="${ticketofficeList}" var="ticketoffice">
                                    <c:if test="${ticketoffice.id == ticketOutLog.ticketofficeId}">
                                        <td>${ticketoffice.name}</td>
                                    </c:if>
                                </c:forEach>

                                <td>${ticketOutLog.startNum}</td>
                                <td>${ticketOutLog.endNum}</td>
                                <td>${ticketOutLog.totalNum}</td>
                                <td>${ticketOutLog.price}</td>

                                <td>${ticketOutLog.toatlPrice}</td>
                                <td>${ticketOutLog.payType}</td>
                                <td>${ticketOutLog.outAccountName}</td>
                                <td>
                                    <span class="label ${ticketOutLog.payType == '未支付' ? 'label-danger' : 'label-success'}">${record.state}</span>
                                </td>
                                <td>${record.financeAccountName}</td>
                                <td>
                                    <c:if test="${ticketOutLog.payType == '未支付'}">
                                        <a href="/finance/pay/${ticketOutLog.id}" class="btn btn-sm btn-success">支付</a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
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
<script src="/static/plugins/jQuery/jquery.twbsPagination.js"></script>
<script>
    $(function () {
        $('#pagination-demo').twbsPagination({
            totalPages: ${pageInfo.pages},
            visiblePages: 5,
            first:'首页',
            last:'末页',
            prev:'上一页',
            next:'下一页',
            href:"?p={{number}}"
        });
    });
</script>
</body>
</html>