<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>CRM-销售机会进度统计</title>
    <%@ include file="../include/css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">
    <%@include file="../include/header.jsp"%>
    <!-- =============================================== -->

    <jsp:include page="../include/sider.jsp">
        <jsp:param name="param" value="charts_schedule"/>
    </jsp:include>
    <!-- 右侧内容部分 -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">

            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">销售机会进度统计</h3>
                </div>
                <div class="box-body">
                    <div id="bar" style="height: 475px;width: 100%"></div>
                </div>
                <div class="box-body">
                    <div id="bar2" style="height: 475px;width: 100%"></div>
                </div>
            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <%@ include file="../include/footer.jsp"%>

</div>
<!-- ./wrapper -->

<%@include file="../include/js.jsp"%>

<script>
    $(function () {
    	
    	$.ajax({
    		url : "/chart/schedule",
    		type : "post",
    		success : function(json){
    			var countArray = [];
			    var processArray = [];
				var array = json.data;
				for (var i = 0; i < array.length; i++) {
					 var obj = array[i];
					 countArray.push(obj.count);
					 processArray.push(obj.process);
				}
				var bar = echarts.init(document.getElementById("bar"));
		    	var data = [];
		        for(var i = 0;i<processArray.length;i++){
					var obj = {value:countArray[i],name:processArray[i]};
					data.push(obj);
			    } 
		    	
		    	var option = {
		    		    legend: {
		    		        data: processArray
		    		    },
		    		    series: [
		    		        {
		    		            name:'漏斗图',
		    		            type:'funnel',
		    		            left: '10%',
		    		            top: 60,
		    		            bottom: 60,
		    		            width: '80%',
		    		            min: 0,
		    		            max: 100,
		    		            minSize: '0%',
		    		            maxSize: '100%',
		    		            sort: 'descending',
		    		            gap: 2,
		    		            label: {
		    		                normal: {
		    		                    show: true,
		    		                    position: 'inside'
		    		                },
		    		                emphasis: {
		    		                    textStyle: {
		    		                        fontSize: 20
		    		                    }
		    		                }
		    		            },
		    		            labelLine: {
		    		                normal: {
		    		                    length: 10,
		    		                    lineStyle: {
		    		                        width: 1,
		    		                        type: 'solid'
		    		                    }
		    		                }
		    		            },
		    		            itemStyle: {
		    		                normal: {
		    		                    borderColor: '#fff',
		    		                    borderWidth: 1
		    		                }
		    		            },
		    		            data: data
		    		        }
		    		    ]
		    		};

		    	bar.setOption(option);
		    	var data2 = [];
		        for(var i = 0;i<processArray.length;i++){
		        	//alert(processArray[i]);
					var obj = {value:countArray[i],name:processArray[i]};
					data2.push(obj);
			    } 
		    	var option2 = {
		    		    title : {
		    		        text: '销售机会进度统计图',
		    		        subtext: '学渣软件',
		    		        x:'center'
		    		    },
		    		    tooltip : {
		    		        trigger: 'item',
		    		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    		    },
		    		    legend: {
		    		        orient: 'vertical',
		    		        left: 'left',
		    		        data: processArray
		    		    },
		    		    series : [
		    		        {
		    		            name: '进度',
		    		            type: 'pie',
		    		            radius : '55%',
		    		            center: ['50%', '60%'],
		    		            data:data2,
		    		            itemStyle: {
		    		                emphasis: {
		    		                    shadowBlur: 10,
		    		                    shadowOffsetX: 0,
		    		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		    		                }
		    		            }
		    		        }
		    		    ]
		    		};
		    	var bar2 = echarts.init(document.getElementById("bar2"));
		    	bar2.setOption(option2);
    		}
    	})
    	
    
	});
</script>

</body>
</html>
