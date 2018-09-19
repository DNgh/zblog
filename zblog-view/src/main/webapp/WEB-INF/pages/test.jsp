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
  <!-- Theme style -->
  <link rel="stylesheet" href="components/jquery-mCustomScrollbar/css/jquery.mCustomScrollbar.min.css">
  <link rel="stylesheet" href="components/jquery-emoji/css/jquery.emoji.css">
  
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
    
<!-- jQuery 3.3.1 -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/jquery-mousewheel/3.1.13/jquery.mousewheel.min.js"></script>
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
    
</script>
</body>
</html>
