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
    <link rel="stylesheet" href="/static/plugins/uploader/webuploader.css">
    <%@ include file="../include/css.jsp"%>
    <style>
        tr{
            height: 50px;
            line-height: 50px;
        }
        .table>tbody>tr>td{
            vertical-align: middle;
        }
        .file_icon {
            font-size: 30px;
        }
        .table>tbody>tr:hover{
            cursor: pointer;
        }
        .webuploader-container {
            display: inline-block;
        }
        .webuploader-pick {
            padding: 5px 10px;
            overflow: visible;
            font-size: 12px;
            line-height:1.5;
            font-weight: 400;
        }
    </style>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
	<div class="wrapper">
		<%@ include file="../include/header.jsp"%>
		<jsp:include page="../include/sider.jsp">
			<jsp:param value="disk_home" name="param" />
		</jsp:include>
		<!-- =============================================== -->

		<!-- 右侧内容部分 -->
		<div class="content-wrapper">

			<!-- Main content -->
			<section class="content">

				<!-- Default box -->
				<div class="box">
					<div class="box-header with-border">
						<c:choose>
							<c:when test="${empty disk}">
								<h3 class="box-title">公司网盘</h3>
							</c:when>
							<c:otherwise>
								<h3 class="box-title">${disk.name}</h3>
							</c:otherwise>
						</c:choose>


						<div class="box-tools pull-right">
							<c:if test="${not empty disk}">
								<a href="/disk/list?pid=${disk.pId}" class="btn btn-default btn-sm"><i class="fa fa-arrow-left"></i>返回上一级</a>
							</c:if>
							
							<c:choose>
	                    		<c:when test="${disk.type == 'file' }">
									<a href="/disk/download?id=${disk.id}&fileName=${disk.name}" class="btn btn-info btn-sm"><i class="fa fa-download"></i> 下载</a>                    			
	                    		</c:when>
                    		<c:otherwise>
			                    	<a id="picker"><i class="btn-sm fa fa-upload">上传文件</i> </a>
			                        <button id="addFolderBtn" class="btn btn-success btn-sm"><i class="fa fa-plus"></i> 新建文件夹</button>
                    		</c:otherwise>	
                    		</c:choose>
                    		
                    		
							<!-- <button class="btn btn-primary btn-sm">
								<i class="fa fa-upload"></i> 上传文件
							</button>
							<button id="addFolderBtn" class="btn btn-success btn-sm">
								<i class="fa fa-plus"></i> 新建文件夹
							</button> -->
							
							
						</div>
					</div>
					
					
					<div class="box-body no-padding">

						<table class="table table-hover">
							<c:choose>
								<c:when test="${disk.type == 'file' }">
                    			<tr>
                    				<td colspan="4">
                    					<c:choose>
                    						<c:when test="${disk.name.endsWith('.pdf') or disk.name.endsWith('.jpg') or disk.name.endsWith('.gif') or disk.name.endsWith('.jpeg') or disk.name.endsWith('.png')}">
				                    			<a href="/disk/download?id=${disk.id}" target="_blank" class="btn btn-default btn-sm"><i class="fa fa-search"></i> 预览</a>
				                    			<a href="/disk/download?id=${disk.id}&fileName=${disk.name}" class="btn btn-info btn-sm"><i class="fa fa-download"></i> 下载</a>          
                    						</c:when>
                    						<c:otherwise>
				                    			<a href="/disk/download?id=${disk.id}&fileName=${disk.name}" class="btn btn-info btn-sm"><i class="fa fa-download"></i> 下载</a>          
                    						</c:otherwise>
                    					
                    					</c:choose>
                    				</td>
                    			</tr>
                    			</c:when>
								<c:otherwise>
									<c:if test="${empty diskList}">
		                    		<tr><td colspan="5">暂无内容</td></tr>
		                    	</c:if>
		                    	<c:forEach items="${diskList}" var="disk">
			                        <tr class="tr" rel="${disk.id }">
			                        	<c:choose>
			                        		<c:when test="${disk.type == 'dir' }">
					                            <td width="50" class="file_icon td" rel="${disk.id }"><i
												class="fa fa-folder-o"></i></td>
			                        		</c:when>
			                        		<c:otherwise>
					                            <td width="50" class="file_icon td" rel="${disk.id }"><i
												class="fa fa-file-o"></i></td>
			                        		</c:otherwise>
			                        	</c:choose>
			                            <td class='td' rel="${disk.id }">${disk.name}</td>
			                            <td class='td' rel="${disk.id }"><fmt:formatDate value="${disk.updateTime}" pattern="yyyy年MM月dd日"/></td>
			                            <td width="100" class='td' rel="${disk.id }">${disk.fileSize}</td>
			                            <td width="150" >
			                                <div class="btn-group">
			                                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
			                                        <i class="fa fa-ellipsis-h"></i>
			                                    </button>
			                                    <ul class="dropdown-menu">
				                                    <c:choose>
						                        		<c:when test="${disk.type == 'dir' }">
								                            <li><a href="/disk/list?pid=${disk.id}">打开</a></li>
						                        		</c:when>
						                        		<c:otherwise>
								                           <li><a href="/disk/download?id=${disk.id}&fileName=${disk.name}">下载</a></li>
						                        		</c:otherwise>
						                        	</c:choose>
			                                        
			                                        <c:if test="${isAdmin == 'yes' }">
				                                        <li><a href="#" class="reName" rel="${disk.id}">重命名</a></li>
			                                        	<li><a href="#" class="del" rel="${disk.id}">删除</a></li>
			                                        </c:if>
			                                    </ul>
			                                </div>
			                            </td>
			                        </tr>
		                    	</c:forEach>
								</c:otherwise>
							</c:choose>
							
							
							<%-- <c:forEach items="${diskList}" var="disk">
								<tr class="">
									<c:choose>
										<c:when test="${disk.type == 'dir' }">
											<td width="50" class="file_icon td" rel="${disk.id }"><i
												class="fa fa-folder-o"></i></td>
										</c:when>
										<c:otherwise>
											<td width="50" class="file_icon td" rel="${disk.id }"><i
												class="fa fa-file-o"></i></td>
										</c:otherwise>
									</c:choose>
									<td class='td' rel="${disk.id }">${disk.name}</td>
									<td class='td' rel="${disk.id }"><fmt:formatDate
											value="${disk.updateTime}" pattern="yyyy年MM月dd日 " /></td>
									<td class='td' rel="${disk.id }" width="100">${disk.fileSize}</td>
									<td width="150">
										<div class="btn-group">
											<button type="button" class="btn btn-default dropdown-toggle"
												data-toggle="dropdown">
												<i class="fa fa-ellipsis-h"></i>
											</button>
											<ul class="dropdown-menu">
												<c:choose>
													<c:when test="${disk.type == 'dir' }">
														<li><a href="/disk/list?pid=${disk.id}">打开</a></li>
													</c:when>
													<c:otherwise>
														<li><a href="">下载</a></li>
													</c:otherwise>
												</c:choose>
												<li><a href="#" class="reName" rel="${disk.id}">重命名</a></li>
												<li><a href="#" class="del" rel="${disk.id}">删除</a></li>
											</ul>
										</div>
									</td>
								</tr>
							</c:forEach> --%>

						</table>


					</div>
				</div>
			</section>
		</div>
		<%@ include file="../include/footer.jsp"%>
	</div>
	<%@ include file="../include/js.jsp"%>
<script type="text/javascript" src="/static/plugins/uploader/webuploader.js"></script>
 <script>
		$(function() {
			var pid = "${disk == null? '0' : disk.id}";
			
			var fileMd5;
		 	WebUploader.Uploader.register({
			    'before-send-file': 'preupload'
			}, {
			    preupload: function( file ) {
			        var me = this,
			            owner = this.owner,
			            server = me.options.server,
			            deferred = WebUploader.Deferred();
		
			        	owner.md5File(file)
		
			            // 如果读取出错了，则通过reject告诉webuploader文件上传出错。
			            .fail(function() {
			                deferred.reject();
			            })
		
			            // md5值计算完成
			            .then(function( md5 ) {
			            	fileMd5 = md5;
		                	$.ajax({
		    					url:'/disk/md5',
		    					type:'get',
		    					data:{"md5":md5,
		    						"pid":pid,
		    						"name":file.name
		    					},
		    					success: function(json) {
		                            // 如果验证已经上传过
		                            if (json.state == "error" ) {
		                                owner.skipFile( file );
		                                console.log('文件重复，已跳过');
		                            } else {
								        me.options.formData.fileMd5 = fileMd5;
		                            }
		                            // webuploader接着往下走。
		                            deferred.resolve();
		                        }
		                	});
			            });
			        return deferred.promise();
			    }
			});
			
			
			$("#addFolderBtn").click(function() {
				layer.prompt({
					title : "请输入文件夹名称:"
				}, function(text, index) {
					layer.close(index);
					$.ajax({
						url : "/disk/add",
						type : "post",
						data : {
							"name" : text,
							"pid" : pid
						},
						success : function(data) {
							layer.msg("添加成功");
							history.go(0);
						}
					})
				});
			});

			$(".td").click(function() {
				var pid = $(this).attr("rel");
				window.location.href = "/disk/list?pid=" + pid;
			});

			$(".del").click(function() {
				var id = $(this).attr("rel");
				layer.confirm("你确定要删除么？", function(index) {
					/* alert(id); */
					layer.close(index);
					$.ajax({
						url : "/disk/del",
						type : "get",
						data : {
							"id" : id
						},
						success : function() {
							history.go(0)
						}
					})
				})
			});

			$(".reName").click(function() {
				var id = $(this).attr("rel");
				layer.prompt({
					title : "请输入新文件夹/文件的名称:"
				}, function(text, index) {
					layer.close(index);
					$.ajax({
						url : "/disk/rename",
						type : "get",
						data : {
							"id" : id,
							"name" : text
						},
						success : function() {
							history.go(0)
						}
					})

				})
			});
			
	        var uploader = WebUploader.create({
	            pick:"#picker",
	            swf:'/static/plugins/uploader/Uploader.swf',
	            server:'/disk/upload', 
	            auto: true, 
	            fileVal:'file', 
	            formData:{
	                "pid":pid
	            } //发送请求给服务器的额外数据
	        });
	        
	        uploader.on('uploadStart',function (file) {
	            loadIndex = layer.load(2); // 2 :加载的样式
	        });
	        
	        //上传成功
	        uploader.on('uploadSuccess',function (file,resp) {
	            if(resp.state == 'success') {
	                layer.msg("文件上传成功");
	                history.go(0);
	            }
	        });

		})
	</script>
</body>
</html>
