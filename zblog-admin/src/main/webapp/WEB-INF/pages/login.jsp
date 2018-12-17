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
  <link rel="stylesheet" href="components/bootstrap/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="components/font-awesome/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="components/Ionicons/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="components/AdminLTE/css/AdminLTE.css">
  <!-- custom css -->
  <link rel="stylesheet" href="custom/css/login.css">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
  
  <!--<link rel="shortcut icon " type="images/x-icon" href="../dist/img/favico.png">-->
  <link rel="shortcut icon " type="images/x-icon" href="custom/img/star.png">
  
</head>
<body class="blur-bg">

	<div class="container">
	  <div class="row">
	    <div class="col-md-4"></div>
	    <div class="col-md-4">
	      <section class="login-form">
	        <form method="post" action="<c:url value='login'/>" role="login">
	          <!-- <img src="http://i.imgur.com/RcmcLv4.png" class="img-responsive" alt="" /> -->
	          <div class="login-logo">
			    <a href="javascript:void(0);"><b>ZBLOG</b></a>
			  </div>
			  <div class="form-group has-feedback">
		        <input type="text" name="username" class="form-control input-lg" placeholder="用户名" required="required" style="border-radius:6px;"/>
		        <span class="glyphicon glyphicon-user form-control-feedback"></span>
		      </div>
	          <div class="form-group has-feedback">
		        <input type="password" name="password" class="form-control input-lg" placeholder="密码" required="required" style="border-radius:6px;"/>
		        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
		      </div>
	          <input type="hidden" name="<c:out value="${_csrf.parameterName}"/>" value="<c:out value="${_csrf.token}"/>"/>
	          <!--<div class="pwstrength_viewport_progress"></div>
	          <div class="progress-bar"></div>-->
	          <button type="submit" name="go" class="btn btn-lg btn-primary btn-block">登录</button>
	          
	          <div>
	            <a href="/registerPage">创建账户</a> 还是 <a href="/changePasswordPage">重置密码</a>
	          </div>
	        </form>
	        <div class="form-links">
	          <a href="#">www.minzone.cloud</a>
	        </div>
	      </section>  
	      </div>
	      <div class="col-md-4"></div>
	  </div>   
	</div>


<!-- jQuery 1.12.4 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="components/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
