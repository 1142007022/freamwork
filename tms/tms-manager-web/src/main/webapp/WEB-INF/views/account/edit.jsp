<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    	<jsp:param value="customer_my" name="param"/>
    </jsp:include>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">修改员工</h3>
                    <div class="box-tools pull-right">
                        <button class="btn btn-primary btn-sm" id="backBtn"><i class="fa fa-arrow-left"></i> 返回列表</button>
                    </div>
                </div>
                <div class="box-body">
                    <form action="/account/edit" id="editForm" method="post">
                        <div class="form-group">
                            <label>姓名</label>
                            <input type="hidden" name="id" value="${account.id}"/>
                            <input type="text" class="form-control" value="${account.username}" name="username">
                        </div>
                        <div class="form-group">
                            <label>联系方式</label>
                            <input type="text" class="form-control" value="${account.mobile}"  name="mobile">
                        </div>
                        <div class="form-group">
                            <label>所属部门</label>
                           
                                <option value=""></option>
                                <c:forEach items="${depts}" var="dept">
                               		
                                	<c:choose>
                                		<c:when test="${fn:containsIgnoreCase(account.deptName,dept.deptName )}">
                                			<label><input name="deptId" type="checkbox" value="${dept.id}" checked />${dept.deptName} </label>
                                		</c:when>
                                		<c:otherwise>
                                			<label><input name="deptId" type="checkbox" value="${dept.id}" />${dept.deptName} </label>
                                		</c:otherwise>
                                	</c:choose>
                                		 <%-- <c:if test="${fn:containsIgnoreCase(account.deptName, '焦作中心')}">
                                		
                                			<label><input name="deptId" type="checkbox" value="${dept.id}" checked />${dept.deptName} </label>
                                			
                                		</c:if> 


                                		
										<c:when test="${dept.deptName eq account.deptName}">
											<label><input name="deptId" type="checkbox" value="${dept.id}" checked />${dept.deptName} </label>
										</c:when>
										 <c:otherwise>
											<label><input name="deptId" type="checkbox" value="${dept.id}" />${dept.deptName} </label>
										</c:otherwise> --%>
									
                                </c:forEach>
                            
                        </div>
                    </form>
                </div>
                <div class="box-footer">
                    <button class="btn btn-primary" id="editBtn">保存</button>
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
	$(function(){
		//var accountId = ${account.id};
		
		$("#backBtn").click(function(){
			location.href = "/dept/add"
		});
		
		
		
 		$("#editBtn").click(function(){
 			//layer.alert(${account.id});
 			$("#editForm").submit();
 		})
 		$("#editForm").validate({
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
					url:'/account/edit',
					type:'post',
					data:$("#editForm").serialize(),
					beforeSend : function(){
						$("#editBtn").text("保修改...").attr("disabled","disabled");
					},
					success : function(data){
						window.location.href="/dept/add";
					},
					error : function(){
						alert("系统异常");
					},
					complete : function(){
						$("#editBtn").text("保存").removeAttr("disabled");
					}
				});
				
			}
 		
 		});
 		
 	})
	
	</script>

</body>
</html>
    