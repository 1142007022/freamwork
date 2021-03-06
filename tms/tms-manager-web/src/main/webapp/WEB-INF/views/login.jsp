<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>TMS</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/static/plugins/font-awesome/css/font-awesome.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/static/dist/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">
    <!-- <link rel="stylesheet" href="/static/bootstrap/css/demo.css"> -->
    <style>
        body {
            background-image: url("../../static/images/2.jpg");

        }

        .col-xs-6 link {
            display: inline-block;
        }

        #loginBtn {
            width: 460px;
            margin: 0px 15px;
            background-color: #5BC0DE;
        }

        #zhuce {
            margin: 8px 0px;
            font-size: 16px;
        }

        .login-logo {
            border-radius: 8px 8px 0px 0px;
            box-align: center;
            color: #DDDDDD;
            padding: 18px;
            height: 100px;
            width: 500px;
            margin: 0px;
            background-color: #FFFFFF;
        }

        label {
            color: white;
            text-align: center;
        }

        .login-box-body {
            box-align: center;
            border-radius: 0px 0px 8px 8px;
            background-color: #EEEEEE;
            width: 500px;
            height: 250px;

        }

        .cent {
            position: absolute;
            left: 450px;
        }

        #loginBtn {
            background-color: #4AAF51;
            color: #FFFAE3;
            font-family: \534E\6587\5B8B\4F53;
        }

        .checkbox {
            color: #DDDDDD;
        }

        #jizhuzhanghao {
            color: #4C4C4C;
            font-family: \4EFF\5B8B;
        }
    </style>
</head>

<body class="hold-transition " id="by">
<div class="login-box cent">
    <div class="login-logo">
        TMS系统登录
    </div>
    <!-- /.login-logo -->
    <div class="login-box-body">

        <p class="login-box-msg text-danger">${message}</p>

        <p class="login-box-msg"></p>
        <div class="alert alert-danger" hidden id="message"></div>
        <div class="login-content">
            <div class="form">
                <form id="loginForm" method="post">
                    <div class="form-group has-feedback">
                        <input type="text" class="form-control" id="mobile"
                               name="mobile" placeholder="手机号" autofocus value="${mobile}"> <span
                            class="glyphicon glyphicon-envelope form-control-feedback"></span>
                    </div>
                    <div class="form-group has-feedback">
                        <input type="password" class="form-control" id="password"
                               name="password" placeholder="密码"> <span
                            class="glyphicon glyphicon-lock form-control-feedback"></span>
                    </div>
                    <div class="row ">
                        <button type="button" id="loginBtn"
                                class="btn  btn-block btn-flat">登录
                        </button>
                        <div class="col-xs-8">
                            <div class="checkbox">
                                <label id="jizhuzhanghao"> <input type="checkbox" name="remember"
                                                                  value="remeber"
                                <c:if test="${not empty checked}"> checked</c:if> id="remember">
                                    记住帐号
                                </label>
                            </div>
                        </div>
                    </div>

                </form>

            </div>
        </div>
    </div>
</div>


<!-- jQuery 2.2.3 -->
<script src="/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<script src="/static/dist/js/jquery.validate.min.js"></script>
<script src="/static/bootstrap/js/qcode.js"></script>

<script>
    $(function () {

        var callback = "${param.callback}";

        $(document).keydown(function (event) {
            if (event.keyCode == 13) {
                $("#loginForm").submit();
            }
        })

        $("#loginBtn").click(function () {
            $("#loginForm").submit();
        });

        $("#loginForm").validate({
            errorClass: 'text-danger',
            errorElement: 'span',
            rules: {
                username: {
                    "required": true
                },
                password: {
                    "required": true
                }
            },
            messages: {
                username: {
                    "required": "请输入用户名！"
                },
                password: {
                    "required": "请输入密码！"
                }
            }
        });


        $(document).ready(function () {
            function fixHeight() {
                var headerHeight = $("#switcher").height();
                $("#iframe").attr("height", $(window).height() - 54 + "px");
            }

            $(window).resize(function () {
                fixHeight();
            }).resize();

            $('.icon-monitor').addClass('active');

            $(".icon-mobile-3").click(function () {
                $("#by").css("overflow-y", "auto");
                $('#iframe-wrap').removeClass().addClass('mobile-width-3');
                $('.icon-tablet,.icon-mobile-1,.icon-monitor,.icon-mobile-2,.icon-mobile-3').removeClass('active');
                $(this).addClass('active');
                return false;
            });

            $(".icon-mobile-2").click(function () {
                $("#by").css("overflow-y", "auto");
                $('#iframe-wrap').removeClass().addClass('mobile-width-2');
                $('.icon-tablet,.icon-mobile-1,.icon-monitor,.icon-mobile-2,.icon-mobile-3').removeClass('active');
                $(this).addClass('active');
                return false;
            });

            $(".icon-mobile-1").click(function () {
                $("#by").css("overflow-y", "auto");
                $('#iframe-wrap').removeClass().addClass('mobile-width');
                $('.icon-tablet,.icon-mobile,.icon-monitor,.icon-mobile-2,.icon-mobile-3').removeClass('active');
                $(this).addClass('active');
                return false;
            });

            $(".icon-tablet").click(function () {
                $("#by").css("overflow-y", "auto");
                $('#iframe-wrap').removeClass().addClass('tablet-width');
                $('.icon-tablet,.icon-mobile-1,.icon-monitor,.icon-mobile-2,.icon-mobile-3').removeClass('active');
                $(this).addClass('active');
                return false;
            });

            $(".icon-monitor").click(function () {
                $("#by").css("overflow-y", "hidden");
                $('#iframe-wrap').removeClass().addClass('full-width');
                $('.icon-tablet,.icon-mobile-1,.icon-monitor,.icon-mobile-2,.icon-mobile-3').removeClass('active');
                $(this).addClass('active');
                return false;
            });
        });


        function Responsive($a) {
            if ($a == true) $("#Device").css("opacity", "100");
            if ($a == false) $("#Device").css("opacity", "0");
            $('#iframe-wrap').removeClass().addClass('full-width');
            $('.icon-tablet,.icon-mobile-1,.icon-monitor,.icon-mobile-2,.icon-mobile-3').removeClass('active');
            $(this).addClass('active');
            return false;
        };


    })

</script>
</body>

</html>