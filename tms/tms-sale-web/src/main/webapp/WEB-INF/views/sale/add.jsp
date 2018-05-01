<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>TMS - 售票系统管理 - 年票销售</title>
    <%@include file="../include/css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/treegrid/css/jquery.treegrid.css">
</head>
<style>
    .photo {
        width: 100%;
        height: 300px;
        border: 2px dashed #ccc;
        margin-top: 20px;
        text-align: center;
        line-height: 300px;
    }
</style>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../include/header.jsp"%>

    <!-- =============================================== -->

    <jsp:include page="../include/sider.jsp">
        <jsp:param name="menu" value="manager_account"/>
    </jsp:include>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                年票销售
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">年票销售</h3>
                    <div class="box-tools">
                        <a href="/ticket/sale" class="btn btn-success btn-sm">返回</a>
                        <p class="login-box-msg text-danger">${message}</p>
                    </div>
                </div>
                <div class="box-body">
                    <form method="post" id="saveForm">
                        <input type="hidden" id="storeManagerAttachment" name="idCardKey">
                        <div class="form-group">
                            <label>顾客身份证号</label>
                            <input type="text" name="customerId" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>顾客姓名</label>
                            <input type="text" name="customerName" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>请选择年票号</label>
                            <select name="ticketNum"class="form-control">
                                <c:forEach items="${list}" var="ticket">
                                    <option value="${ticket.num}">${ticket.num}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>性别</label>
                            <select name="sex"class="form-control">
                                    <option value="男">男</option>
                                    <option value="女">女</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <div id="picker">顾客一寸照片</div>
                            <div class="photo" id="idCardKey"></div>
                        </div>
                        <div class="box-footer">
                            <button class="btn pull-right btn-primary"  id="saveBtn">保存</button>
                        </div>

                    </form>
                </div>

            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
</div>
<!-- ./wrapper -->

<%@include file="../include/js.jsp"%>
<script src="../../../static/plugins/uploader/webuploader.js"></script>
<script>

    // 初始化Web Uploader
    var uploader = WebUploader.create({
        // 选完文件后，是否自动上传。
        auto: true,
        // swf文件路径
        swf: '../../../static/plugins/uploader/Uploader.swf',
        // 文件接收服务端。
        server: '/file/upload',
        fileVal:'file',
        formData:{
            "name":"jpg"
        },
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#picker',
        // 只允许选择图片文件。
        accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/*'
        }
    });
    var index = -1;
    uploader.on( 'uploadStart', function( file ) {
        index = layer.load(1);
    });
    uploader.on( 'uploadSuccess', function( file,response ) {
        $("#idCardKey").html("");
        var fileName = response.data;
        var $img = $("<img>").attr("src","/file/download?fileName="+fileName);
        $img.appendTo($("#idCardKey"));
        //将key存放到隐藏域中
        $("#storeManagerAttachment").val(fileName);
        layer.msg("上传成功");
    });
    uploader.on( 'uploadError', function( file ) {
        layer.msg("服务器异常");
    });
    uploader.on( 'uploadComplete', function( file ) {
        layer.close(index);
    });

</script>
</body>
</html>
