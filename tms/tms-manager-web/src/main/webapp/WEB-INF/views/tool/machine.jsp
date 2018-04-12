<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>CRM-首页</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <%@ include file="../include/css.jsp"%>
    	<style >
   		/* #jisuanqi{
   			height: 400px;
            width: 400px;
   		} */
   		.sign{
   			color:#EE9A00;
   		}
   		div{
   			padding: 3px 3px ;
   		}
   		.btn{
   		    height: 34px;
            width: 65px;
   			margin : 2px 1px;
   		}
   		#res{
   		    margin : 5px;
            border-radius:5px;
            width: 855px;
   		}
   	</style>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@ include file="../include/header.jsp"%>
   	<jsp:include page="../include/sider.jsp">
   		<jsp:param value="tool_jisuanqi" name="param"/>
   	</jsp:include>

		<div class="content-wrapper">
			<section class="content">
				<!-- Default box -->
				<div class="box">
					<div class="box-header ">
						<h3 class="box-title">计算器</h3>
					</div>
					<div class="box-body ">
						<div class="form-group">
							<input type="text" class="form-control" readonly="0" placeholder="0" id="res" />
						</div>
						<div>
							<table class="table">
								<tr>
									<td><button class="btn btn-default btn-jisuan " id="PI">π</button></td>
									<td><button class="btn btn-default  " id="back">后退</button></td>
									<td><button class="btn btn-default btn-jisuan sign">%</button></td>
									<td><button class="btn btn-default btn-clean">C</button></td>
								</tr>
								<tr>
									<td><button class="btn btn-default btn-jisuan num">7</button></td>
									<td><button class="btn btn-default btn-jisuan num">8</button></td>
									<td><button class="btn btn-default btn-jisuan num">9</button></td>
									<td><button class="btn btn-default btn-jisuan sign">/</button></td>
								</tr>
								<tr>
									<td><button class="btn btn-default btn-jisuan num">4</button></td>
									<td><button class="btn btn-default btn-jisuan num">5</button></td>
									<td><button class="btn btn-default btn-jisuan num">6</button></td>
									<td><button class="btn btn-default btn-jisuan sign">*</button></td>
								</tr>
								<tr>
									<td><button class="btn btn-default btn-jisuan num">1</button></td>
									<td><button class="btn btn-default btn-jisuan num">2</button></td>
									<td><button class="btn btn-default btn-jisuan num">3</button></td>
									<td><button class="btn btn-default btn-jisuan sign">-</button></td>
								</tr>
								<tr>
									<td><button class="btn btn-default btn-jisuan num">0</button></td>
									<td><button class="btn btn-default btn-jisuan  num">.</button></td>
									<td><button class="btn btn-default " id="compute">=</button></td>
									<td><button class="btn btn-default btn-jisuan sign">+</button></td>
								</tr>
							</table>
						</div>
					</div>
			</section>
			
		</div>

		<!-- 底部 -->
 <%@ include file="../include/footer.jsp"%>
</div>
<!-- ./wrapper -->

 <%@ include file="../include/js.jsp"%>
 <script>
		$(function() {

			var equation = 0;
			var num = 0;
			//var equation = 0; 
			$(".num").click(function() {
				num = ($(this).html());
				//$("#res").val("");
				equation = $("#res").val();
				$("#res").val($("#res").val() + num);

			});

			$("#PI").click(function() {
				equation = $("#res").val();
				$("#res").val($("#res").val() + "3.14");
			});

			$(".sign").click(function() {
				equation = $("#res").val();
				$("#res").val($("#res").val() + " " + $(this).html() + " ");

			})
			$(".btn-clean").click(function() {
				history.go(0);
			});
			$("#compute").click(function() {
				$.ajax({
					url : "/jisuanqi",
					type : "post",
					data : {
						"equation" : $("#res").val(),
					},
					success : function(data) {
						$("#res").val(data);
					}
				})
			});

			$("#back").click(function() {
				var value = $("#res").val();
				value = value.substring(0, value.length -1);
				$("#res").val(value);
			})

		})
	</script>
 	
 
</body>
</html>
   