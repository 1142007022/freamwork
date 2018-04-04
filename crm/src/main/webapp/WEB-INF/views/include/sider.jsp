<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
  <!-- 左侧菜单栏 -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      
      <!-- 搜索表单，不需要删除即可 -->
      <!--<form action="#" method="get" class="sidebar-form">
        <div class="input-group">
          <input type="text" name="q" class="form-control" placeholder="Search...">
              <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
        </div>
      </form>-->
      <!-- /.search form -->
      <!-- 菜单 -->
      <ul class="sidebar-menu">
        <li class="header">系统功能</li>
        <!-- 客户管理 -->
        <li class="treeview ${fn:startsWith(param.param,'customer_') ? 'active' : ''}">
          <a href="#">
            <i class="fa fa-address-book-o"></i> <span>客户管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li class="${param.param == 'customer_my' ? 'active' : ''}"><a href="/customer/my"><i class="fa fa-circle-o"></i> 我的客户</a></li>
            <li class="${param.param == 'customer_public' ? 'active' : ''}"><a href="/customer/public"><i class="fa fa-circle-o"></i> 公海客户</a></li>
          </ul>
        </li>
        <!-- 工作记录 -->
        <li class="treeview ${fn:startsWith(param.param,'work_record_') ? 'active' : ''}">
          <a href="#">
            <i class="fa fa-bars"></i> <span>销售机会</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li class="${param.param == 'work_record_my' ? 'active' : ''}"><a href="/sale/my/list"><i class="fa fa-circle-o"></i> 我的机会</a></li>
          </ul>
        </li>
        <!-- 待办事项 -->
        <li class="treeview ${fn:startsWith(param.param,'task_List') ? 'active' : ''}">
          <a href="#">
            <i class="fa fa-calendar ${param.param == 'task_List' ? 'active' : ''}"></i> <span>待办事项</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li class="${param.param == 'task_List' ? 'active' : ''}"><a href="/task"><i class="fa fa-circle-o"></i> 待办列表</a></li>
          </ul>
        </li>
        <!-- 统计报表 -->
        <li class="treeview ${fn:startsWith(param.param,'charts_') ? 'active' : ''}">
          <a href="#">
            <i class="fa fa-pie-chart"></i> <span>统计报表</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li class="${param.param == 'charts_level' ? 'active' : ''}"><a href="/chart"><i class="fa fa-circle-o"></i> 客户等级</a></li>
            <li class="${param.param == 'charts_schedule' ? 'active' : ''}"><a href="/chart/schedule"><i class="fa fa-circle-o"></i> 销售机会进度统计</a></li>
          </ul>
        </li>
        
        
         <li class="treeview ${fn:startsWith(param.param,'tool_') ? 'active' : ''}">
          <a href="#">
            <i class="fa fa-wrench"></i> <span>快捷工具</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li class="${param.param == 'tool_jisuanqi' ? 'active' : ''}"><a href="/jisuanqi"><i class="fa fa-calculator "></i> 计算器</a></li>
          </ul>
           <ul class="treeview-menu">
            <li class="${param.param == 'tool_daohang' ? 'active' : ''}"><a href="/daohang"><i class="fa fa-level-up"></i>网站导航</a></li>
          </ul>
          
        </li>
        
        
        
        <li><a href="/disk/list"><i class="fa fa-share-alt"></i> <span>公司网盘</span></a></li>
        <c:if test="${isAdmin == 'yes'}">
        	<li class="header ">系统管理</li>
	        <!-- 部门员工管理 -->
	        <li class="${param.param == 'account' ? 'active' : ''}"><i class="fa fa-users"s><a href="/dept/add" ></i> <span>员工管理</span></a></li>
	        <!--<li><a href="#"><i class="fa fa-circle-o text-yellow"></i> <span>Warning</span></a></li>
	        <li><a href="#"><i class="fa fa-circle-o text-aqua"></i> <span>Information</span></a></li>-->
        </c:if>
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>

  <!-- =============================================== -->