/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2018-04-17 14:06:14 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <title>TMS</title>\r\n");
      out.write("    <meta content=\"width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no\" name=\"viewport\">\r\n");
      out.write("    <!-- Bootstrap 3.3.6 -->\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"/static/bootstrap/css/bootstrap.min.css\">\r\n");
      out.write("    <!-- Font Awesome -->\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"/static/plugins/font-awesome/css/font-awesome.min.css\">\r\n");
      out.write("    <!-- Theme style -->\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"/static/dist/css/skins/_all-skins.min.css\">\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"/static/dist/css/AdminLTE.min.css\">\r\n");
      out.write("    <!-- <link rel=\"stylesheet\" href=\"/static/bootstrap/css/demo.css\"> -->\r\n");
      out.write("    <style>\r\n");
      out.write("        body {\r\n");
      out.write("            background-image: url(\"../../static/images/2.jpg\");\r\n");
      out.write("\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .col-xs-6 link {\r\n");
      out.write("            display: inline-block;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        #loginBtn {\r\n");
      out.write("            width: 460px;\r\n");
      out.write("            margin: 0px 15px;\r\n");
      out.write("            background-color: #5BC0DE;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        #zhuce {\r\n");
      out.write("            margin: 8px 0px;\r\n");
      out.write("            font-size: 16px;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .login-logo {\r\n");
      out.write("            border-radius: 8px 8px 0px 0px;\r\n");
      out.write("            box-align: center;\r\n");
      out.write("            color: #DDDDDD;\r\n");
      out.write("            padding: 18px;\r\n");
      out.write("            height: 100px;\r\n");
      out.write("            width: 500px;\r\n");
      out.write("            margin: 0px;\r\n");
      out.write("            background-color: #FFFFFF;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        label {\r\n");
      out.write("            color: white;\r\n");
      out.write("            text-align: center;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .login-box-body {\r\n");
      out.write("            box-align: center;\r\n");
      out.write("            border-radius: 0px 0px 8px 8px;\r\n");
      out.write("            background-color: #EEEEEE;\r\n");
      out.write("            width: 500px;\r\n");
      out.write("            height: 250px;\r\n");
      out.write("\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .cent {\r\n");
      out.write("            position: absolute;\r\n");
      out.write("            left: 450px;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        #loginBtn {\r\n");
      out.write("            background-color: #4AAF51;\r\n");
      out.write("            color: #FFFAE3;\r\n");
      out.write("            font-family: \\534E\\6587\\5B8B\\4F53;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        .checkbox {\r\n");
      out.write("            color: #DDDDDD;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        #jizhuzhanghao {\r\n");
      out.write("            color: #4C4C4C;\r\n");
      out.write("            font-family: \\4EFF\\5B8B;\r\n");
      out.write("        }\r\n");
      out.write("    </style>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body class=\"hold-transition \" id=\"by\">\r\n");
      out.write("<div class=\"login-box cent\">\r\n");
      out.write("    <div class=\"login-logo\">\r\n");
      out.write("        TMS系统登录\r\n");
      out.write("    </div>\r\n");
      out.write("    <!-- /.login-logo -->\r\n");
      out.write("    <div class=\"login-box-body\">\r\n");
      out.write("\r\n");
      out.write("        <p class=\"login-box-msg text-danger\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${message}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</p>\r\n");
      out.write("\r\n");
      out.write("        <p class=\"login-box-msg\"></p>\r\n");
      out.write("        <div class=\"alert alert-danger\" hidden id=\"message\"></div>\r\n");
      out.write("        <div class=\"login-content\">\r\n");
      out.write("            <div class=\"form\">\r\n");
      out.write("                <form id=\"loginForm\" method=\"post\">\r\n");
      out.write("                    <div class=\"form-group has-feedback\">\r\n");
      out.write("                        <input type=\"text\" class=\"form-control\" id=\"mobile\"\r\n");
      out.write("                               name=\"mobile\" placeholder=\"手机号\" autofocus value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${mobile}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"> <span\r\n");
      out.write("                            class=\"glyphicon glyphicon-envelope form-control-feedback\"></span>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"form-group has-feedback\">\r\n");
      out.write("                        <input type=\"password\" class=\"form-control\" id=\"password\"\r\n");
      out.write("                               name=\"password\" placeholder=\"密码\"> <span\r\n");
      out.write("                            class=\"glyphicon glyphicon-lock form-control-feedback\"></span>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"row \">\r\n");
      out.write("                        <button type=\"button\" id=\"loginBtn\"\r\n");
      out.write("                                class=\"btn  btn-block btn-flat\">登录\r\n");
      out.write("                        </button>\r\n");
      out.write("                        <div class=\"col-xs-8\">\r\n");
      out.write("                            <div class=\"checkbox\">\r\n");
      out.write("                                <label id=\"jizhuzhanghao\"> <input type=\"checkbox\" name=\"remember\"\r\n");
      out.write("                                                                  value=\"remeber\"\r\n");
      out.write("                                ");
      if (_jspx_meth_c_005fif_005f0(_jspx_page_context))
        return;
      out.write(" id=\"remember\">\r\n");
      out.write("                                    记住帐号\r\n");
      out.write("                                </label>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("\r\n");
      out.write("                </form>\r\n");
      out.write("\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- jQuery 2.2.3 -->\r\n");
      out.write("<script src=\"/static/plugins/jQuery/jquery-2.2.3.min.js\"></script>\r\n");
      out.write("<!-- Bootstrap 3.3.6 -->\r\n");
      out.write("<script src=\"/static/bootstrap/js/bootstrap.min.js\"></script>\r\n");
      out.write("<script src=\"/static/dist/js/jquery.validate.min.js\"></script>\r\n");
      out.write("<script src=\"/static/bootstrap/js/qcode.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("    $(function () {\r\n");
      out.write("\r\n");
      out.write("        var callback = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.callback}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\";\r\n");
      out.write("\r\n");
      out.write("        $(document).keydown(function (event) {\r\n");
      out.write("            if (event.keyCode == 13) {\r\n");
      out.write("                $(\"#loginForm\").submit();\r\n");
      out.write("            }\r\n");
      out.write("        })\r\n");
      out.write("\r\n");
      out.write("        $(\"#loginBtn\").click(function () {\r\n");
      out.write("            $(\"#loginForm\").submit();\r\n");
      out.write("        });\r\n");
      out.write("\r\n");
      out.write("        $(\"#loginForm\").validate({\r\n");
      out.write("            errorClass: 'text-danger',\r\n");
      out.write("            errorElement: 'span',\r\n");
      out.write("            rules: {\r\n");
      out.write("                username: {\r\n");
      out.write("                    \"required\": true\r\n");
      out.write("                },\r\n");
      out.write("                password: {\r\n");
      out.write("                    \"required\": true\r\n");
      out.write("                }\r\n");
      out.write("            },\r\n");
      out.write("            messages: {\r\n");
      out.write("                username: {\r\n");
      out.write("                    \"required\": \"请输入用户名！\"\r\n");
      out.write("                },\r\n");
      out.write("                password: {\r\n");
      out.write("                    \"required\": \"请输入密码！\"\r\n");
      out.write("                }\r\n");
      out.write("            }\r\n");
      out.write("        });\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("        $(document).ready(function () {\r\n");
      out.write("            function fixHeight() {\r\n");
      out.write("                var headerHeight = $(\"#switcher\").height();\r\n");
      out.write("                $(\"#iframe\").attr(\"height\", $(window).height() - 54 + \"px\");\r\n");
      out.write("            }\r\n");
      out.write("\r\n");
      out.write("            $(window).resize(function () {\r\n");
      out.write("                fixHeight();\r\n");
      out.write("            }).resize();\r\n");
      out.write("\r\n");
      out.write("            $('.icon-monitor').addClass('active');\r\n");
      out.write("\r\n");
      out.write("            $(\".icon-mobile-3\").click(function () {\r\n");
      out.write("                $(\"#by\").css(\"overflow-y\", \"auto\");\r\n");
      out.write("                $('#iframe-wrap').removeClass().addClass('mobile-width-3');\r\n");
      out.write("                $('.icon-tablet,.icon-mobile-1,.icon-monitor,.icon-mobile-2,.icon-mobile-3').removeClass('active');\r\n");
      out.write("                $(this).addClass('active');\r\n");
      out.write("                return false;\r\n");
      out.write("            });\r\n");
      out.write("\r\n");
      out.write("            $(\".icon-mobile-2\").click(function () {\r\n");
      out.write("                $(\"#by\").css(\"overflow-y\", \"auto\");\r\n");
      out.write("                $('#iframe-wrap').removeClass().addClass('mobile-width-2');\r\n");
      out.write("                $('.icon-tablet,.icon-mobile-1,.icon-monitor,.icon-mobile-2,.icon-mobile-3').removeClass('active');\r\n");
      out.write("                $(this).addClass('active');\r\n");
      out.write("                return false;\r\n");
      out.write("            });\r\n");
      out.write("\r\n");
      out.write("            $(\".icon-mobile-1\").click(function () {\r\n");
      out.write("                $(\"#by\").css(\"overflow-y\", \"auto\");\r\n");
      out.write("                $('#iframe-wrap').removeClass().addClass('mobile-width');\r\n");
      out.write("                $('.icon-tablet,.icon-mobile,.icon-monitor,.icon-mobile-2,.icon-mobile-3').removeClass('active');\r\n");
      out.write("                $(this).addClass('active');\r\n");
      out.write("                return false;\r\n");
      out.write("            });\r\n");
      out.write("\r\n");
      out.write("            $(\".icon-tablet\").click(function () {\r\n");
      out.write("                $(\"#by\").css(\"overflow-y\", \"auto\");\r\n");
      out.write("                $('#iframe-wrap').removeClass().addClass('tablet-width');\r\n");
      out.write("                $('.icon-tablet,.icon-mobile-1,.icon-monitor,.icon-mobile-2,.icon-mobile-3').removeClass('active');\r\n");
      out.write("                $(this).addClass('active');\r\n");
      out.write("                return false;\r\n");
      out.write("            });\r\n");
      out.write("\r\n");
      out.write("            $(\".icon-monitor\").click(function () {\r\n");
      out.write("                $(\"#by\").css(\"overflow-y\", \"hidden\");\r\n");
      out.write("                $('#iframe-wrap').removeClass().addClass('full-width');\r\n");
      out.write("                $('.icon-tablet,.icon-mobile-1,.icon-monitor,.icon-mobile-2,.icon-mobile-3').removeClass('active');\r\n");
      out.write("                $(this).addClass('active');\r\n");
      out.write("                return false;\r\n");
      out.write("            });\r\n");
      out.write("        });\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("        function Responsive($a) {\r\n");
      out.write("            if ($a == true) $(\"#Device\").css(\"opacity\", \"100\");\r\n");
      out.write("            if ($a == false) $(\"#Device\").css(\"opacity\", \"0\");\r\n");
      out.write("            $('#iframe-wrap').removeClass().addClass('full-width');\r\n");
      out.write("            $('.icon-tablet,.icon-mobile-1,.icon-monitor,.icon-mobile-2,.icon-mobile-3').removeClass('active');\r\n");
      out.write("            $(this).addClass('active');\r\n");
      out.write("            return false;\r\n");
      out.write("        };\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    })\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005fif_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f0.setParent(null);
    // /WEB-INF/views/login.jsp(119,32) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${not empty checked}", java.lang.Boolean.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
    if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write(" checked");
        int evalDoAfterBody = _jspx_th_c_005fif_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
    return false;
  }
}
