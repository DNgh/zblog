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
      <h1>编辑文章</h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-home"></i> 主页</a></li>
        <li class="active">编辑文章</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
    	<div class="panel panel-default">
		   <div class="panel-body">
		   		<input type="hidden" id="articleId" value="${articleInfo.id}"/>
				<form class="form-horizontal" role="form">
					<div class="form-group">
						<label class="col-sm-1 control-label">标题</label>
						<div class="col-sm-11">
							<em style="font-size: 12px;">*必输项</em>
							<input id="title" class="form-control" type="text" placeholder="文章标题，必填" value="${articleInfo.title}">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label">简介</label>
						<div class="col-sm-11">
							<textarea id="description" class="form-control" rows="3">${articleInfo.description}</textarea>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-1 col-sm-11">
						<c:choose>
							<c:when test="${articleInfo.top}">
								<input type="checkbox" id="top" value="option1" checked> 是否置顶
							</c:when>
							<c:otherwise>
								<input type="checkbox" id="top" value="option1"> 是否置顶
							</c:otherwise>
						</c:choose>
					    <c:choose>
							<c:when test="${articleInfo.recommend}">
								<input type="checkbox" id="recommend" value="option2" checked> 是否推荐
							</c:when>
							<c:otherwise>
								<input type="checkbox" id="recommend" value="option2"> 是否推荐
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${articleInfo.original}">
								<input type="checkbox" id="original" value="option3" checked> 是否原创
							</c:when>
							<c:otherwise>
								<input type="checkbox" id="original" value="option3"> 是否原创
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${articleInfo.comment}">
								<input type="checkbox" id="comment" value="option4" checked> 是否允许评论
							</c:when>
							<c:otherwise>
								<input type="checkbox" id="comment" value="option4"> 是否允许评论
							</c:otherwise>
						</c:choose>
					    </div>
					</div>
					<div class="form-group">
						<label class="col-sm-1 control-label">分类</label>
						<div class="col-sm-11">
							<select id="category" class="form-control">
								<!-- Category -->
								<c:forEach items="${categoryInfoList}" var="categoryInfo">  
								    <c:choose>
										<c:when test="${articleInfo.categoryId == categoryInfo.id}">
											<option value="${categoryInfo.id}" selected>${categoryInfo.categoryName}</option>
										</c:when>
										<c:otherwise>
											<option value="${categoryInfo.id}">${categoryInfo.categoryName}</option>
										</c:otherwise>
									</c:choose> 
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
								<c:choose>
									<c:when test="${fn:contains(articleInfo.tagIdList, tagInfo.id)}">
										<input name="tags" type="checkbox" value="${tagInfo.id}" checked> ${tagInfo.tagName}
									</c:when>
									<c:otherwise>
										<input name="tags" type="checkbox" value="${tagInfo.id}"> ${tagInfo.tagName}
									</c:otherwise>
								</c:choose>
							</c:forEach>
			                <!-- /.Tag -->
					    </div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-1">内容</label>
						<div class="col-sm-11">
							<em style="font-size: 12px;">*必输项</em>
							<div id="articleEditor"><textarea style="display:none;">${articleInfo.content}</textarea></div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-1 col-sm-1">
					    	<button type="button" class="btn btn-success" id="publishBtn">发布</button>
					    </div>
					    <div class="col-sm-1">
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
		initNavBarStatus("articleMenu", "editArticleMenu");
		
		articleEditor = editormd("articleEditor", {
	        width: "100%",
	        height: 800,
	        path : "components/editor.md/lib/",
	        theme : "default",
	        previewTheme : "default",
	        editorTheme : "default",
	        //markdown : "",
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
	        imageUploadURL : "/file/uploadImage/",
	        onload : function() {
	            console.log('onload', this);
	            // 引入插件 执行监听方法
               /*  editormd.loadPlugin("components/editor.md/plugins/image-handle-paste/image-handle-paste", function(){
                	articleEditor.imagePaste();
                }); */
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
			//获取文章id
			var articleId = $("#articleId").val();
			
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
				'articleId':articleId,
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
			//ajax请求后端
			$.ajax({
   	            url: "article/save",
   	            datatype: 'json',
   	            type: "POST",
   	            data: convertAjaxDataNP(map),
   	            success: function (result) {
   	            	if(result.success == true){
   	            		layer.msg("发布成功");
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
			//获取文章id
			var articleId = $("#articleId").val();
			
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
				'articleId':articleId,
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
   	            url: "article/save",
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
