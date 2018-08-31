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
  <link rel="stylesheet" href="components/typo.css/typo.css">
  
  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]--> 
  
  <!--<link rel="shortcut icon " type="images/x-icon" href="../dist/img/favico.png">-->
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

          <!-- Profile Image -->
          <div class="box box-warning">
            <div class="box-body box-profile">
              <img class="profile-user-img img-responsive img-circle" src="custom/img/profile-128x128.jpg" alt="User profile picture">
							<h3 class="profile-title text-center">上下求索</h3>
							<hr />
              <p class="text-muted text-center">码农一枚，从事Java软件开发，坚持学习技术，努力提升自己</p>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->

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
                <li class="active"><a href="#"><i class="fa fa-inbox"></i> Java
                  <span class="label label-primary pull-right">12</span></a></li>
                <li><a href="#"><i class="fa fa-tree"></i> Spring</a></li>
                <li><a href="#"><i class="fa fa-file-text-o"></i> Hibernate</a></li>
                <li><a href="#"><i class="fa fa-filter"></i> Struts <span class="label label-warning pull-right">65</span></a>
                </li>
                <li><a href="#"><i class="fa fa-ship"></i> Docker</a></li>
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
                <li class="active"><a href="#"><i class="fa fa-angle-double-right "></i> 2018-07
                  <span class="label label-primary pull-right">12</span></a></li>
                <li><a href="#"><i class="fa fa-angle-double-right"></i> 2018-06</a></li>
                <li><a href="#"><i class="fa fa-angle-double-right"></i> 2018-05</a></li>
                <li><a href="#"><i class="fa fa-angle-double-right"></i> 2018-04 <span class="label label-warning pull-right">65</span></a>
                </li>
                <li><a href="#"><i class="fa fa-angle-double-right"></i> 2018-03</a></li>
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
                <li><a href="#"><span class="label label-primary">大数据</span></a></li>
                <li><a href="#"><span class="label label-info">大数据</span></a></li>
                <li><a href="#"><span class="label label-danger">大数据</span></a></li>
                <li><a href="#"><span class="label label-success">大数据</span></a></li>
                <li><a href="#"><span class="label label-default">大数据</span></a></li>
                <li><a href="#"><span class="label label-warning">大数据</span></a></li>
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
                 <li><a href="#">
                	<strong>阅读第一</strong>
              		<p class="text-muted">阅读量：200</p></a>
                 </li>
                 <li><a href="#">
                	<strong>阅读第一</strong>
              		<p class="text-muted">阅读量：200</p></a>
                 </li>
                 <li><a href="#">
                	<strong>阅读第一</strong>
              		<p class="text-muted">阅读量：200</p></a>
                 </li>
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
                
                  <h3><a href="article/show/1">Docker简介</a></h3>
                  <ul class="list-inline">
                  	<li><i class="fa fa-calendar margin-r-5"></i>发表时间：2018-07-17 11:18:16</li>
                  	<li><i class="fa fa-eye margin-r-5"></i>阅读数：84</li>
                  </ul>
                  <div class="divider"></div>
                  <div class="typo">
                  <textarea  id="content" cols="25" rows="20" style="width:100%;">
                  	正文
                  	Docker简介
Docker是2013发起的一个项目，早在2013年，Docker自诞生起，就是整个技术界的明星项目，当时我还在上海实习，就在各种技术媒体上看到了Docker的介绍文章，很多技术媒体宣称docker是一项技术突破，并且是一次技术革命，可惜当时由于本身是一个Android Framework开发者，眼界很低，对于这种OS虚拟化技术有点不屑一顾，而今转后台后才发现这项技术的重要性

Docker的特征
Docker是一个云开源项目，托管在github，任何人都可以通过 git clone 或者fork参与进来，本身是基于linux的容器技术，采用当时如日中天google新推出的Go语言实现。采用apache 2.0协议开源。

docker镜像地址

Go语言与Docker
相比Go语言与其它语言的对比，国内外很多技术媒体都有列举，在Docker领域，Go语言相比其它语言的优势在于
相对于C/C 开发难度低，支持向前兼容，运维维护成本小
相对于python，生成的是静态文件，有效的避免的低级错误，并且性能高一个等级
并发性好，内存占用低
部署简单，毕竟生成的静态文件，有glibc的地方就能运行

一门语言当然也有自己的缺点，比如，内存回收延迟久，图片处理库有bug，对包版本要求严格等一些问题，但是瑕不掩瑜，一个开发成本极其简单，性能优良，部署简单的语言与Docker简直就是 天作之合

至于Go语言的优势，在Go的社区中都有非常详尽的讨论，这里不多讲


		                 	</textarea>
		                 	<div id="show" class="typo"></div>
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

<script src="components/require/require.js"></script>
<!-- jQuery 3.3.1 -->
<script src="components/jquery/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="components/bootstrap/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script src="components/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="components/AdminLTE/js/adminlte.min.js"></script>
<!-- bootstrap-paginator -->
<script src="components/bootstrap-paginator/bootstrap-paginator.min.js"></script>
<!-- markdown -->
<script src="components/marked/marked.min.js"></script>
<!-- highlight -->
<script src="http://cdn.bootcss.com/highlight.js/8.0/highlight.min.js"></script>
<!-- custom jQuery -->
<script src="custom/js/zblog.js"></script>
<script>
	
    
    
    //var myMarked = require('marked');
    var options = {
        gfm: true,
        tables: true,
        escaped : true,
        breaks: false,
        pedantic: false,
        sanitize: false,
        smartLists: true,
        smartypants: false,
        highlight: function (code, lang) {
            console.log('code',code)
        // return   hljs.highlight(lang, code, false,true).value;
        return hljs.highlightAuto(code).value;
      }
    };

    $("#show").html(marked('# Marked in browser\n\nRendered by **marked**.',options));
</script>
</body>
</html>
