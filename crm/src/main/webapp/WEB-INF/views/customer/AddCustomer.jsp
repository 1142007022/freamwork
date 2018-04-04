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
  <%@ include file="../include/sider.jsp"%>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">新增客户</h3>
                    <div class="box-tools pull-right">
                        <button class="btn btn-primary btn-sm"><i class="fa fa-arrow-left"></i> 返回列表</button>
                    </div>
                </div>
                <div class="box-body">
                    <form action="" id="customerAddForm">
                        <div class="form-group">
                            <label>姓名</label>
                            <input type="text" class="form-control" name="name">
                        </div>
						<div class="form-group">
                            <label>性别</label>
                            <div>
                                <label class="radio-inline">
                                    <input type="radio" name="sex" value="先生" checked> 先生
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="sex" value="女士"> 女士
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label>职位</label>
                            <input type="text" class="form-control" name="job">
                        </div>
                        <div class="form-group">
                            <label>联系方式</label>
                            <input type="text" class="form-control" name="mobile">
                        </div>
                        <div class="form-group">
                            <label>地址</label>
                            <input type="text" class="form-control" name="address">
                        </div>
                        <div class="form-group">
                            <label>所属行业</label>
                            <select class="form-control" name="trade">
                                <option value=""></option>
                                <c:forEach items="${trades}" var="trade">
                                	 <option value="${trade}">${trade}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>客户来源</label>
                            <select class="form-control" name="source">
                                <option value=""></option>
                                <c:forEach items="${sources}" var="source">
                                	 <option value="${source}">${source}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>级别</label>
                            <select class="form-control" name="level">
                                <option value=""></option>
                                <option value="★">★</option>
                                <option value="★★">★★</option>
                                <option value="★★★">★★★</option>
                                <option value="★★★★">★★★★</option>
                                <option value="★★★★★">★★★★★</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>备注</label>
                            <input type="text" class="form-control" name="mark">
                        </div>
                    </form>
                </div>
                <div class="box-footer">
                    <button class="btn btn-primary" id="save">保存</button>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

 <%@ include file="../include/sider.jsp"%>

</div>
<!-- ./wrapper -->

 <%@ include file="../include/js.jsp"%>
 <script>
 	$(function(){
 		$("#save").click(function(){
 			$("#customerAddForm").submit();
 		})
 		$("#customerAddForm").validate({
 			errorClass : 'text-danger',
			errorElement : 'span',
			rules : {
				custname :{
					"required" : true
				},
				mobile : {
					"required" : true
				}
			},
			messages :{
				custname :{
					"required" : "请输入客户姓名"
				},
				mobile : {
					"required" : "请输入客户联系方式"
				}
			},
			submitHandler : function(form){
				$.ajax({
					url:'/customer/my/add',
					type:'post',
					data:$("#customerAddForm").serialize(),
					beforeSend : function(){
						$("#save").text("保存中...").attr("disabled","disabled");
					},
					success : function(data){
						if(data.state == 'success') {
							layer.alert("添加新客户成功！")
						   window.location.href="/customer/my";
						}else {
							layer.alert("添加失败请重试");
						}
					},
					error : function(){
						alert("系统异常");
					},
					complete : function(){
						$("#save").text("保存").removeAttr("disabled");
					}
				});
				
			}
 		
 		});
 		
 	})
 
 </script>
</body>
</html>
