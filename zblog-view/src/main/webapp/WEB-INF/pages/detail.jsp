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
  <!-- typo.css -->
  <!-- <link rel="stylesheet" href="components/typo.css/typo.css"> -->
  <link rel="stylesheet" href="components/wangEditor/wangEditor.min.css">
  <link rel="stylesheet" href="custom/css/zyd.comment.css">
  <link rel="stylesheet" href="custom/css/custom.css">
  
  <!-- markdown 0.5.0 -->
  <script src="components/marked/marked.min.js"></script>
  
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
		  	<div class="panel panel-default" id="preNext">
				<div class="panel-body">
				  	<div id="comment-place">
						<div class="comment-post" id="comment-post">
							<h3><i class="fa fa-commenting-o fa-fw"></i>评论</h3>
							<div class="cancel-reply" id="cancel-reply" style="display: none;">
								<a href="javascript:void(0);" onclick="$.comment.cancelReply(this)"><i class="fa fa-share"></i>取消回复</a>
							</div>
							<form class="form-horizontal" role="form" id="comment-form">
								<input type="hidden" name="pid" id="comment-pid" value="0" size="22" tabindex="1">
								<div id="editor" style="width: 100%;height: 150px;">
									<p>我十分怀疑你脖子上顶的东西是否有其存在的积极意义！</p>
								</div>
								<a id="comment-form-btn" type="button" data-loading-text="正在提交评论..." class="btn btn-info btn-block">提交评论</a>
							</form>
						</div>
					</div>
		    	</div>
		    	<!-- /.panel-body -->
		  	</div>
		  	<!-- /.panel -->
		  	
		  	
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

<!-- jQuery 3.3.1 -->
<script src="components/jquery/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="components/bootstrap/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script src="components/fastclick/fastclick.js"></script>
<!-- AdminLTE App 2.4.5 -->
<script src="components/AdminLTE/js/adminlte.min.js"></script>
<!-- bootstrap-paginator -->
<script src="components/bootstrap-paginator/bootstrap-paginator.min.js"></script>
<!-- highlight -->
<script src="http://cdn.bootcss.com/highlight.js/8.0/highlight.min.js"></script>
<script src="components/wangEditor/wangEditor.min.js"></script>
<!-- custom js -->
<script src="custom/js/zblog.js"></script>
<script>
    marked.setOptions({
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
            console.log('code',code)
        // return   hljs.highlight(lang, code, false,true).value;
        return hljs.highlightAuto(code).value;
      }
    });
    
    //给table添加样式
    var renderer = new marked.Renderer();
    renderer.table = function (header, body) {
        return '<table class="table table-striped">'+header+body+'</table>'
    }
    
    $("#show").html(marked('${articleInfo.content}',{renderer: renderer}));
    
    $("[data-toggle='tooltip']").tooltip();
    
    var E = window.wangEditor
	var editor = new E('#editor')
	// 自定义菜单配置
	editor.customConfig.menus = [
		'code', // 插入代码
		//			'head', // 标题
		'bold', // 粗体
		'italic', // 斜体
		'underline', // 下划线
		//			'strikeThrough', // 删除线
		//			'foreColor', // 文字颜色
		//			'backColor', // 背景颜色
		'image', // 插入图片
		'link', // 插入链接
		'list', // 列表
		//			'justify', // 对齐方式
		'quote', // 引用
		'emoticon', // 表情
		//			'table', // 表格
		//			'video', // 插入视频
		//			'undo', // 撤销
		//			'redo' // 重复
	];
	// debug模式下，有 JS 错误会以throw Error方式提示出来
	editor.customConfig.debug = true;

	// 关闭粘贴样式的过滤
	editor.customConfig.pasteFilterStyle = false;
	// 自定义处理粘贴的文本内容
	editor.customConfig.pasteTextHandle = function(content) {
		// content 即粘贴过来的内容（html 或 纯文本），可进行自定义处理然后返回
		return content + '<p>在粘贴内容后面追加一行</p>'
	};
	// 插入网络图片的回调
	editor.customConfig.linkImgCallback = function(url) {
		console.log(url) // url 即插入图片的地址
	};
	editor.customConfig.zIndex = 100;
	editor.create();
	//E.fullscreen.init('#editor');
	//		editor.txt.clear(); //清空编辑器内容
	//		editor.txt.html('<p>用 JS 设置的内容</p><strong>hello</strong><script>alert(/xss/);<\/script>');
	//		editor.txt.append('<p>追加的内容</p>');
	// 读取 html
	console.log(editor.txt.html());
	// 读取 进行 xss 攻击过滤后的html
	//console.log(filterXSS(editor.txt.html()));
	// 读取 text
	console.log(editor.txt.text());
	// 获取 JSON 格式的内容
	console.log(editor.txt.getJSON());
</script>
</body>
</html>
