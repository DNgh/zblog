<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
  <!-- Theme style -->
  <link rel="stylesheet" href="components/jquery-mCustomScrollbar/css/jquery.mCustomScrollbar.min.css">
  <link rel="stylesheet" href="components/jquery-emoji/css/jquery.emoji.css">
  <link rel="stylesheet" href="custom/css/custom.css">
  
  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]--> 
  
  <link rel="shortcut icon " type="images/x-icon" href="custom/img/star.png">
  
</head>
<body>

	<h4>可编辑div editable div</h4>
    <div id="editor" contenteditable="true"></div>
    <button id="btn" class="btn btn-sm btn-default">:)</button>
    <button class="btn btn-primary" id="btnLoad2">加载表情 load emoji</button>
    
    <button id="modalBtn" class="btn btn-sm btn-default">弹出模态框</button>
    <!--信息提示模态框-->
			<div class="modal fade bs-example-modal-sm" id="detail-modal" tabindex="-1" role="dialog" aria-labelledby="detail-modal-label">
				<div class="modal-dialog modal-sm" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							<h4 class="modal-title" id="detail-modal-label">评论信息框</h4>
						</div>
						<div class="modal-body">
							<!-- <form id="detail-form"> -->
								<%-- <div class="form-group input-logo">
									<input id="nickname" type="text" class="form-control" placeholder="必填" value="匿名">
									<span class="fa fa-user pull-left" aria-hidden="true">昵称</span>
								</div>
								<div class="form-group input-logo">
									<input id="email" type="text" class="form-control" placeholder="选填">
									<span class="fa fa-envelope pull-left" aria-hidden="true">邮箱</span>
								</div>
								<div class="form-group input-logo">
									<input id="website" type="text" class="form-control" placeholder="选填">
									<span class="fa fa-globe pull-left" aria-hidden="true">网址</span>
								</div>
								<div class="form-group">
									<button type="button" class="btn btn-default btn-sm" id="detail-form-btn" onclick="addComment()">提交评论</button>
								</div> --%>
								<button type="button" class="btn btn-default btn-sm" id="detail-form-btn" onclick="addComment()">提交评论</button>
							<!-- </form> -->
						</div>
					</div>
				</div>
			</div>
			
<!-- jQuery 1.12.4 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="components/bootstrap/js/bootstrap.min.js"></script>
<script src="components/jquery-mCustomScrollbar/js/jquery.mousewheel-3.0.6.min.js"></script>
<script src="components/jquery-mCustomScrollbar/js/jquery.mCustomScrollbar.min.js"></script>
<script src="components/jquery-emoji/js/jquery.emoji.min.js"></script>
<!-- custom js -->
<script src="custom/js/zblog.js"></script>
<script>
  	
    $("#btnLoad2").click(function () {
        $("#editor").emoji({
            button: "#btn",
            showTab: false,
            animation: 'slide',
            icons: [{
                name: "QQ表情",
                path: "components/jquery-emoji/img/qq/",
                maxNum: 91,
                excludeNums: [41, 45, 54],
                file: ".gif"
            }]
        });
    });
    
    $("#modalBtn").click(function () {
    	//提示框，填入用户名、邮箱
    	$("#detail-modal").modal("show");
    })
  //添加评论
    function addComment() {
    	//隐藏提示框 
    	$("#detail-modal").modal("hide");
    	//按钮显示“正在提交”，禁用清除按钮 
    	/* $("#promptCommentBtn").val("正在提交");
    	$("#clearCommentBtn").attr('disabled',true); */
    	
    	//读取评论值，转换换行符</br>
    	/* var commentVal = $("#commentEditor").val();
    	commentVal = convertLineCtrl(commentVal.substring(commentVal.indexOf(":")+1));
    	$("#comment-result").html(commentVal);
    	$("#comment-result").emojiParse({
    	    icons: iconsCfg
    	}); */
    	
    	//读取评论人信息 
    	/* var nickname = $("#nickname").val();
		var email = $("#email").val();
		var website = $("#website").val(); */
			
    	//读取文章id、根评论id，父评论id，url
    	/* var articleId = $("#articleId").val();
    	var rid = $("#commentRid").val();
    	var pid = $("#commentPid").val();
    	var pnickname = $("#pnickname").val();
		var to = $("#commentUrl").val(); */
		var to = "comment/add"
			
		//组装参数 
		var map = {
			'articleId':10,
			'commentRid':4,
			'commentPid':4,
			'commentContent':'牛xxx',
			'pnickname':'迎',
			'nickname':'草帽X',
			'email':'',
			'website':''
		};
		
		$.ajax({
            url: to,
            datatype: 'json',
            type: "POST",
            data: convertAjaxDataNP(map),
            success: function (result) {
            	alert("comment result:"+result)
            	//按钮显示“评论”，启用清除按钮
    			
            },
            error: function(XMLHttpRequest, textStatus, errorThrown){
            	//清除默认值
            	
        		alert("请求失败");
            }
        });
		
    }
  
</script>
</body>
</html>
