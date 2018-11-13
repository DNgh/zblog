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
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <!-- custom css -->
<!--   <link rel="stylesheet" href="custom/css/custom.css"> -->
  
  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]--> 
  
  <!--<link rel="shortcut icon " type="images/x-icon" href="../dist/img/favico.png">-->
  <link rel="shortcut icon " type="images/x-icon" href="custom/img/star.png">
  
</head>
<body>


		<div class="panel panel-default">
			<div class="panel-body">
				<div class="searchArea">
					<form class="form-inline" role="form">
						<div class="row">
							<div class="form-group col-md-5">
								<label class="control-label">创建时间:</label>
								<div class="input-group">
						            <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
						            <input name="daterange" type="text" class="form-control" size="20"/>
						            <span class="input-group-addon"><i class="fa fa-remove"></i></span>
						     	</div>
							</div>
							<div class="form-group col-md-5">
								<label class="control-label">启用标志:</label>
								<select id="category" class="form-control">
									<!-- 启用 -->
									<option value="" selected>不限</option>
									<option value="Y">是</option>
									<option value="N">否</option>
						      		<!-- /.启用 -->
								</select>
							</div>
							<div class="form-group col-md-2">
								<button type="button" class="btn btn-success" id="searchBtn">查询</button>
							</div>
						</div>
					</form>
				</div>
				<!-- /.searchArea -->
				<!-- 全部分类检索 -->
				<table id="allTable" lay-filter="allTable" style="width:100%"></table>
	    	</div>
		</div>

  

<!-- jQuery 1.12.4 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="components/bootstrap/js/bootstrap.min.js"></script>
<!-- custom jQuery -->
<!-- <script src="custom/js/zblog.js"></script> -->
<script type="text/javascript">
	
</script>
</body>
</html>
