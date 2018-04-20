<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>TMS - 系统管理 - 售票点管理</title>
    <%@include file="../../include/css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/treegrid/css/jquery.treegrid.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../include/header.jsp"%>

    <!-- =============================================== -->

    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="manager_ticketoffice"/>
    </jsp:include>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                售票点管理
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">售票点列表</h3>
                    <div class="box-tools">
                        <a href="/manager/ticketoffice/add" class="btn btn-success btn-sm"><i class="fa fa-plus"></i> 新增售票点</a>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table tree">
                        <thead>
                        <tr>
                            <th>售票点名称</th>
                            <th>法人姓名</th>
                            <th>法人手机号</th>
                            <th>售票点状态</th>
                            <th>售票点地址</th>
                            <th>年票数量</th>
                            <th>证明文件</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pageInfo.list}" var="ticketoffice">

                                    <tr class="treegrid-${power.id} treegrid-expanded">
                                        <td>${ticketoffice.name}</td>
                                        <td>${ticketoffice.saleAccount.accountName}</td>
                                        <td>${ticketoffice.saleAccount.mobile}</td>
                                        <td>${ticketoffice.status}</td>
                                        <td>${ticketoffice.place}</td>
                                        <td>${ticketoffice.ticketNum}</td>
                                        <td>
                                            <a href="/disk/download?id=${disk.id}&fileName=${disk.name}" class="btn btn-info btn-sm"><i class="fa fa-download"></i> 营业执照</a>
                                        </td>
                                        <td>
                                            <a href="/manager/ticketoffice/update/${ticketoffice.id}">修改</a>
                                            <a href="javascript:;" class="del" rel="${ticketoffice.id}">删除</a>
                                            <a href="javascript:;" class="info" rel="${ticketoffice.id}">详情</a>
                                        </td>

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

<%@include file="../../include/js.jsp"%>
<script src="/static/plugins/jQuery/jquery.twbsPagination.js"></script>
<script src="/static/plugins/treegrid/js/jquery.treegrid.min.js"></script>
<script src="/static/plugins/treegrid/js/jquery.treegrid.bootstrap3.js"></script>
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
            href:"/manager/ticketoffice?p={{number}}"
        });


        $(".info").click(function(){
            var id = $(this).attr("rel");
             window.location.href='/manager/ticketoffice/'+id;
        })


        $(".del").click(function () {
            var id = $(this).attr("rel");
            layer.confirm("你确定要删除么？",function (){
                $.ajax({
                    url : "/manager/ticketoffice/del/"+id,
                    type : "get",

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
    });
</script>
</body>
</html>
