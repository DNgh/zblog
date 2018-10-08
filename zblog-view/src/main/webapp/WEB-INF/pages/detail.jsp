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
  <!-- Ionicons -->
  <link rel="stylesheet" href="components/Ionicons/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="components/AdminLTE/css/AdminLTE.css">
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="components/AdminLTE/css/skins/_all-skins.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.12.0/styles/tomorrow-night-eighties.min.css">
  <link rel="stylesheet" href="components/jquery-mCustomScrollbar/css/jquery.mCustomScrollbar.min.css">
  <link rel="stylesheet" href="components/jquery-emoji/css/jquery.emoji.css">
  <link rel="stylesheet" href="custom/css/custom.css">
  
  <!-- markdown 0.5.0 -->
  <%-- <script src="components/marked/marked.min.js"></script> --%>
  
  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]--> 
  
  <link rel="shortcut icon " type="images/x-icon" href="custom/img/star.png">
  
</head>
<body class="hold-transition skin-red-light sidebar-mini">
<div class="wrapper">

  <header class="main-header">
    
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
      <div class="navbar-custom-menu">

        <ul class="nav navbar-nav navbar-custom-list">
          <li>
          	<a href="home">
              <i class="fa fa-home"></i> | 首页
            </a>
          </li>
         
          <li>
            <a href="about">
              <i class="fa fa-info"></i> | 关于
            </a>
          </li>

          <li>
            <a href="contact">
              <i class="fa fa-phone"></i> | 联系
            </a>
          </li>

        </ul>
      </div>
    </nav>
  </header>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">

    <!-- Main content -->
    <section class="content">

      <div class="row">
      	<div class="col-md-1">
      	</div>

        <div class="col-md-3">
		  <!-- Widget: user widget style 1 -->
          <div class="box box-widget widget-user">
            <!-- Add the bg color to the header using any of the bg-* classes -->
            <div class="widget-user-header bg-black" style="background: url('custom/img/photo1.png') center center;">
              <h3 class="widget-user-username">周志民</h3>
              <h5 class="widget-user-desc">软件开发</h5>
            </div>
            <div class="widget-user-image">
              <img class="img-circle" src="custom/img/profile-128x128.jpg" alt="User Avatar">
            </div>
            <div class="box-footer">
              <div class="row">
                <div class="col-sm-12">
                  <div class="description-block">
                    <span class="description-text">码农一枚，从事Java软件开发，坚持学习技术，努力提升自己。</span>
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="col-sm-4 border-right">
                  <div class="description-block">
                    <h5 class="description-header"><s:property value="blogInfo.totalArticleNum"/></h5>
                    <span class="description-text">文章数</span>
                  </div>
                  <!-- /.description-block -->
                </div>
                <!-- /.col -->
                <div class="col-sm-4 border-right">
                  <div class="description-block">
                    <h5 class="description-header"><s:property value="blogInfo.totalReadNum"/></h5>
                    <span class="description-text">阅读数</span>
                  </div>
                  <!-- /.description-block -->
                </div>
                <!-- /.col -->
                <div class="col-sm-4">
                  <div class="description-block">
                    <h5 class="description-header"><s:property value="blogInfo.totalCommentNum"/></h5>
                    <span class="description-text">评论数</span>
                  </div>
                  <!-- /.description-block -->
                </div>
                <!-- /.col -->
              </div>
              <!-- /.row -->
            </div>
          </div>
          <!-- /.widget-user -->
          
          <!-- Categories Box -->
          <div class="box box-primary">
            <div class="box-header with-border">
              <i class="fa fa-th"></i>
              <h3 class="box-title">分类</h3>
              <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>
              </div>
            </div>
            <!-- /.box-header -->
            <div class="box-body no-padding">
              <ul class="nav nav-pills nav-stacked">
                <!-- Category -->
                <s:iterator value="categoryInfoList" var="var">
				  <li><a href="javascript:void(0);" onclick="pageFunction('article/listArticleByCategory', {'pageSize':'5','categoryName':'${var.categoryName}'})">
				  	<i class="fa ${var.icon}"></i> <s:property value="#var.categoryName"/> 
				  	<span class="label label-default pull-right"><s:property value="#var.articleNum"/>篇</span></a>  
				  </li>
				</s:iterator>
                <!-- /.Category -->
              </ul>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->

          <!-- Archive Box -->
          <div class="box box-info">
            <div class="box-header with-border">
              <i class="fa fa-archive"></i>
              <h3 class="box-title">归档</h3>
              <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>
              </div>
            </div>
            <!-- /.box-header -->
            <div class="box-body no-padding">
              <ul class="nav nav-pills nav-stacked">
                <!-- Archive -->
                <s:iterator value="archiveInfoList" var="var">
				  <li><a href="javascript:void(0);" onclick="pageFunction('article/listArticleByArchive', {'pageSize':'5', 'archiveName':'${var.archiveName}'})">
				  	<i class="fa fa-angle-double-right"></i> <s:property value="#var.archiveTitle"/> 
				  	<span class="label label-default pull-right"><s:property value="#var.articleNum"/>篇</span></a>
				  </li>  
				</s:iterator>
                <!-- /.Archive -->
              </ul>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->

          <!-- Tag Box -->
          <div class="box box-success">
            <div class="box-header with-border">
              <i class="fa fa-tags"></i>
              <h3 class="box-title">标签</h3>
              <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>
              </div>
            </div>
            <!-- /.box-header -->
            <div class="box-body no-padding">
              <ul class="nav nav-pills">
               <!-- Tag -->
                <s:iterator value="tagInfoList" var="var">
				  <li><a href="javascript:void(0);" onclick="pageFunction('article/listArticleByTag', {'pageSize':'5','tagName':'${var.tagName}'})">
				  	<span class="label ${var.style}"><s:property value="#var.tagName"/></span></a>
				  </li>  
				</s:iterator>
                <!-- /.Tag -->
              </ul>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
          
          <!-- Rank Box -->
          <div class="box box-danger">
            <div class="box-header with-border">
              <i class="fa fa-thumbs-up"></i>
              <h3 class="box-title">阅读排行</h3>
              <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>
              </div>
            </div>
            <!-- /.box-header -->
            <div class="box-body no-padding">
              <ul class="nav nav-pills nav-stacked">
                 <!-- Rank -->
                <s:iterator value="articleRankList" var="var">
				  <li class="nav-item">
				    <a class="nav-link" href="javascript:void(0);" onclick="doPost('article/show', {'articleKey':'${var.id}'})">
				      <strong><s:property value="#var.title"/></strong>
				      <span class="text-muted block-oneline">阅读量：<s:property value="#var.readNum"/></span>
				    </a>
				  </li>  
				</s:iterator>
                <!-- /.Rank -->
              </ul>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
        </div>
        <!-- /.col -->
        
        <div class="col-md-7">
        	<div class="panel panel-default">
				<div class="panel-body">
				  	<!-- Article -->
				  	<div id="detail">
	                  	<h3><a href="javascript:void(0);" onclick="doPost('article/show', {'articleKey':'${articleInfo.id}'})"><s:property value="articleInfo.title"/></a></h3>
	                  	<ul class="list-inline">
	                  		<li>发表时间：<s:property value="articleInfo.createTime"/></li>
	                  		<li>阅读数：<s:property value="articleInfo.readNum"/></li>
	                  	</ul>
	                  	<div class="divider-h"></div>
	                  	
	                  	<!-- 文章显示块 -->
				        <div id="show"></div>
				        <div class="divider-h"></div>
				        
				        <!-- 点赞 分享：按钮 -->
				        <div class="text-center">
				        	<ul class="list-inline">
				        	  <li>
				        	  	<button type="button" class="btn btn-block btn-default btn-lg" data-toggle="tooltip" data-placement="top" title="点赞">
					              <i class="fa fa-heart"></i>
					              <span class="badge bg-red"><s:property value="articleInfo.favorNum"/></span>
					            </button>
				        	  </li>
				        	  <li>
				        	    <button type="button" class="btn btn-block btn-default btn-lg" data-toggle="tooltip" data-placement="top" title="分享">
					              <i class="fa fa-share"></i>
					              <span class="badge bg-green"><s:property value="articleInfo.shareNum"/></span>
					            </button>
				        	  </li>
				        	</ul>
	           			</div>
	           			
			        	<ul class="list-inline">
			        		<li>文章分类：</li>
		                	<li><a href="#"><s:property value="articleInfo.categoryName"/></a></li>
		               	</ul>
				        	
				        <ul class="list-inline">
				        	<li>标签：</li>
				        	<!-- Tag -->
			                <s:iterator value="articleInfo.tagList" var="var">
							  <li><a href="#"><s:property value="#var"/></a></li>
							</s:iterator>
			                <!-- /.Tag -->
	                  	</ul>
			    	</div>
			    	
			    	<div id="articlePanel"></div>
					<!-- Paginator -->
					<!-- <div class="divider"></div> -->
					<div class="text-center">
	             	   <ul id="homePaginator"></ul>
	           		</div>
	           		
		    	</div>
		    	<!-- /.panel-body -->
		  	</div>
		  	<!-- /.panel -->
		  	
		  	<!-- 上一篇 下一篇 -->
		  	<div class="panel panel-default" id="preNext">
				<div class="panel-body">
				  	<div class="pull-left">
				  		<a href="javascript:void(0);" onclick="doPost('article/showPre', {'articleKey':'${articleInfo.id}'})">上一篇</a>
                    </div>
	                <div class="pull-right">
	                	<a href="javascript:void(0);" onclick="doPost('article/showNext', {'articleKey':'${articleInfo.id}'})">下一篇</a>
	                </div>
		    	</div>
		    	<!-- /.panel-body -->
		  	</div>
		  	<!-- /.panel -->
		  	
		  	<!-- 评论 -->
		  	<div class="panel panel-default" id="commentArea">
				<div class="panel-body">
				  	<!--评论编辑区域-->
					<div class="comment-editor">
						<div class="row row-margin-bottom">
							<div class="col-md-12">
								<h3><i class="fa fa-commenting-o fa-fw"></i>评论</h3>
							</div>
						</div>
						<div class="row row-margin-bottom">
							<div class="col-md-12">
								<input type="hidden" id="articleId" value="${articleInfo.id}"/>
								<input type="hidden" id="commentRid" value="N"/>
								<input type="hidden" id="commentPid" value="N"/>
								<input type="hidden" id="commentUrl" value="comment/add"/>
								<textarea id="commentEditor" class="rounded-border blue-border-focus" rows="7" placeholder="添加评论..."></textarea>
							</div>
						</div>
						<div class="row row-margin-bottom">
							<div class="col-md-2">
								<!-- <button id="faceBtn" class="btn btn-sm btn-default">表情</button> -->
								<img id="faceBtn" alt="表情" src="custom/img/happy.png" class="img-circle" data-toggle="tooltip" data-placement="top" title="表情"/>
							</div>
							<div class="col-md-2 col-md-push-6">
								<button type="button" class="btn btn-block btn-danger" onclick="addComment()">评论</button>
							</div>
							<div class="col-md-2 col-md-push-6">
								<button type="button" class="btn btn-block btn-success" onclick="clearComment()">清空</button>
							</div>
						</div>
					</div>
					<!-- /.comment-editor -->
					<p id="comment-result"></p>
					
					<div class="divider-h"></div>
					<h3><i class="fa fa-comments-o fa-fw"></i><em>20</em>条评论~~~</h3>
					<div class="comment" id="commentShow">
					  <s:iterator value="commentInfoList" var="commentInfo">
					      <!-- comment item -->
					      <div class="item">
			                <img src="custom/img/boy.png" alt="user image">
							<div class="header">
								<a href="javascript:void(0);" class="name"><s:property value="#commentInfo.nickname"/></a>
							</div>
			                <p class="message"><s:property value="#commentInfo.content"/></p>
			                <div class="footer clearfix">
			                	<span class="text-muted"><i class="fa fa-clock-o"></i><s:property value="#commentInfo.createTime"/></span>
			                	<div class="pull-right">
			                        <a href="javascript:void(0);" onclick="addReview(${commentInfo.id},${commentInfo.id},'${commentInfo.nickname}')"><i class="fa fa-reply"></i>回复</a>
			                        <span>|</span>
			                        <a href="javascript:void(0);" onclick="favorReview(this, ${commentInfo.id})"><i class="fa fa-heart"></i>赞 (<i class="num"><s:property value="#commentInfo.favorNum"/></i>)</a>
			                    </div>
			                </div>
			                <!-- 定义变量：子评论列表 -->
			                <s:set name="subCommentList" value="subCommentMap[#commentInfo.id]" />
			                <s:if test='#subCommentList == null || #subCommentList.size <= 0'>
			                	<div class="review" id="review${commentInfo.id}" style="display:none;"></div>
			                </s:if>
			                <s:else>
				                <div class="review" id="review${commentInfo.id}">
				                  <s:iterator value="#subCommentList" var="reviewInfo">
					                  <!-- review item -->
						              <div class="subitem">
										<div class="header">
											<a href="javascript:void(0);" class="name"><s:property value="#reviewInfo.nickname"/>:回复@<s:property value="#reviewInfo.pnickname"/>:</a>
										</div>
						                <p class="message"><s:property value="#reviewInfo.content"/></p>
						                <div class="footer clearfix">
						                    <span class="text-muted"><i class="fa fa-clock-o"></i><s:property value="#reviewInfo.createTime"/></span>
						                	<div class="pull-right">
						                        <a href="javascript:void(0);" onclick="addReview(${reviewInfo.rid},${reviewInfo.id},'${reviewInfo.nickname}')"><i class="fa fa-reply"></i>回复</a>
						                        <span>|</span>
						                        <a href="javascript:void(0);" onclick="favorReview(this, ${reviewInfo.id})"><i class="fa fa-heart"></i>赞(<i class="num"><s:property value="#reviewInfo.favorNum"/></i>)</a>
						                    </div>
						                </div>
						              </div>
				                	</s:iterator>
				                </div>
					            <!-- /.review -->
			                </s:else>
			              </div>
			              <!-- /.item -->
		               </s:iterator>  
		            </div>
		            <!-- /.comment -->
				</div>
		  		<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
			
			<!--信息提示模态框-->
			<div class="modal fade bs-example-modal-sm" id="detail-modal" tabindex="-1" role="dialog" aria-labelledby="detail-modal-label">
				<div class="modal-dialog modal-sm" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							<h4 class="modal-title" id="detail-modal-label">评论信息框</h4>
						</div>
						<div class="modal-body">
							<form id="detail-form">
								<div class="form-group input-logo">
									<input type="text" class="form-control" placeholder="必填" value="匿名">
									<span class="fa fa-user pull-left" aria-hidden="true">昵称</span>
								</div>
								<div class="form-group input-logo">
									<input type="text" class="form-control" placeholder="选填">
									<span class="fa fa-envelope pull-left" aria-hidden="true">邮箱</span>
								</div>
								<div class="form-group input-logo">
									<input type="text" class="form-control" placeholder="选填">
									<span class="fa fa-globe pull-left" aria-hidden="true">网址</span>
								</div>
								<div class="form-group">
									<button type="button" class="btn btn-default btn-sm" id="detail-form-btn">提交评论</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		
        </div>
        <!-- /.col -->
        
        <div class="col-md-1">
      	</div>
        
      </div>
      <!-- /.row -->

    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  <footer class="main-footer">
    <div class="pull-right hidden-xs">
      <b>Version</b> 2.0
    </div>
    <strong>Copyright &copy; 2018-2020.</strong> All rights
    reserved.
  </footer>
  
</div>
<!-- ./wrapper -->

<!-- jQuery 1.12.4 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.0/jquery.cookie.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="components/bootstrap/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script src="components/fastclick/fastclick.js"></script>
<!-- AdminLTE App 2.4.5 -->
<script src="components/AdminLTE/js/adminlte.min.js"></script>
<!-- bootstrap-paginator -->
<script src="components/bootstrap-paginator/bootstrap-paginator.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/markdown-it/8.4.1/markdown-it.min.js"></script>
<!-- highlight -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.12.0/highlight.min.js"></script>
<script src="components/jquery-mCustomScrollbar/js/jquery.mousewheel-3.0.6.min.js"></script>
<script src="components/jquery-mCustomScrollbar/js/jquery.mCustomScrollbar.min.js"></script>
<script src="components/jquery-emoji/js/jquery.emoji.min.js"></script>
<!-- custom js -->
<script src="custom/js/zblog.js"></script>
<script>
	hljs.initHighlightingOnLoad();

	
    /* marked.setOptions({
        renderer: new marked.Renderer(),
        gfm: true,
        tables: true,
        escaped : true,
        breaks: true,
        pedantic: false,
        sanitize: false,
        smartLists: true,
        smartypants: false,
        highlight: function (code, lang) {
        	console.log('code',code);
        	// return   hljs.highlight(lang, code, false,true).value;
        	return hljs.highlightAuto(code).value;
        	//return hljs.highlightBlock(code).value;
      }
    });
    
    //给table添加样式
    var renderer = new marked.Renderer();
    renderer.table = function (header, body) {
        return '<table class="table table-striped">'+header+body+'</table>'
    }
    $("#show").html(marked('${articleInfo.content}',{renderer: renderer})); */
    
    var md = window.markdownit({
   	  highlight: function (str, lang) {
   		    if (lang && hljs.getLanguage(lang)) {
   		      try {
   		        return hljs.highlight(lang, str, true).value;
   		      } catch (__) {}
   		    }

   		    return ''; // use external default escaping
      	  }
      });
      
	$("#show").html(md.render('${articleInfo.content}'));
    
	var iconsCfg = [{
        name: "贴吧表情",
        path: "components/jquery-emoji/img/tieba/",
        maxNum: 50,
        file: ".jpg",
        placeholder: ":{alias}:"
    }, {
        name: "QQ高清",
        path: "components/jquery-emoji/img/qq/",
        maxNum: 91,
        file: ".gif",
        placeholder: "#qq_{alias}#"
    }, {
        name: "emoji高清",
        path: "components/jquery-emoji/img/emoji/",
        maxNum: 84,
        file: ".png",
        placeholder: "#emoji_{alias}#"
    }];
	
    //添加评论
    function addComment() {
    	//提示框，填入邮箱
    	
    	//取值，请求后端 
    	var commentVal = $("#commentEditor").val();
    	commentVal = convertLineCtrl(commentVal);
    	$("#comment-result").html(commentVal);
    	$("#comment-result").emojiParse({
    	    icons: iconsCfg
    	});
    	
    	var articleId = $("#articleId").val();
    	var rid = $("#commentRid").val();
    	var pid = $("#commentPid").val();
		var to = $("#commentUrl").val();
		var map = {
			'articleId':articleId,
			'commentRid':rid,
			'commentPid':pid,
			'commentContent':commentVal
		};
		$.ajax({
            url: to,
            type: "Post",
            data: convertAjaxDataNP(map),
            success: function (result) {
            	alert("comment result:"+result)
                if (result != null) {
                	if(rid == "N" || pid == "N"){
                		//根评论 
                		var html = '<div class="item">'+
                		'<img src="custom/img/boy.png" alt="user image">'+
						'<div class="header">'+
			 			'<a href="javascript:void(0);" class="name">'+result.nickname+'</a>'+
						'</div>'+
	                    '<p class="message">'+result.content+'</p>'+
	                    '<div class="footer clearfix">'+
	                    '<span class="text-muted"><i class="fa fa-clock-o"></i>'+result.createTime+'</span>'+
	                    '<div class="pull-right">'+
	                    '<a href="javascript:void(0);" onclick="addReview('+result.id+','+result.id+',\''+result.nickname+'\')"><i class="fa fa-reply"></i>回复</a>'+
	                    '<span>|</span>'+
	                    '<a href="javascript:void(0);" onclick="favorReview(this,'+result.id+')"><i class="fa fa-heart"></i>赞(<i class="num">'+result.favorNum+'</i>)</a>'+
	                    '</div>'+
	                    '</div>'+
	                    '<div class="review" id="review'+result.id+'">'+
                    	'</div>'+
	                    '</div>';
              
                		$("#commentShow").append(html);
                		//没有回复，则隐藏回复区域 
                		$("#review"+result.id).hide();
                	}else{
                		//子评论 
                		var html = '<div class="subitem">'+
							'<div class="header">'+
				 			'<a href="javascript:void(0);" class="name">'+result.nickname+':回复@'+result.pnickname+':</a>'+
							'</div>'+
		                    '<p class="message">'+result.content+'</p>'+
		                    '<div class="footer clearfix">'+
		                    '<span class="text-muted"><i class="fa fa-clock-o"></i>'+result.createTime+'</span>'+
		                    '<div class="pull-right">'+
		                    '<a href="javascript:void(0);" onclick="addReview('+result.rid+','+result.id+',\''+result.nickname+'\')"><i class="fa fa-reply"></i>回复</a>'+
		                    '<span>|</span>'+
		                    '<a href="javascript:void(0);" onclick="favorReview(this,'+result.id+')"><i class="fa fa-heart"></i>赞(<i class="num">'+result.favorNum+'</i>)</a>'+
		                    '</div>'+
		                    '</div>'+
		                    '</div>';
	              
                    	$("#review"+result.rid).append(html);
                    	//显示回复区域  
                    	$("#review"+result.rid).show();
                	}
                }
            	//清除默认值
            	$("#commentRid").val("N");
        		$("#commentPid").val("N");
        		$("#commentUrl").val("comment/add");
            },
            error: function(XMLHttpRequest, textStatus, errorThrown){
            	//清除默认值
            	$("#commentRid").val("N");
        		$("#commentPid").val("N");
        		$("#commentUrl").val("comment/add");
        		alert("请求失败");
            }
        });
		
    }
    
  	//清除评论
    function clearComment() {
    	$("#commentEditor").val("");
    }
  	
    $("[data-toggle='tooltip']").tooltip();
    
    //显示文本框表情样式 
    $("#commentEditor").emoji({
    	button: "#faceBtn",
        showTab: true,
        animation: 'fade',
        icons: iconsCfg
    });
    
    //定位到评论框
    function addReview(rId, pId, nickname){
		$("#commentRid").val(rId);
		$("#commentPid").val(pId);
		$("#commentUrl").val("comment/add");
		var commetEditor = $("#commentEditor");
		commetEditor.val("回复@"+nickname+":");
		commetEditor.focus();
    }
    
    //点赞
    function favorReview(obj, id){
    	var favorCommentId = $.cookie("favorComment"+id);
    	
    	if(id == null || id == undefined || id < 0){
    		alert("评论id错误");
    	}else if(favorCommentId != null){
    		alert("不能重复点赞");
    	}else{
    		var map = {
    	 		'commentId':id
   		   	};
   	    	$.ajax({
   	            url: "comment/favor",
   	            type: "Post",
   	            data: convertAjaxDataNP(map),
   	            success: function (result) {
   	            	if(result.success == true){
   	            	    //成功，点赞个数+1
   	            		var fNum = $(obj).find(".num").html();
   	            		fNum++;
   	            		$(obj).find(".num").html(fNum);
   	            		//保存信息到cookies
   	            		$.cookie('favorComment'+id,'true',{expire:1});
   	            	}else{
   	            		//失败，提示信息
   	            		alert(result.message);
   	            	}
   	            },
   	            error: function(XMLHttpRequest, textStatus, errorThrown){
   	            	//清除默认值
   	            	alert("请求失败");
   	            }
   	        });
    	}
    }
</script>
</body>
</html>
