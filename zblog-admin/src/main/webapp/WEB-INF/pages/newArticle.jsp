<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
  <base href="<%=basePath%>">
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>MinZone | Blog</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.7 -->
  <link rel="stylesheet" href="components/bootstrap/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="components/font-awesome/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="components/Ionicons/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="components/AdminLTE/css/AdminLTE.css">
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="components/AdminLTE/css/skins/_all-skins.min.css">
  <!-- editormd -->
  <link rel="stylesheet" href="components/editor.md/css/editormd.css" />
  <!-- custom css -->
  <link rel="stylesheet" href="custom/css/custom.css">
  
  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]--> 
  
  <!--<link rel="shortcut icon " type="images/x-icon" href="../dist/img/favico.png">-->
  <link rel="shortcut icon " type="images/x-icon" href="custom/img/star.png">
  
</head>
<body class="hold-transition skin-red sidebar-mini">
<div class="wrapper">

  <header class="main-header">
    <!-- Logo -->
    <a href="home" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>M</b>in</span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>Min</b>zone</span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
        <span class="sr-only">切换导航栏</span>
      </a>

      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <!-- User Account: style can be found in dropdown.less -->
          <li class="dropdown user user-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <img src="custom/img/profile-128x128.jpg" class="user-image" alt="User Image">
              <span class="hidden-xs">Zzm</span>
            </a>
            <ul class="dropdown-menu">
              <!-- User image -->
              <li class="user-header">
                <img src="custom/img/profile-128x128.jpg" class="img-circle" alt="User Image">

                <p>
                  Zzm - 软件开发工程师
                  <small>注册时间2018-10-01</small>
                </p>
              </li>
              <!-- Menu Body -->
              <li class="user-body">
                <div class="row">
                  <div class="col-xs-4 text-center">
                    <a href="#">文章数(0)</a>
                  </div>
                  <div class="col-xs-4 text-center">
                    <a href="#">阅读数(0)</a>
                  </div>
                  <div class="col-xs-4 text-center">
                    <a href="#">评论数(0)</a>
                  </div>
                </div>
                <!-- /.row -->
              </li>
              <!-- Menu Footer-->
              <li class="user-footer">
                <div class="pull-left">
                  <a href="#" class="btn btn-default btn-flat">个人信息</a>
                </div>
                <div class="pull-right">
                  <a href="#" class="btn btn-default btn-flat">退出</a>
                </div>
              </li>
            </ul>
          </li>
          <!-- Control Sidebar Toggle Button -->
          <li>
            <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
          </li>
        </ul>
      </div>
    </nav>
  </header>
  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="custom/img/profile-128x128.jpg" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p>Zzm</p>
          <a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
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
        <li class="active treeview">
          <a href="#">
            <i class="fa fa-book"></i>
            <span>文章管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li class="active"><a href="article/editorPage"><i class="fa fa-circle-o"></i> 创建文章</a></li>
            <li><a href="article/queryPage"><i class="fa fa-circle-o"></i> 查询文章</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-th"></i>
            <span>分类管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="pages/charts/chartjs.html"><i class="fa fa-circle-o"></i> A</a></li>
            <li><a href="pages/charts/morris.html"><i class="fa fa-circle-o"></i> B</a></li>
            <li><a href="pages/charts/flot.html"><i class="fa fa-circle-o"></i> C</a></li>
            <li><a href="pages/charts/inline.html"><i class="fa fa-circle-o"></i> D</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-archive"></i>
            <span>归档管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="pages/charts/chartjs.html"><i class="fa fa-circle-o"></i> A</a></li>
            <li><a href="pages/charts/morris.html"><i class="fa fa-circle-o"></i> B</a></li>
            <li><a href="pages/charts/flot.html"><i class="fa fa-circle-o"></i> C</a></li>
            <li><a href="pages/charts/inline.html"><i class="fa fa-circle-o"></i> D</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-tags"></i>
            <span>标签管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="pages/charts/chartjs.html"><i class="fa fa-circle-o"></i> A</a></li>
            <li><a href="pages/charts/morris.html"><i class="fa fa-circle-o"></i> B</a></li>
            <li><a href="pages/charts/flot.html"><i class="fa fa-circle-o"></i> C</a></li>
            <li><a href="pages/charts/inline.html"><i class="fa fa-circle-o"></i> D</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-comments"></i>
            <span>评论管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="pages/charts/chartjs.html"><i class="fa fa-circle-o"></i> A</a></li>
            <li><a href="pages/charts/morris.html"><i class="fa fa-circle-o"></i> B</a></li>
            <li><a href="pages/charts/flot.html"><i class="fa fa-circle-o"></i> C</a></li>
            <li><a href="pages/charts/inline.html"><i class="fa fa-circle-o"></i> D</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-history"></i>
            <span>访问历史管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="pages/charts/chartjs.html"><i class="fa fa-circle-o"></i> A</a></li>
            <li><a href="pages/charts/morris.html"><i class="fa fa-circle-o"></i> B</a></li>
            <li><a href="pages/charts/flot.html"><i class="fa fa-circle-o"></i> C</a></li>
            <li><a href="pages/charts/inline.html"><i class="fa fa-circle-o"></i> D</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-history"></i>
            <span>操作历史管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="pages/charts/chartjs.html"><i class="fa fa-circle-o"></i> A</a></li>
            <li><a href="pages/charts/morris.html"><i class="fa fa-circle-o"></i> B</a></li>
            <li><a href="pages/charts/flot.html"><i class="fa fa-circle-o"></i> C</a></li>
            <li><a href="pages/charts/inline.html"><i class="fa fa-circle-o"></i> D</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-user"></i>
            <span>系统安全管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="pages/charts/chartjs.html"><i class="fa fa-circle-o"></i> 用户管理</a></li>
            <li><a href="pages/charts/morris.html"><i class="fa fa-circle-o"></i> 角色管理</a></li>
            <li><a href="pages/charts/flot.html"><i class="fa fa-circle-o"></i> C</a></li>
            <li><a href="pages/charts/inline.html"><i class="fa fa-circle-o"></i> D</a></li>
          </ul>
        </li>
        <li>
          <a href="pages/widgets.html">
            <i class="fa fa-th"></i> <span>Widgets</span>
            <span class="pull-right-container">
              <small class="label pull-right bg-green">new</small>
            </span>
          </a>
        </li>
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>创建文章</h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-home"></i> 主页</a></li>
        <li class="active">创建文章</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
    	<div class="panel panel-default">
		   <div class="panel-body">
				<form class="form-horizontal" role="form">
					<div class="form-group">
						<label class="col-sm-1 control-label">标题</label>
						<div class="col-sm-11">
							<em style="font-size: 12px;">*必输项</em>
							<input id="title" class="form-control" type="text" placeholder="文章标题，必填">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label">简介</label>
						<div class="col-sm-11">
							<textarea id="description" class="form-control" rows="3"></textarea>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-1 col-sm-11">
					    <input type="checkbox" id="top" value="option1"> 是否置顶
					    <input type="checkbox" id="recommend" value="option2"> 是否推荐
					    <input type="checkbox" id="original" value="option3"> 是否原创
					    <input type="checkbox" id="comment" value="option4"> 是否允许评论
					    </div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label">分类</label>
						<div class="col-sm-11">
							<select id="category" class="form-control">
								<!-- Category -->
								<c:forEach items="${categoryInfoList}" var="categoryInfo">  
								    <option value="${categoryInfo.id}">${categoryInfo.categoryName}</option> 
								</c:forEach>
				                <!-- /.Category -->
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-1">标签</label>
						<div id="tag" class="col-sm-11">
							<!-- Tag -->
							<c:forEach items="${tagInfoList}" var="tagInfo">
								<input name="tags" type="checkbox" value="${tagInfo.id}"> ${tagInfo.tagName}  
							</c:forEach>
			                <!-- /.Tag -->
					    </div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-1">内容</label>
						<div class="col-sm-11">
							<em style="font-size: 12px;">*必输项</em>
							<div id="articleEditor"></div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-1 col-sm-1">
					    	<button type="button" class="btn btn-success" id="publishBtn">发布</button>
					    </div>
					    <div class="col-sm-1">
					    	<button type="button" class="btn btn-info" id="saveBtn">保存</button>
					    </div>
					    <div class="col-sm-1">
					    	<button type="button" class="btn btn-danger" id="clearBtn">清除</button>
					    </div>
					</div>
				</form>
		   </div>
		</div>
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  <footer class="main-footer">
    <div class="pull-right hidden-xs">
      <b>Version</b> 1.0
    </div>
    <strong>Copyright &copy; 2018-2020.</strong> All rights
    reserved.
  </footer>

</div>
<!-- ./wrapper -->

<!-- jQuery 1.12.4 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="components/bootstrap/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script src="components/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="components/AdminLTE/js/adminlte.min.js"></script>
<!-- bootstrap-paginator -->
<script src="components/bootstrap-paginator/bootstrap-paginator.min.js"></script>
<!-- editormd 1.15-->
<script src="components/editor.md/editormd.min.js"></script>
<script src="components/layer/layer.js"></script>
<!-- custom jQuery -->
<script src="custom/js/zblog.js"></script>
<script type="text/javascript">
	var articleEditor;
	$(function(){
		articleEditor = editormd("articleEditor", {
	        width: "100%",
	        height: 800,
	        path : "components/editor.md/lib/",
	        theme : "default",
	        previewTheme : "default",
	        editorTheme : "default",
	        markdown : "hahaha",
	        codeFold : true,
	        //syncScrolling : false,
	        saveHTMLToTextarea : true,    // 保存 HTML 到 Textarea
	        searchReplace : true,
	        //watch : false,                // 关闭实时预览
	        htmlDecode : "style,script,iframe|on*",            // 开启 HTML 标签解析，为了安全性，默认不开启    
	        //toolbar  : false,             //关闭工具栏
	        //previewCodeHighlight : false, // 关闭预览 HTML 的代码块高亮，默认开启
	        emoji : true,
	        taskList : true,
	        tocm            : true,         // Using [TOCM]
	        tex : true,                   // 开启科学公式TeX语言支持，默认关闭
	        flowChart : true,             // 开启流程图支持，默认关闭
	        sequenceDiagram : true,       // 开启时序/序列图支持，默认关闭,
	        //dialogLockScreen : false,   // 设置弹出层对话框不锁屏，全局通用，默认为true
	        //dialogShowMask : false,     // 设置弹出层对话框显示透明遮罩层，全局通用，默认为true
	        //dialogDraggable : false,    // 设置弹出层对话框不可拖动，全局通用，默认为true
	        //dialogMaskOpacity : 0.4,    // 设置透明遮罩层的透明度，全局通用，默认值为0.1
	        //dialogMaskBgColor : "#000", // 设置透明遮罩层的背景颜色，全局通用，默认为#fff
	        imageUpload : true,
	        imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
	        imageUploadURL : "./php/upload.php",
	        onload : function() {
	            console.log('onload', this);
	            //this.fullscreen();
	            //this.unwatch();
	            //this.watch().fullscreen();

	            //this.setMarkdown("#PHP");
	            //this.width("100%");
	            //this.height(480);
	            //this.resize("100%", 640);
	        }
	    });	
		
		//发布博客
		$("#publishBtn").click(function(){
			//获取文章标题
			var title = $("#title").val();
			//获取文章简介
			var description = $("#description").val();
			//是否置顶、是否推荐、是否原创、是否允许评论
			var top = false;
			if($('#top').is(':checked')) {
				top = true;
			}
			
			var recommend = false;
			if($('#recommend').is(':checked')) {
				recommend = true;
			}
			
			var original = false;
			if($('#original').is(':checked')) {
				original = true;
			}
			
			var comment = false;
			if($('#comment').is(':checked')) {
				comment = true;
			}
			//获取分类id列表
			var categoryId = $("#category option:selected").val();
			//获取标签id列表
			var tagIdList = new Array();
			$("input[name='tags']:checked").each(function(index, item){
				tagIdList.push($(item).val());
			});
			//获取markdown
			var markdown = articleEditor.getMarkdown()
			
			//标题判空
			if(title==null||title==undefined||title==""){
				layer.msg('标题不能为空');
				return;
			}
			//简介判空
			if(description==null||description==undefined||description==""){
				layer.msg('简介不能为空');
				return;
			}
			//内容判空
			if(markdown==null||markdown==undefined||markdown==""){
				layer.msg('内容不能为空');
				return;
			}
			
			var map = {
				'title':title,
				'description':description,
				'top':top,
				'recommend':recommend,
				'original':original,
				'comment':comment,
				'categoryId':categoryId,
				'tagIdList':tagIdList,
				'markdown':markdown,
				'state':'PUBLISH'
	   		};
			//var newWindow = window.open();
			//ajax请求后端
			$.ajax({
   	            url: "article/add",
   	            datatype: 'json',
   	            type: "POST",
   	            data: convertAjaxDataNP(map),
   	            success: function (result) {
   	            	if(result.success == true){
   	            		//newWindow.location.href = "article/editorPage";
   	            		//window.open("article/editorPage");
   	            		window.location.href = "article/editorPage";
   	            	}else{
   	            		//失败，提示信息
   	            		layer.alert(result.message, {icon: 5});
   	            	}
   	            },
   	            error: function(XMLHttpRequest, textStatus, errorThrown){
   	            	//清除默认值
   	            	alert("请求失败");
   	            }
   	        });
		});
		
		//保存博客到草稿箱
		$("#saveBtn").click(function(){
			//获取文章标题
			var title = $("#title").val();
			//获取文章简介
			var description = $("#description").val();
			//是否置顶、是否推荐、是否原创、是否允许评论
			var top = $("#description").val();
			var recommend = $("#description").val();
			var original = $("#description").val();
			var comment = $("#description").val();
			//获取分类id列表
			var categoryId = $("#category option:selected").val();
			//获取标签id列表
			var tagIdList = new Array();
			$("input[name='tags']:checked").each(function(index, item){
				tagIdList.push($(item).val());
			});
			//获取markdown
			var markdown = articleEditor.getMarkdown()
			
			//标题判空
			if(title==null||title==undefined||title==""){
				layer.msg('标题不能为空');
				return;
			}
			//简介判空
			if(description==null||description==undefined||description==""){
				layer.msg('简介不能为空');
				return;
			}
			//内容判空
			if(markdown==null||markdown==undefined||markdown==""){
				layer.msg('内容不能为空');
				return;
			}
			
			var map = {
				'title':title,
				'description':description,
				'top':top,
				'recommend':recommend,
				'original':original,
				'comment':comment,
				'categoryId':categoryId,
				'tagIdList':tagIdList,
				'markdown':markdown,
				'state':'CREATE'
	   		};
			//ajax请求后端
			$.ajax({
   	            url: "article/add",
   	            datatype: 'json',
   	            type: "POST",
   	            data: convertAjaxDataNP(map),
   	            success: function (result) {
   	            	if(result.success == true){
   	            		window.open("article/editorPage");
   	            	}else{
   	            		//失败，提示信息
   	            		layer.alert(result.message, {icon: 5});
   	            	}
   	            },
   	            error: function(XMLHttpRequest, textStatus, errorThrown){
   	            	//清除默认值
   	            	alert("请求失败");
   	            }
   	        });
		});
	});
	
</script>
</body>
</html>
