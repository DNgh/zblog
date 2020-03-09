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
  <meta name="_csrf_param" content="${_csrf.parameterName}"/>
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

  <!-- 动态加载标题栏 -->
  <jsp:include page="header.jsp" flush="true" />
  
  <!-- 动态加载导航栏 -->
  <jsp:include page="navigation.jsp" flush="true" />

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
		var header = $("meta[name='_csrf_header']").attr("content");
		var token = $("meta[name='_csrf']").attr("content");
		//设置全局ajax请求头
		$.ajaxSetup({ 
			beforeSend: function (xhr) {
				xhr.setRequestHeader(header, token);
			}
		});
		
		//初始化导航栏
		initNavBarStatus("articleMenu", "newArticleMenu");
		
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
   	            		window.location.href = "article/editorPage?articleId="+result.articleId;
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
		
		//保存博客到草稿箱
		$("#saveBtn").click(function(){
			//获取文章标题
			var title = $("#title").val();
			//获取文章简介
			var description = $("#description").val();
			//是否置顶、是否推荐、是否原创、是否允许评论
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
   	            		layer.msg("保存成功");
   	            		window.location.href = "article/editorPage?articleId="+result.articleId;
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
