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
  <link rel="stylesheet" href="https://cdn.bootcss.com/highlight.js/9.12.0/styles/tomorrow-night-eighties.min.css">
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
		  	<div class="panel panel-default" id="preNext">
				<div class="panel-body">
				  	<!--评论区域-->
					<div class="comment-area">
						<div class="row row-margin-bottom">
							<div class="col-md-12">
								<h3><i class="fa fa-commenting-o fa-fw"></i>评论</h3>
							</div>
						</div>
						<div class="row row-margin-bottom">
							<div class="col-md-12">
								<textarea id="comment-content" class="rounded-border blue-border-focus" rows="7" placeholder="添加评论..."></textarea>
							</div>
						</div>
						<div class="row row-margin-bottom">
							<div class="col-md-12">
					            <div id="editor" class="rounded-border blue-border-focus" contenteditable="true"></div>
					        </div>
						</div>
						<div class="row row-margin-bottom">
							<div class="col-md-2">
								<!-- <a href="javascript:;">表情</a> -->
								<button id="btn" class="btn btn-sm btn-default">表情</button>
							</div>
							<div class="col-md-2 col-md-push-6">
								<button type="button" class="btn btn-block btn-danger" onclick="submitComment()">评论</button>
							</div>
							<div class="col-md-2 col-md-push-6">
								<button type="button" class="btn btn-block btn-success" onclick="clearComment()">清空</button>
							</div>
						</div>
					</div>
					<!-- /.comment-area -->
					<p id="comment-result"></p>
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
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="components/bootstrap/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script src="components/fastclick/fastclick.js"></script>
<!-- AdminLTE App 2.4.5 -->
<script src="components/AdminLTE/js/adminlte.min.js"></script>
<!-- bootstrap-paginator -->
<script src="components/bootstrap-paginator/bootstrap-paginator.min.js"></script>
<script src="https://cdn.bootcss.com/markdown-it/8.4.1/markdown-it.min.js"></script>
<!-- highlight -->
<script src="https://cdn.bootcss.com/highlight.js/9.12.0/highlight.min.js"></script>
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
    
    //提交评论
    function submitComment() {
    	var commentVal = $("#comment-content").val();
    	commentVal = convertLineCtrl(commentVal);
    	$("#comment-result").html(commentVal);
    }
    
  	//清除评论
    function clearComment() {
    	$("#comment-content").val("");
    }
  	
    $("[data-toggle='tooltip']").tooltip();
    
    $("#editor").emoji({
    	button: "#btn",
        showTab: true,
        animation: 'fade',
        icons: [{
            name: "贴吧表情",
            path: "components/jquery-emoji/img/tieba/",
            maxNum: 50,
            file: ".jpg",
            placeholder: ":{alias}:",
            alias: {
                1: "hehe",
                2: "haha",
                3: "tushe",
                4: "a",
                5: "ku",
                6: "lu",
                7: "kaixin",
                8: "han",
                9: "lei",
                10: "heixian",
                11: "bishi",
                12: "bugaoxing",
                13: "zhenbang",
                14: "qian",
                15: "yiwen",
                16: "yinxian",
                17: "tu",
                18: "yi",
                19: "weiqu",
                20: "huaxin",
                21: "hu",
                22: "xiaonian",
                23: "neng",
                24: "taikaixin",
                25: "huaji",
                26: "mianqiang",
                27: "kuanghan",
                28: "guai",
                29: "shuijiao",
                30: "jinku",
                31: "shengqi",
                32: "jinya",
                33: "pen",
                34: "aixin",
                35: "xinsui",
                36: "meigui",
                37: "liwu",
                38: "caihong",
                39: "xxyl",
                40: "taiyang",
                41: "qianbi",
                42: "dnegpao",
                43: "chabei",
                44: "dangao",
                45: "yinyue",
                46: "haha2",
                47: "shenli",
                48: "damuzhi",
                49: "ruo",
                50: "OK"
            },
            title: {
                1: "呵呵",
                2: "哈哈",
                3: "吐舌",
                4: "啊",
                5: "酷",
                6: "怒",
                7: "开心",
                8: "汗",
                9: "泪",
                10: "黑线",
                11: "鄙视",
                12: "不高兴",
                13: "真棒",
                14: "钱",
                15: "疑问",
                16: "阴脸",
                17: "吐",
                18: "咦",
                19: "委屈",
                20: "花心",
                21: "呼~",
                22: "笑脸",
                23: "冷",
                24: "太开心",
                25: "滑稽",
                26: "勉强",
                27: "狂汗",
                28: "乖",
                29: "睡觉",
                30: "惊哭",
                31: "生气",
                32: "惊讶",
                33: "喷",
                34: "爱心",
                35: "心碎",
                36: "玫瑰",
                37: "礼物",
                38: "彩虹",
                39: "星星月亮",
                40: "太阳",
                41: "钱币",
                42: "灯泡",
                43: "茶杯",
                44: "蛋糕",
                45: "音乐",
                46: "haha",
                47: "胜利",
                48: "大拇指",
                49: "弱",
                50: "OK"
            }
        }, {
            name: "QQ高清",
            path: "components/jquery-emoji/img/qq/",
            maxNum: 91,
            excludeNums: [41, 45, 54],
            file: ".gif",
            placeholder: "#qq_{alias}#"
        }, {
            name: "emoji高清",
            path: "components/jquery-emoji/img/emoji/",
            maxNum: 84,
            file: ".png",
            placeholder: "#emoji_{alias}#"
        }]
    });
</script>
</body>
</html>
