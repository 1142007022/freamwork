/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2018-04-26 09:24:27 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.include;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class sider_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
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
      response.setContentType("text/html;charset=UTF-8");
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
      out.write("<!-- 左侧菜单栏 -->\r\n");
      out.write("<style>\r\n");
      out.write("/*    #sidebar{\r\n");
      out.write("        background-color:#4AAF51;\r\n");
      out.write("    }*/\r\n");
      out.write("</style>\r\n");
      out.write("<aside class=\"main-sidebar\" id=\"sidebar\">\r\n");
      out.write("    <!-- sidebar: style can be found in sidebar.less -->\r\n");
      out.write("    <section class=\"sidebar\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("        <!-- 菜单 -->\r\n");
      out.write("        <ul class=\"sidebar-menu\">\r\n");
      out.write("            <li class=\"header\">销售管理</li>\r\n");
      out.write("            <li class=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.menu == 'ticket_storage' ? 'active' : ''}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"><a href=\"/ticket/sale\"><i class=\"fa fa-circle-o\"></i> <span>年票办理</span></a></li>\r\n");
      out.write("            <li class=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.menu == 'ticket_out' ? 'active' : ''}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"><a href=\"/ticket/out\"><i class=\"fa fa-circle-o\"></i> <span>年票续费</span></a></li>\r\n");
      out.write("            <li><a href=\"#\"><i class=\"fa fa-circle-o\"></i> <span>年票挂失</span></a></li>\r\n");
      out.write("            <li class=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.menu == 'ticket_chart' ? 'active' : ''}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"><a href=\"/ticket/chart\"><i class=\"fa fa-circle-o\"></i> <span>年票解挂</span></a></li>\r\n");
      out.write("            <li><a href=\"#\"><i class=\"fa fa-circle-o\"></i> <span>年票补办</span></a></li>\r\n");
      out.write("            <li><a href=\"#\"><i class=\"fa fa-circle-o\"></i> <span>销售统计</span></a></li>\r\n");
      out.write("\r\n");
      out.write("            ");
      out.write("\r\n");
      out.write("        </ul>\r\n");
      out.write("    </section>\r\n");
      out.write("    <!-- /.sidebar -->\r\n");
      out.write("</aside>");
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
}
