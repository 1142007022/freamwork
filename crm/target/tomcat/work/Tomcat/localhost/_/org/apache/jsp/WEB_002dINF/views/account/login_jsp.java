/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2018-05-02 12:20:15 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.account;

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
      out.write("     \r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <title>CRM</title>\r\n");
      out.write("    <meta content=\"width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no\" name=\"viewport\">\r\n");
      out.write("\t<!-- Bootstrap 3.3.6 -->\r\n");
      out.write("\t<link rel=\"stylesheet\" href=\"/static/bootstrap/css/bootstrap.min.css\">\r\n");
      out.write("\t<!-- Font Awesome -->\r\n");
      out.write("\t<link rel=\"stylesheet\" href=\"/static/plugins/font-awesome/css/font-awesome.min.css\">\r\n");
      out.write("\t<!-- Theme style -->\r\n");
      out.write("\t<link rel=\"stylesheet\" href=\"/static/dist/css/skins/_all-skins.min.css\">\r\n");
      out.write("\t<link rel=\"stylesheet\" href=\"/static/dist/css/AdminLTE.min.css\">\r\n");
      out.write("\t<!-- <link rel=\"stylesheet\" href=\"/static/bootstrap/css/demo.css\"> -->\r\n");
      out.write("\t<style >\r\n");
      out.write("\t\tbody{\r\n");
      out.write("\t\t\tbackground-color: #6699FF;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t.col-xs-6 link{\r\n");
      out.write("\t\t\tdisplay: inline-block;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t#loginBtn {\r\n");
      out.write("\t\t\twidth: 460px;\r\n");
      out.write("\t\t\tmargin: 0px 15px;\r\n");
      out.write("\t\t\tbackground-color: #5BC0DE;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t#zhuce{\r\n");
      out.write("\t\t\tmargin: 8px 0px ;\r\n");
      out.write("\t\t\tfont-size: 16px;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t.login-logo{\r\n");
      out.write("\t\t\tbox-align:center;\r\n");
      out.write("\t\t\tcolor: white;\r\n");
      out.write("\t\t\tpadding:18px;\r\n");
      out.write("\t\t\theight:100px;\r\n");
      out.write("\t\t    width: 500px;\r\n");
      out.write("\t\t\tmargin: 0px;\r\n");
      out.write("\t\t\tbackground-color: #293D66;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tlabel{\r\n");
      out.write("\t\t\tcolor: white;\r\n");
      out.write("\t\t\ttext-align: center;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t.login-box-body{\r\n");
      out.write("\t\t\tbox-align:center;\r\n");
      out.write("\t\t\tbackground-color: #C2D3FF;\r\n");
      out.write("\t\t\twidth: 500px;\r\n");
      out.write("\t\t\theight: 220px;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t.cent{\r\n");
      out.write("\t\t\tposition: absolute;\r\n");
      out.write("\t\t\tleft : 450px;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t</style>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body class=\"hold-transition \" id =\"by\">\r\n");
      out.write("\t<div class=\"login-box cent\">\r\n");
      out.write("\t\t<div class=\"login-logo\">\r\n");
      out.write("\t\t\t登录\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t<!-- /.login-logo -->\r\n");
      out.write("\t\t<div class=\"login-box-body\">\r\n");
      out.write("\t\t\t<p class=\"login-box-msg\"></p>\r\n");
      out.write("\t\t\t<div class=\"alert alert-danger\" hidden id=\"message\"></div>\r\n");
      out.write("\t\t\t<divclass=\"login-content\">\r\n");
      out.write("\t\t\t\t<div class=\"form\">\r\n");
      out.write("\t\t\t\t<form id=\"loginForm\" method=\"post\">\r\n");
      out.write("\t\t\t\t\t<div class=\"form-group has-feedback\">\r\n");
      out.write("\t\t\t\t\t\t<input type=\"text\" class=\"form-control\" id=\"username\"\r\n");
      out.write("\t\t\t\t\t\t\tname=\"username\" value=\"123\" placeholder=\"手机号\"> <span\r\n");
      out.write("\t\t\t\t\t\t\tclass=\"glyphicon glyphicon-envelope form-control-feedback\"></span>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div class=\"form-group has-feedback\">\r\n");
      out.write("\t\t\t\t\t\t<input type=\"password\" class=\"form-control\" id=\"password\"\r\n");
      out.write("\t\t\t\t\t\t\tname=\"password\" value=\"000000\" placeholder=\"密码\"> <span\r\n");
      out.write("\t\t\t\t\t\t\tclass=\"glyphicon glyphicon-lock form-control-feedback\"></span>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div class=\"row \">\r\n");
      out.write("\t\t\t\t\t\t<button type=\"button\" id=\"loginBtn\"\r\n");
      out.write("\t\t\t\t\t\t\tclass=\"btn btn-primary btn-block btn-flat\">登录</button>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"col-xs-8\">\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"checkbox\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<label> <input type=\"checkbox\" name=\"remember\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\tvalue=\"remeber\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t");
      if (_jspx_meth_c_005fif_005f0(_jspx_page_context))
        return;
      out.write(" id=\"remember\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t记住帐号\r\n");
      out.write("\t\t\t\t\t\t\t\t</label>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t</form>\r\n");
      out.write("\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t<!-- jQuery 2.2.3 -->\r\n");
      out.write("\t<script src=\"/static/plugins/jQuery/jquery-2.2.3.min.js\"></script>\r\n");
      out.write("\t<!-- Bootstrap 3.3.6 -->\r\n");
      out.write("\t<script src=\"/static/bootstrap/js/bootstrap.min.js\"></script>\r\n");
      out.write("\t<script src=\"/static/dist/js/jquery.validate.min.js\"></script>\r\n");
      out.write("\t<script src=\"/static/bootstrap/js/qcode.js\"></script>\r\n");
      out.write("\r\n");
      out.write("\t<script>  \r\n");
      out.write("\t$(function(){\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tvar callback = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.callback}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\";\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t$(document).keydown(function(event){\r\n");
      out.write("\t\t\tif(event.keyCode == 13){\r\n");
      out.write("\t\t\t\t$(\"#loginForm\").submit();\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t})\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t$(\"#loginBtn\").click(function(){\r\n");
      out.write("\t\t\t$(\"#loginForm\").submit();\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t$(\"#loginForm\").validate({\r\n");
      out.write("\t\t\terrorClass : 'text-danger',\r\n");
      out.write("\t\t\terrorElement : 'span',\r\n");
      out.write("\t\t\trules : {\r\n");
      out.write("\t\t\t\tusername :{\r\n");
      out.write("\t\t\t\t\t\"required\" : true\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\tpassword : {\r\n");
      out.write("\t\t\t\t\t\"required\" : true\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tmessages :{\r\n");
      out.write("\t\t\t\tusername :{\r\n");
      out.write("\t\t\t\t\t\"required\" : \"请输入用户名！\"\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\tpassword : {\r\n");
      out.write("\t\t\t\t\t\"required\" : \"请输入密码！\"\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tsubmitHandler : function(form){\r\n");
      out.write("\t\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\t\turl:'/login',\r\n");
      out.write("\t\t\t\t\ttype:'post',\r\n");
      out.write("\t\t\t\t\tdata:$(\"#loginForm\").serialize(),\r\n");
      out.write("\t\t\t\t\tbeforeSend : function(){\r\n");
      out.write("\t\t\t\t\t\t$(\"#loginBtn\").text(\"登录中\").attr(\"disabled\",\"disabled\");\r\n");
      out.write("\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\tsuccess : function(data){\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\tif(data.state == 'success') {\r\n");
      out.write("\t\t\t\t\t\t\tif(callback){\r\n");
      out.write("\t\t\t\t\t\t\t\tlocation.href=callback;\r\n");
      out.write("\t\t\t\t\t\t\t}else {\r\n");
      out.write("\t\t\t\t\t\t\t\tlocation.href = \"/account/home\";\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t} else {\r\n");
      out.write("\t\t\t\t\t\t\t$(\"#message\").text(data.message).show();\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\terror : function(){\r\n");
      out.write("\t\t\t\t\t\talert(\"系统异常，其请稍候重试\");\r\n");
      out.write("\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\tcomplete : function(){\r\n");
      out.write("\t\t\t\t\t\t$(\"#loginBtn\").text(\"登录\").removeAttr(\"disabled\");\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("        $(document).ready(function () {\r\n");
      out.write("            function fixHeight() {\r\n");
      out.write("                var headerHeight = $(\"#switcher\").height();\r\n");
      out.write("                $(\"#iframe\").attr(\"height\", $(window).height()-54+ \"px\");\r\n");
      out.write("            }\r\n");
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
      out.write("\t\t\r\n");
      out.write("\r\n");
      out.write("        function Responsive($a) {\r\n");
      out.write("            if ($a == true) $(\"#Device\").css(\"opacity\", \"100\");\r\n");
      out.write("            if ($a == false) $(\"#Device\").css(\"opacity\", \"0\");\r\n");
      out.write("            $('#iframe-wrap').removeClass().addClass('full-width');\r\n");
      out.write("            $('.icon-tablet,.icon-mobile-1,.icon-monitor,.icon-mobile-2,.icon-mobile-3').removeClass('active');\r\n");
      out.write("            $(this).addClass('active');\r\n");
      out.write("            return false;\r\n");
      out.write("        };\r\n");
      out.write("    \r\n");
      out.write("\t\t\r\n");
      out.write("\t})\r\n");
      out.write("\t\r\n");
      out.write("    </script>\r\n");
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
    // /WEB-INF/views/account/login.jsp(90,9) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
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
