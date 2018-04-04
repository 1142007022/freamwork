<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<link rel="stylesheet"
	href="/static/plugins/tree/css/metroStyle/metroStyle.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<!-- Site wrapper -->
	<div class="wrapper">

		<%@ include file="../include/header.jsp"%>
		<jsp:include page="../include/sider.jsp">
			<jsp:param value="account" name="param" />
		</jsp:include>
		<!-- =============================================== -->

		<!-- 右侧内容部分 -->
		<div class="content-wrapper">

			<!-- Main content -->
			<section class="content">

				<div class="row">
					<div class="col-md-2">
						<div class="box">
							<div class="box-body">
								<button class="btn btn-primary" id="addDept">添加部门</button>
								<ul id="ztree" class="ztree"></ul>
							</div>
						</div>
					</div>
					<div class="col-md-10">
						<!-- Default box -->
						<div class="box">
							<div class="box-header with-border">
								<h3 class="box-title">员工管理</h3>
								<div class="box-tools pull-right">
									<button type="button" class="btn btn-box-tool" id="addAcc"
										title="Collapse">
										<i class="fa fa-plus"></i> 添加员工
									</button>
								</div>
							</div>
							<div class="box-body">
								<table class="table">
									<thead>
										<tr>
											<th>姓名</th>
											<th>部门</th>
											<th>手机</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody id="body">
									
									</tbody>
								</table>
								<ul class="pagination pull-right" id="pagination"></ul>
							</div>
						</div>
						<!-- /.box -->
					</div>
				</div>

			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<!-- Modal -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">新增员工</h4>
					</div>
					<div class="modal-body">
						<form id="addAccountForm">
							<div class="form-group">
								<label>姓名</label> <input type="text" id="name" class=" form-control"
									name="username">
							</div>
							<div class="form-group">
								<label>手机号码</label> <input type="text" id="password" class="info form-control"
									name="mobile">
							</div>
							<div class="form-group">
								<label>密码(默认000000)</label> <input type="password"
									class="info form-control" name="password" value="000000">
							</div>
							<div class="form-group">
								<label>所属部门</label>
								<div id="checkboxList"></div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-primary" id="saveBtn">保存</button>
					</div>
				</div>
			</div>
		</div>
		<!-- 底部 -->
		<%@ include file="../include/footer.jsp"%>

	</div>
	<!-- ./wrapper -->

	<%@ include file="../include/js.jsp"%>
	<script src="/static/plugins/tree/js/jquery.ztree.all.min.js"></script>

	<script>
		$(function() {
			
			var deptId = null;
			 var $pagination = $('#pagination');
			    var defaultOpts = {
			        totalPages: 20,
			    };
			    $pagination.twbsPagination(defaultOpts);
			    pageload(deptId);
			    
			    
                $(document).delegate(".edit","click",function(){
						
						var accId = $(this).attr("rel");
						//layer.alert(accId);
						location.href = "/account/edit?id=" + accId;
					
			  	});  
			  	
	
			//删除操作   此处使用了时间委托机制
			$(document).delegate(".del", "click", function() {

				layer.confirm('您确定要删除本员工么？', function() {

					var accId = $(".del").attr("rel");
					//layer.alert(accId);
					$.ajax({
						url : '/account/del',
						type : 'post',
						data : {
							'id' : accId
						},
						success : function(data) {
							if (data.state == 'success') {
								layer.alert("删除成功!");
								pageload(deptId)
							}
						}
					});

				})

			});

			function pageload(deptId) {
				$.ajax({
					url : '/account/list.json',
					type : 'get',
					data : {
						"deptId" : deptId
					},
					success : function(data) {
						var page = data.data;
						var totalPages = page.totalpage;
						//   alert(totalPages);
						//  var currentPage = $pagination.twbsPagination('getCurrentPage');
						$pagination.twbsPagination('destroy');
						$pagination.twbsPagination($.extend({}, defaultOpts, {

							startPage : 1,
							totalPages : totalPages,
							visiblePages : 3,
							first : '首页',
							last : '末页',
							prev : '上一页',
							next : '下一页',
							onPageClick : function(event, page) {
								// 自动执行第一页数据
								load(deptId, page);
							}
						}))
					}
				})

			}
			;

			function load(deptId, p) {
				$.ajax({
							url : "/account/list.json",
							type : "get",
							data : {
								"deptId" : deptId,
								"p" : p
							},
							success : function(data) {
								var page = data.data;
								$("#body").html("");
								for (var i = 0; i < page.items.length; i++) {
									var account = page.items[i];
									
										//account = page.items[i + 1];
										var html = "<tr> <td>"
											+ account.username
											+ "</td> <td>"
											+ account.deptName
											+ "</td> <td>"
											+ account.mobile
											+ "</td> <td> <a href='javascript:;' class='del' rel='" + account.id + "'>删除</a>  <a href='javascript:;' class='edit' rel='" + account.id + "'>编辑</a></td></tr>";
									$("#body").append(html);
									
								}
							}

						});
			};

			//点击添加新员工事件
			$("#addAcc").click(
							function() {
								$("#name").val("");
								$("#password").val("");
								$("#checkboxList").html("");
								//	 layer.alert("sb");
								$.ajax({
											url : '/dept/list',
											type : 'get',
											success : function(data) {
												for (var i = 0; i < data.length; i++) {
													var dept = data[i];
													if (dept.pId == 1) {
														var html = '<label class="checkbox-inline"><input type="checkbox" name="deptId" value="'+ dept.id +'">'
																+ dept.deptName
																+ '</label>';
														$("#checkboxList")
																.append(html);

													}
												}
												$('#myModal').modal({
													backdrop : 'static',
													keyboard : false,
												});

											},
											error : function() {
												layer.alert("系统异常提交失败");
											}
										})

							});

			$("#saveBtn").click(function() {
				$("#addAccountForm").submit();
			});

			$("#addAccountForm").validate({
				errorClass : 'text-danger',
				errorElement : 'span',
				rules : {
					userName : {
						required : true
					},
					mobile : {
						required : true
					},
					password : {
						required : true
					},
					deptId : {
						required : true
					}
				},
				messages : {
					userName : {
						required : "请填写员工名称"
					},
					mobile : {
						required : "请填写员工电话"
					},
					password : {
						required : "请填写员工密码"
					},
					deptId : {
						required : "请填写员工部门"
					}
				},
				submitHandler : function() {
					$.ajax({
						url : '/account/add',
						type : 'post',
						data : $("#addAccountForm").serialize(),
						success : function(data) {
							if (data.state == 'success') {
								$('#myModal').modal('hide');
								layer.alert("添加成功");
								load(deptId);
							} else {
								layer.alert(data.message);
							}
						},
						error : function() {
							layer.alert("添加失败，系统异常");
						}

					})
				}

			});

			$("#addDept").click(function() {
				layer.prompt({
					title : "请输入新的部门名称："
				}, function(text, index) {
					layer.close(index);
					$.ajax({
						url : '/dept/add',
						type : 'post',
						data : {
							deptName : text
						},
						success : function(data) {
							layer.msg("部门新增成功");
							var treeObj = $.fn.zTree.getZTreeObj("ztree");
							treeObj.reAsyncChildNodes(null, "refresh");//此处的函数用于设置自动刷新
						},
						error : function() {
							layer.alert("系统故障请稍后重试")
						}

					})
				})
			});

			var setting = {
				data : {
					simpleData : {
						enable : true
					//选择true的话是简单json类型
					},
					key : {
						name : 'deptName',
					}
				},

				async : {
					enable : true,
					url : '/dept/list',
					type : 'get',
					dataFilter : ajaxDataFilter,
				},

				callback : {
					onClick : function onClick(event, treeId, treeNode,
							clickFlag) {

						//layer.alert("这是我即将写的地方");

						deptId = treeNode.id;
						if (deptId == 1) {
							deptId = null;
						}
						pageload(deptId);

					},

				}
			};

			function ajaxDataFilter(treeId, parentNode, responseData) {
				if (responseData) {
					for (var i = 0; i < responseData.length; i++) {
						if (responseData[i].id == 1) {
							responseData[i].open = true;
						}
					}
				}
				return responseData;
			}
			;

			$.fn.zTree.init($("#ztree"), setting);
		});
	</script>
</body>
</html>
