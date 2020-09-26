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
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/eshengsky/jQuery-emoji/dist/css/jquery.emoji.css">
  <!-- custom css -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/DNgh/zblog-static/css/common.min.css">
  
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
      <h1>评论详情</h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-home"></i> 主页</a></li>
        <li class="active">评论详情</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
    	<div class="panel panel-default">
		   <div class="panel-body">
				<form class="form-horizontal" role="form">
					<div class="form-group">
						<label class="col-sm-1 control-label text-nowrap">文章标题</label>
						<div class="col-sm-11">
							<p class="form-control-static">${commentInfo.articleTitle}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label text-nowrap">评论内容</label>
						<div class="col-sm-11">
							<p id="content" class="form-control-static">${commentInfo.content}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label text-nowrap">用户昵称</label>
						<div class="col-sm-11">
							<p class="form-control-static">${commentInfo.nickname}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label text-nowrap">点赞数</label>
						<div class="col-sm-11">
							<p class="form-control-static">${commentInfo.favorNum}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label text-nowrap">ip地址</label>
						<div class="col-sm-11">
							<p class="form-control-static">${commentInfo.ip}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label text-nowrap">浏览器类型</label>
						<div class="col-sm-11">
							<p class="form-control-static">${commentInfo.browser}</p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label text-nowrap">创建时间</label>
						<div class="col-sm-11">
							<p class="form-control-static">${commentInfo.createTime}</p>
						</div>
					</div>
					<div class="form-group">
					    <div class="col-sm-offset-1 col-sm-1">
					    	<button type="button" class="btn btn-info" id="returnBtn">返回</button>
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
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="https://cdn.jsdelivr.net/gh/twbs/bootstrap@v3.3.7/dist/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script src="https://cdn.jsdelivr.net/npm/fastclick@1.0.6/lib/fastclick.min.js"></script>
<!-- AdminLTE App -->
<script src="https://cdn.jsdelivr.net/npm/admin-lte@2.4.5/dist/js/adminlte.min.js"></script>
<!-- bootstrap-paginator -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap-paginator@1.0.2/bootstrap-paginator.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/layerui@3.1.1/dist/layer.js"></script>
<script src="https://cdn.jsdelivr.net/gh/eshengsky/jQuery-emoji/dist/js/jquery.emoji.min.js"></script>
<!-- custom jQuery -->
<script src="https://cdn.jsdelivr.net/gh/DNgh/zblog-static/js/common.min.js"></script>
<script type="text/javascript">
	var iconsCfg = [{
	    name: "贴吧表情",
	    path: "https://cdn.jsdelivr.net/gh/eshengsky/jQuery-emoji/dist/img/tieba/",
	    maxNum: 50,
	    file: ".jpg",
	    placeholder: ":{alias}:"
	}, {
	    name: "QQ高清",
	    path: "https://cdn.jsdelivr.net/gh/eshengsky/jQuery-emoji/dist/img/qq/",
	    maxNum: 91,
	    file: ".gif",
	    placeholder: "#qq_{alias}#"
	}, {
	    name: "emoji高清",
	    path: "https://cdn.jsdelivr.net/gh/eshengsky/jQuery-emoji/dist/img/emoji/",
	    maxNum: 84,
	    file: ".png",
	    placeholder: "#emoji_{alias}#"
	}];

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
		initNavBarStatus("commentMenu", "detailCommentMenu");
		
		//表情初始化
    	$("#content").emojiParse({
    	    icons: iconsCfg
    	});
		
		//保存分类
		$("#returnBtn").click(function(){
			window.location.href = "comment/queryPage";
		});
	});
	
</script>
</body>
</html>
