<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>TMS - 系统管理 - 修改</title>
    <%@include file="../../include/css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../include/header.jsp"%>

    <!-- =============================================== -->

    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="manage_permission"/>
    </jsp:include>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                销售点管理
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">修改销售点</h3>
                    <div class="box-tools">
                        <a href="/manager/ticketoffice" class="btn btn-success btn-sm">返回</a>
                    </div>
                </div>
                <div class="box-body">
                    <form method="post" id="saveForm">
                        <input type="hidden" id="storeManagerAttachment" name="idCardKey" value="${ticketoffice.idCardKey}">
                        <input type="hidden" id="storeAttachment" name="businessLicenceKey" value="${ticketoffice.businessLicenceKey}">
                        <div class="form-group">
                            <label>销售点名称</label>
                            <input type="text" name="name" class="form-control" value="${ticketoffice.name}">
                        </div>
                        <div class="form-group">
                        <label>销售点所在地</label>
                        <input type="text" name="place" class="form-control" value="${ticketoffice.place}">
                    </div>
                        <div class="form-group">
                            <label>营业状态</label>
                            <input type="text" name="status" value="${ticketoffice.status}" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>法人姓名</label>
                            <input type="text" name="accountName" value="${saleAccount.accountName}" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>年票数量</label>
                            <input type="text" name="ticketNum" value="${ticketoffice.ticketNum}" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>法人手机号</label>
                            <input type="text" name="mobile" value="${saleAccount.mobile}" class="form-control">
                        </div>

                        <div class="row">
                            <div class="col-md-6">
                                <div id="picker">选择联系人身份证照片</div>
                                <div class="photo" id="idCardKey">
                                    <img src="http://p7kwh2p7p.bkt.clouddn.com/${ticketoffice.idCardKey}-photo" alt="">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div id="picker2">选择营业执照照片</div>
                                <div class="photo" id="storePhoto">
                                    <img src="http://p7kwh2p7p.bkt.clouddn.com/${ticketoffice.businessLicenceKey}-photo" alt="">
                                </div>
                            </div>
                        </div>

                    </form>
                </div>
                <div class="box-footer">
                    <button class="btn pull-right btn-primary" id="saveBtn">保存</button>
                </div>
            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
</div>
<!-- ./wrapper -->

<%@include file="../../include/js.jsp"%>
<script src="../../../../static/plugins/uploader/webuploader.js"></script>
<script>
    $(function () {
        $("#saveBtn").click(function () {
            $("#saveForm").submit();
        });



        // 初始化Web Uploader
        var uploader = WebUploader.create({
            // 选完文件后，是否自动上传。
            auto: true,
            // swf文件路径
            swf: '../../../../static/plugins/uploader/Uploader.swf',
            // 文件接收服务端。
            server: 'http://upload-z1.qiniup.com',
            fileVal:'file',
            formData:{
                "token":"${upToken}"
            },
            // 选择文件的按钮。可选。
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
            var fileName = response.key;
            var $img = $("<img>").attr("src","http://\n" + "p7kwh2p7p.bkt.clouddn.com/"+fileName+"-photo");
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
        // 初始化Web Uploader
        var uploader2 = WebUploader.create({
            // 选完文件后，是否自动上传。
            auto: true,
            // swf文件路径
            swf: '../../../../static/plugins/uploader/Uploader.swf',
            // 文件接收服务端。
            server: 'http://upload-z1.qiniup.com',
            fileVal:'file',
            formData:{
                "token":"${upToken}"
            },
            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: '#picker2',
            // 只允许选择图片文件。
            accept: {
                title: 'Images',
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/*'
            }
        });
        uploader2.on( 'uploadStart', function( file ) {
            index = layer.load(1);
        });
        uploader2.on( 'uploadSuccess', function( file,response ) {
            $("#businessLicenceKey").html("");
            var fileName = response.key;
            var $img = $("<img>").attr("src","http://\n" + "p7kwh2p7p.bkt.clouddn.com/"+fileName+"-photo");
            $img.appendTo($("#businessLicenceKey"));
            //将key存放到隐藏域中
            $("#storeAttachment").val(fileName);
            layer.msg("上传成功");
        });
        uploader2.on( 'uploadError', function( file ) {
            layer.msg("服务器异常");
        });
        uploader2.on( 'uploadComplete', function( file ) {
            layer.close(index);
        });




    })
</script>
</body>
</html>
