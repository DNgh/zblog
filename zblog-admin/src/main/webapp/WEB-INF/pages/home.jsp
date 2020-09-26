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
  <meta name="_csrf" content="${_csrf.token}"/>
  <meta name="_csrf_header" content="${_csrf.headerName}"/>
  <title>MinZone | Blog</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.7 -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/twbs/bootstrap@v3.3.7/dist/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/FortAwesome/Font-Awesome@v4.7.0/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/ionic-team/ionicons@v2.0.0/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/admin-lte@2.4.5/dist/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/admin-lte@2.4.5/dist/css/skins/_all-skins.min.css">
  <!-- custom css -->
  <!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/DNgh/zblog-static/css/common.min.css"> -->
  
  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]--> 
  
  <!--<link rel="shortcut icon " type="images/x-icon" href="../dist/img/favico.png">-->
  <link rel="shortcut icon " type="images/x-icon" href="https://cdn.jsdelivr.net/gh/DNgh/zblog-static/img/star.png">
  
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
      <h1>
                    主页
      </h1>
      <ol class="breadcrumb">
        <li class="active"><a href="#"><i class="fa fa-home"></i> Home</a></li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
		<p class="text-center" style="font-size:50px;">欢迎使用zblog后台管理系统</p>
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  
  <!-- 动态加载尾部栏 -->
  <jsp:include page="footer.jsp" flush="true" />

</div>
<!-- ./wrapper -->

<!-- jQuery 1.12.4 -->
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="https://cdn.jsdelivr.net/gh/twbs/bootstrap@v3.3.7/dist/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script src="https://cdn.jsdelivr.net/npm/fastclick@1.0.6/lib/fastclick.min.js"></script>
<!-- AdminLTE App -->
<script src="https://cdn.jsdelivr.net/npm/admin-lte@2.4.5/dist/js/adminlte.min.js"></script>
<!-- bootstrap-paginator -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap-paginator@1.0.2/bootstrap-paginator.min.js"></script>
<!-- custom jQuery -->
<script src="https://cdn.jsdelivr.net/gh/DNgh/zblog-static/js/common.min.js"></script>
<script type="text/javascript">
	$(function() {
		var header = $("meta[name='_csrf_header']").attr("content");
		var token = $("meta[name='_csrf']").attr("content");
		//设置全局ajax请求头
		$.ajaxSetup({ 
			beforeSend: function (xhr) {
				xhr.setRequestHeader(header, token);
			}
		});
		
		//初始化导航栏
		initNavBarStatus("", "homeMenu");
	})
</script>
</body>
</html>
