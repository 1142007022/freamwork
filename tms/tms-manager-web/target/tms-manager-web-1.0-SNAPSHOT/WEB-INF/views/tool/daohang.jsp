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
    	a{
    		padding:40px;
    		
    	}
    	.itiltright{
    		border-radius: 20px;
    	}
   	#boot{
   		background-image : url(static/pictures/1.png)
   	}
	#javafx{
		background-image : url(static/pictures/javafx.png)
	}
	#maven{
		background-image : url(static/pictures/maven.png)
	}
	#echars{
		background-image : url(static/pictures/echars.png)
	}
	#jquery{
		background-image : url(static/pictures/jquery.png)
	}
	#apache{
		background-image : url(static/pictures/apache.png)
	}
	#github{
		background-image : url(static/pictures/github.png)
	}
	#zhihu{
		background-image : url(static/pictures/zhihu.png)
	}
   	</style>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<%@ include file="../include/header.jsp"%>
<jsp:include page="../include/sider.jsp">
   	<jsp:param value="tool_daohang" name="param"/>
</jsp:include>
	

<div style="width:1900px; margin:30px 0px 0px 280px;">

	
	<div class="demo">
		<a href="https://v3.bootcss.com/css/" target="_blank"><canvas id='boot' class="itiltright" width="150" height="200" onclick="null" style="height: 220px; width: 180px; visibility: visible;background-color:red"></canvas></a>
		<a href="http://www.javafxchina.net/main/" target="_blank"><canvas id="javafx" class="itiltright" width="150" height="200" onclick="null" style="height: 220px; width: 180px; visibility: visible;background-color:red"></canvas></a>
		<a href="http://mavenrepository.com/artifact/javax.servlet/javax.servlet-api/3.1.0" target="_blank"><canvas id="maven" class="itiltright" width="150" height="200" onclick="null" style="height: 220px; width: 180px; visibility: visible;background-color:red"></canvas></a>
		<a href="http://echarts.baidu.com/" target="_blank"><canvas id="echars" class="itiltright" width="150" height="200" onclick="null" style="height: 220px; width: 180px; visibility: visible;background-color:red"></canvas></a>
	</div>
		<div class="demo">
		<a href="http://jquery.com/" target="_blank"><canvas id="jquery" class="itiltright" width="150" height="200" onclick="null" style="height: 220px; width: 180px; visibility: visible;background-color:red"></canvas></a>
		<a href="http://www.apache.org/" target="_blank"><canvas id="apache" class="itiltright" width="150" height="200" onclick="null" style="height: 220px; width: 180px; visibility: visible;background-color:red"></canvas></a>
		<a href="https://github.com/" target="_blank"><canvas id="github" class="itiltright" width="150" height="200" onclick="null" style="height: 220px; width: 180px; visibility: visible;background-color:red"></canvas></a>
		<a href="https://www.zhihu.com/signup?next=%2F" target="_blank"><canvas id="zhihu" class="itiltright" width="150" height="200" onclick="null" style="height: 220px; width: 180px; visibility: visible;background-color:red"></canvas></a>
	</div>
	
</div>

 <%@ include file="../include/footer.jsp"%>
 <%@ include file="../include/js.jsp"%>
 <script>

</script>
 	
 
</body>
</html>
