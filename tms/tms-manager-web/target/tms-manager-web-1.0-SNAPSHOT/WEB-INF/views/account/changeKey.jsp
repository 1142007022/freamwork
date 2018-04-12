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
    <%@ include file="../include/css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/select2/select2.min.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <!-- 顶部导航栏部分 -->
    
       <%@ include file="../include/header.jsp"%>
		<jsp:include page="../include/sider.jsp">
			<jsp:param value="account" name="param" />
		</jsp:include>
    <!-- =============================================== -->

    

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">修改密码</h3>
                   
                </div>
                <div class="box-body">
                    <form action="/changeMyMessages" id="changeForm">
                        <div class="form-group">
                            <label>原始密码</label>
                            <input type="password" class="form-control" value="${account.password}">
                        </div>
                        <div class="form-group">
                            <label>新密码</label>
                            <input type="password" class="form-control" name="newPassword" id="newPassword">
                        </div>
                        <div class="form-group">
                            <label>确认密码</label>
                            <input type="password" class="form-control" name="newPassword1" id="newPassword1">
                        </div>
                        
                    </form>
                </div>
                <div class="box-footer">
                    <button class="btn btn-primary" id="saveBtn">保存</button>
                    <button class="btn btn-default" id="backBtn">返回</button>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
	<%@ include file="../include/js.jsp"%>
	
    <!-- 底部 -->
   <%@ include file="../include/footer.jsp"%>
</div>

  <script>
  	$(function(){
  		
  		var accId = ${account.id};
  		
  		$("#saveBtn").click(function(){
  			
  			$("#changeForm").submit();
  			
  			
  		});
  		
  		//layer.alert(accId);
  		$("#backBtn").click(function(){
  			location.href = "/dept/add";
  		})
  		
  		
  		$("#changeForm").validate({
 			errorClass : 'text-danger',
			errorElement : 'span',
			rules : {
				newPassword :{
					"required" : true
				},
				newPassword1 : {
					"required" : true
				}
			},
			messages :{
				newPassword :{
					"required" : "请输入新密码"
				},
				newPassword1 : {
					"required" : "请输入确认密码"
				}
			},
			submitHandler : function(){
				$.ajax({
					url:'/changeMyMessages',
					type:'post',
					data : {
		  				"accId":accId,
		  				"newPassword" : $("#newPassword").val(),
		  				"newPassword1" : $("#newPassword1").val()
		  			},
					beforeSend : function(){
						$("#saveBtn").text("修改中...").attr("disabled","disabled");
					},
					success : function(data){
						if(data.state == 'error'){
							layer.msg(data.message, {icon: 5});
						}else{
							//layer.alert("修改密码成功即将重新登录");
							location.href = "/login";
						}
					},
					error : function(){
						alert("系统异常");
					},
					complete : function(){
						$("#saveBtn").text("保存").removeAttr("disabled");
					}
				});
				
			}
 		
 		})
  		
  		
  		
  		
  		
  		
  		
  		
  		
  		
  		
  		
  		
  		
  		
  		
  		
  		
  		
  		
  		
  		
  		
  		
  		
  		/*$.ajax({
	  			url : '/changeMyMessages',
	  			type : 'post',
	  			data : {
	  				"accId":accId,
	  				"newPassword" : $("#newPassword").val(),
	  				"newPassword1" : $("#newPassword1").val()
	  			},
	  			success : function(data){
	  				if(data.state == 'success'){
	  					location.href = "/login";
	  				} else {
	  					layer.msg("两次密码不一致", {icon: 5});
	  				}
	  				
	  			}
	  		})*/
			
  		
  		
  	})
  
  </script>

</body>
</html>
