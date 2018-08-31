<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>md文本测试</title>
    <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/marked/0.3.4/marked.min.js"></script>
    <link rel="stylesheet" href="components/typo.css/typo.css">
    <script src="http://cdn.bootcss.com/highlight.js/8.0/highlight.min.js"></script>    <style>
    body {display: flex; justify-content: space-between;}
    #content {width: 49%;}
    #show{width: 50%; border:  1px solid  #ddd; padding: 50px; box-sizing: border-box;}
    </style>
</head>
<body>  
<textarea  id="content"></textarea> 
<div id="show" class="typo"></div>
<script>
    marked.setOptions({
        renderer: new marked.Renderer(),
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
    });

    $("#content").on("input  propertychange", function() {
        var val = $(this).val();
        $("#show").html(marked(val));
    })
</script>
</body>
</html>
