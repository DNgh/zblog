<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
  <meta name="_csrf" content="${_csrf.token}"/>
  <meta name="_csrf_header" content="${_csrf.headerName}"/>
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

  <!-- 动态加载标题栏 -->
  <jsp:include page="header.jsp" flush="true" />
  
  <!-- 动态加载导航栏 -->
  <jsp:include page="navigation.jsp" flush="true" />

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>个人详情</h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-home"></i> 主页</a></li>
        <li class="active">个人详情</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
    	<div class="panel panel-default">
		   <div class="panel-body">
				<form class="form-horizontal" role="form">
					<div class="form-group">
						<label class="col-sm-2 control-label text-nowrap">用户名</label>
						<div class="col-sm-10">
							<p id="username" class="form-control-static">${userInfo.username}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">昵称</label>
						<div class="col-sm-10">
							<input id="nickname" type="text" class="form-control" value="${userInfo.nickname}"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">电话</label>
						<div class="col-sm-10">
							<input id="mobile" type="text" class="form-control" value="${userInfo.mobile}"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label text-nowrap">邮箱</label>
						<div class="col-sm-10">
							<p id="email" class="form-control-static">${userInfo.email}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">住址</label>
						<div class="col-sm-10">
							<input id="location" type="text" class="form-control" value="${userInfo.location}"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label text-nowrap">注册ip</label>
						<div class="col-sm-10">
							<p id="regIp" class="form-control-static">${userInfo.regIp}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label text-nowrap">上次登录ip</label>
						<div class="col-sm-10">
							<p id="lastLoginIp" class="form-control-static">${userInfo.lastLoginIp}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label text-nowrap">上次登录时间</label>
						<div class="col-sm-10">
							<p id="lastLoginTime" class="form-control-static">${userInfo.lastLoginTime}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label text-nowrap">登录次数</label>
						<div class="col-sm-10">
							<p id="loginCount" class="form-control-static">${userInfo.loginCount}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label text-nowrap">注册时间</label>
						<div class="col-sm-10">
							<p id="createTime" class="form-control-static">${userInfo.createTime}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label text-nowrap">更新时间</label>
						<div class="col-sm-10">
							<p id="updateTime" class="form-control-static">${userInfo.updateTime}</p>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-1">
					    	<button type="button" class="btn btn-info" id="saveBtn">保存</button>
					    </div>
					</div>
				</form>
		   </div>
		</div>
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  
  <!-- 动态加载尾部栏 -->
  <jsp:include page="footer.jsp" flush="true" />

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
<script src="components/layer/layer.js"></script>
<!-- custom jQuery -->
<script src="custom/js/zblog.js"></script>
<script type="text/javascript">
	var articleEditor;
	$(function(){
		var header = $("meta[name='_csrf_header']").attr("content");
		var token = $("meta[name='_csrf']").attr("content");
		//设置全局ajax请求头
		$.ajaxSetup({ 
			beforeSend: function (xhr) {
				xhr.setRequestHeader(header, token);
			}
		});
		
		//初始化导航栏
		initNavBarStatus("articleMenu", "editArticleMenu");
		
		//保存博客到草稿箱
		$("#saveBtn").click(function(){
			//获取用户名
			var username = $("#username").val();
			//获取昵称
			var nickname = $("#nickname").val();
			//获取电话
			var mobile = $("#mobile").val();
			//住址
			var location = $("#location").val();
			
			var map = {
				'username':username,
				'nickname':nickname,
				'mobile':mobile,
				'location':location
	   		};
			//ajax请求后端
			$.ajax({
   	            url: "user/save",
   	            datatype: 'json',
   	            type: "POST",
   	            data: convertAjaxDataNP(map),
   	            success: function (result) {
   	            	if(result.success == true){
   	            		layer.msg("保存成功");
   	            	}else{
   	            		//失败，提示信息
   	            		layer.alert(result.message, {icon: 5});
   	            	}
   	            },
   	            error: function(XMLHttpRequest, textStatus, errorThrown){
   	            	//清除默认值
   	            	layer.alert("请求失败", {icon: 5});
   	            }
   	        });
		});
	});
	
</script>
</body>
</html>
