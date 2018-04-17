<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<style>
  #nav{
    background-color:#2B2B29;
  }
  #a{
    background-color:#2B2B29;
  }
  #mini-btn:hover{
    background-color:#222D32 ;
  }
</style>
<!-- 顶部导航栏部分 -->
  <header class="main-header">
    <!-- Logo -->
    <a id="a" href="index2.html" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>TMS</b></span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b></b>TMS</span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav id="nav" class="navbar navbar-static-top">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button" id="mini-btn">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </a>

      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <!-- User Account: style can be found in dropdown.less -->
          <li class="dropdown user user-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <img src="/static/dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
              <span class="hidden-xs"><shiro:principal property="accName"/></span>
            </a>
            <ul class="dropdown-menu">
              <!-- User image -->
              <li class="user-header">
                <img src="/static/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">

                <p>
                  ${acc.accName}
                </p>
              </li>
              <!-- Menu Footer-->
              <li class="user-footer">
                <div class="pull-left">
                  <a href="/changeMyMessages" class="btn btn-default ">设置</a>
                </div>
                <div class="pull-right">
                  <a href="/logout" class="btn btn-default ">退出</a>
                </div>
              </li>
            </ul>
          </li>
        </ul>
      </div>
    </nav>
  </header>

  <!-- =============================================== -->
