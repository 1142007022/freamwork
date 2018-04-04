<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>CRM-首页</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<%@ include file="../include/css.jsp"%>
<style>
.name-avatar {
	display: inline-block;
	width: 50px;
	height: 50px;
	background-color: #ccc;
	border-radius: 50%;
	text-align: center;
	line-height: 50px;
	font-size: 24px;
	color: #FFF;
}

.table>tbody>tr:hover {
	cursor: pointer;
}

.table>tbody>tr>td {
	vertical-align: middle;
}

.star {
	font-size: 20px;
	color: #ff7400;
}
.pink {
        	 background-color: #FA8072;
        }
</style>

</head>
<body class="hold-transition skin-blue sidebar-mini">
	<!-- Site wrapper -->
	<div class="wrapper">
		<%@ include file="../include/header.jsp"%>

		<jsp:include page="../include/sider.jsp">
			<jsp:param value="customer_my" name="param" />
		</jsp:include>
		<!-- =============================================== -->

		<!-- 右侧内容部分 -->
		<div class="content-wrapper">
			<!-- Main content -->
			<section class="content">

				<!-- Default box -->
				<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title">我的客户</h3>
						<div class="box-tools pull-right">
							<button class="btn btn-success btn-sm" id="addBtn">
								<i class="fa fa-plus"></i> 新增客户
							</button>

						</div>
					</div>
					<div class="box-body no-padding">
						<table class="table table-hover">
							<thead>
								<tr id="tr">
									<th width="80"></th>
									<th>姓名</th>
									<th>职位</th>
									<th>跟进时间</th>
									<th>级别</th>
									<th>联系方式</th>
								</tr>
							<thead>
							<tbody id="tbody">

							</tbody>
						</table>
						<ul id="pagination" class="pagination pagination-lg pull-right"></ul>
					</div>
					<!-- /.box-body -->
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
		$(function() {
			var $pagination = $('#pagination');

			$("#addBtn").click(function() {
				window.location.href = "/customer/my/add";
			});

			var p = 1;
			load(p);

			function load(p) {
				$.ajax({
					url : "/customer/my",
					type : "post",
					data : {
						"p" : p
					},
					success : function(data) {
						var page = data.data;
						$pagination.twbsPagination({
							totalPages : page.totalpage,
							visiblePages : 3,
							first : "首页",
							prev : "上一页",
							next : "下一页",
							last : "末页",
							onPageClick : function(event, page) {
								p = page;
								loadPage(p);
							}
						})

					}
				})
			};
			

			function loadPage(p) {
				$.ajax({
					url : "/customer/my",
					type : "post",
					data : {
						"p" : p
					},
					success : function(data) {
						var page = data.data;
						$("#tbody").html("");
						for (var i = 0; i < page.items.length; i++) {
							var customer = page.items[i];
							var html = "<tr class='messages' rel='"+ customer.id +"'>"
										+ "<td><span class='name-avatar '>" + customer.name.substring(0, 1) + "</span></td>" 
										+ "<td>" + customer.name+ "</td>" 
										+ "<td>" + customer.job + "</td>"
										+ "<td>" + customer.lastConcatTime + "</td>"
										+ "<td class='star'>" + customer.level+ "</td>"
										+ "<td><i class='fa fa-phone'></i>" + customer.mobile + "</td>" 
										+ "</tr>";
								
							$("#tbody").append(html);
							if(customer.sex == '女士'){
								$(".name-avatar").addClass("pink");
							//	$(html).find("span").addClass("pink");
							};		
							
						};
						$(".messages").click(function(){
							
							var custId = $(this).attr("rel");
							//alert(custId);
							window.location.href = "/customer/my/detail?id=" + custId;
				 		});
					}
				})
			};
			
			
		
	 		
			

		})
	</script>
</body>
</html>
