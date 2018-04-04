<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>CRM-首页</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <%@ include file="../include/css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@ include file="../include/header.jsp"%>
   	<jsp:include page="../include/sider.jsp">
   		<jsp:param value="work_record_my" name="param"/>
   	</jsp:include>
   
    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">我的销售机会</h3>

                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool">
                            <i class="fa fa-plus"></i> 添加机会
                        </button>
                    </div>
                </div>
                <div class="box-body">
                    <form action="" id="saleAddForm">
                        <div class="form-group">
                            <label>机会名称</label>
                            <input type="text" class="form-control" name="salename">
                        </div>
                        <div class="form-group">
                            <label>关联客户</label>
                            <select name="custId" id="" class="form-control">
                                <c:forEach items="${customerList}" var="cust" >
                                	<option value="${cust.id}">${cust.name}(${cust.mobile}})</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>机会价值</label>
                            <input type="text" class="form-control" name="worth">
                        </div>
                        <div class="form-group">
                            <label>当前进度</label>
                            <select name="process" class="form-control">
                                <c:forEach items="${process}" var="pro" >
                                	<option value="${pro}">${pro}</option>
                                </c:forEach>
                                
                            </select>
                        </div>
                        <div class="form-group">
                            <label>详细内容</label>
                            <textarea class="form-control" name="content"></textarea>
                        </div>
                    </form>
                </div>
                <!-- /.box-body -->
                <div class="box-footer">
                    <button class="btn btn-primary" id="saveBtn">保存</button>
                    <button class="btn btn-btn-default" id="backBtn">返回</button>
                </div>
            </div>
            <!-- /.box -->

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <!-- 底部 -->
 <%@ include file="../include/footer.jsp"%>
</div>
<!-- ./wrapper -->

 <%@ include file="../include/js.jsp"%>
 <script>
	$(function(){
		$("#saveBtn").click(function(){
 			$("#saleAddForm").submit();
 		});
		
		$("#backBtn").click(function(){
			location.href = "/sale/my/list";
		});
		
 		$("#saleAddForm").validate({
 			errorClass : 'text-danger',
			errorElement : 'span',
			rules : {
				salename :{
					"required" : true
				},
				worth : {
					"required" : true,
					"number" : true,
					"min" : 1
				}
			},
			messages :{
				salename :{
					"required" : "请输入机会名称"
				},
				worth : {
					"required" : "请输入机会价值",
					"number" : "请输入正确的金额",
					"min" : "请输入合适的金额"
				}
			},
			submitHandler : function(form){
				$.ajax({
					url:'/sale/add',
					type:'post',
					data:$("#saleAddForm").serialize(),
					beforeSend : function(){
						$("#saveBtn").text("保存中...").attr("disabled","disabled");
					},
					success : function(data){
						window.location.href="/sale/my/list";
					},
					error : function(){
						alert("系统异常");
					},
					complete : function(){
						$("#saveBtn").text("保存").removeAttr("disabled");
					}
				});
				
			}
 		
 		});
 		
	}) 
 </script>
 	
 
</body>
</html>
    