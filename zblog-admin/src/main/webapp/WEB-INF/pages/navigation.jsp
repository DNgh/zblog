<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="custom/img/avatar.png" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p><sec:authentication property="name"/></p>
          <a href="home"><i class="fa fa-circle text-success"></i> 在线</a>
        </div>
      </div>
      <!-- search form -->
      <form action="#" method="post" class="sidebar-form">
        <div class="input-group">
          <input type="text" name="q" class="form-control" placeholder="搜索...">
          <span class="input-group-btn">
            <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
            </button>
          </span>
        </div>
      </form>
      <!-- /.search form -->
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu" data-widget="tree">
        <li class="header">导航栏</li>
        <li id="homeMenu"><a href="home"><i class="fa fa-home"></i> <span>主页</span></a></li>
        <li id="articleMenu" class="treeview">
          <a href="#">
            <i class="fa fa-book"></i>
            <span>文章管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li id="newArticleMenu"><a href="article/newPage"><i class="fa fa-circle-o"></i> 创建文章</a></li>
            <li id="queryArticleMenu"><a href="article/queryPage"><i class="fa fa-circle-o"></i> 查询文章</a></li>
            <li id="editArticleMenu"><a href="javascript:void(0);"><i class="fa fa-circle-o"></i> 编辑文章</a></li>
          </ul>
        </li>
        <li id="categoryMenu" class="treeview">
          <a href="#">
            <i class="fa fa-th"></i>
            <span>分类管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
          	<li id="newCategoryMenu"><a href="category/newPage"><i class="fa fa-circle-o"></i> 创建分类</a></li>
            <li id="queryCategoryMenu"><a href="category/queryPage"><i class="fa fa-circle-o"></i> 查询分类</a></li>
            <li id="editCategoryMenu"><a href="javascript:void(0);"><i class="fa fa-circle-o"></i> 编辑分类</a></li>
          </ul>
        </li>
        <li id="archiveMenu" class="treeview">
          <a href="#">
            <i class="fa fa-archive"></i>
            <span>归档管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li id="queryArchiveMenu"><a href="archive/queryPage"><i class="fa fa-circle-o"></i> 查询归档</a></li>
          </ul>
        </li>
        <li id="tagMenu" class="treeview">
          <a href="#">
            <i class="fa fa-tags"></i>
            <span>标签管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
          	<li id="newTagMenu"><a href="tag/newPage"><i class="fa fa-circle-o"></i> 创建标签</a></li>
            <li id="queryTagMenu"><a href="tag/queryPage"><i class="fa fa-circle-o"></i> 查询标签</a></li>
            <li id="editTagMenu"><a href="javascript:void(0);"><i class="fa fa-circle-o"></i> 编辑标签</a></li>
          </ul>
        </li>
        <li id="commentMenu" class="treeview">
          <a href="#">
            <i class="fa fa-comments"></i>
            <span>评论管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li id="queryCommentMenu"><a href="comment/queryPage"><i class="fa fa-circle-o"></i> 查询评论</a></li>
            <li id="detailCommentMenu"><a href="javascript:void(0);"><i class="fa fa-circle-o"></i> 评论详情</a></li>
          </ul>
        </li>
        <li id="visitHstMenu" class="treeview">
          <a href="#">
            <i class="fa fa-history"></i>
            <span>访问历史管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li id="queryVisitHstMenu"><a href="visitHst/queryPage"><i class="fa fa-circle-o"></i> 查询访问历史</a></li>
          </ul>
        </li>
        <li id="optHstMenu" class="treeview">
          <a href="#">
            <i class="fa fa-pencil"></i>
            <span>操作历史管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li id="queryOptHstMenu"><a href="operateHst/queryPage"><i class="fa fa-circle-o"></i> 查询操作历史</a></li>
          </ul>
        </li>
        <li id="userMenu" class="treeview">
          <a href="#">
            <i class="fa fa-user"></i>
            <span>系统安全管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li id="userProfileMenu"><a href="user/profile"><i class="fa fa-circle-o"></i> 个人信息</a></li>
          </ul>
        </li>
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>
