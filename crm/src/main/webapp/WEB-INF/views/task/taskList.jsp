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
   		<jsp:param value="task_List" name="param"/>
   	</jsp:include>
    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">计划任务</h3>

                    <div class="box-tools pull-right">
                        <a href="/task/add" class="btn btn-success btn-sm"><i class="fa fa-plus"></i> 新增任务</a>
                        <c:choose>
                        	<c:when test="${param.show == 'undone' }">
                        		<a href="/task" class="btn btn-primary btn-sm"><i class="fa fa-eye"></i> 显示所有任务</a>
                        	</c:when>
                        	<c:otherwise>
                        		<a href="/task?show=undone" class="btn btn-primary btn-sm"><i class="fa fa-eye"></i> 显示未完成任务</a>
                        	</c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="box-body">

                    <ul class="todo-list">
                    	<c:forEach items="${taskList}" var="task">
                    		<input type="hidden" value="${task.id}" id="taskId"/>
                    		<li class="${task.status == 1 ? 'done' : '' }">
	                            <input type="checkbox" class="taskStatus" rel="${task.id}" ${task.status == 1 ? 'checked' : ''}>
	                            <span class="text">${task.name}</span>
	                             <small class="label ${task.overTime ? 'label-danger' : 'label-success' }"><i class="fa fa-clock-o"></i> ${task.finishTime}</small> 
	                            <div class="tools">
	                                <i class="fa fa-edit" rel="${task.id}"></i>
	                                <i class="fa fa-trash-o" rel="${task.id}"></i>
	                            </div>
	                        </li>
                    	</c:forEach>
                        
                    </ul>
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
			
			/* $(document).delegate(".fa-edit","click",function(){
				alert("hello");
			}) */
			
			$(".fa-edit").click(function(){
				var taskId = $(this).attr("rel");
				window.location.href = "/edit/task?taskId=" + taskId;
			});
			
			$(".fa-trash-o").click(function(){
				var taskId = $(this).attr("rel");
				layer.confirm("你确认要删除么？",function(){
					$.ajax({
						url : "/task/del",
						type : "get",
						data : {
							"taskId" : taskId
						},
						success : function(){
							window.location.href="/task";
						}
					})
				})
			});
			
			$(".taskStatus").click(function(){
				var taskId = $(this).attr("rel");
				var checked = $(this)[0].checked; // 判断是打勾（true）还是取消（false）
				var status = 0;
				if(checked) {
					status = 1;
				} else {
					status = 0;
				}
				
				$.ajax({
					url : "/task/changeStatus",
					type : "get",
					data : {
						"taskId" : taskId,
						 "status" : status
					},
					success : function(data){
						window.location.href = "/task"
					}
				})
			})
			
		})
	</script>
</body>
</html>
    