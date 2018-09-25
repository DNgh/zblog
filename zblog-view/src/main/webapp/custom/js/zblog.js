/**
 * ZBLOG JQuery
 * ------------------
 */
$(function () {
    
    /*var options = {
	    currentPage: 1,    
	    totalPages: 10,    
	    size:"large",    
	    bootstrapMajorVersion: 3,    
	    alignment:"center",    
	    numberOfPages:5,
	    useBootstrapTooltip: true, 
	    tooltipTitles: function (type, page, current) {
            switch (type) {
            case "first":
                return "跳转到首页";
            case "prev":
                return "跳转到上一页";
            case "next":
                return "跳转到下一页";
            case "last":
                return "跳转到尾页";
            case "page":
                return "跳转到第" + page + "页";
            }
        },
	    shouldShowPage:true,
	    itemContainerClass: function (type, page, current) {
            return (type=="page" && page === current) ? "active" : "pointer-cursor"
        },
	    itemTexts: function (type, page, current) {        
	        switch (type) {            
	        case "first": return "首页";            
	        case "prev": return "上一页";            
	        case "next": return "下一页";            
	        case "last": return "末页";            
	        case "page": return page;
	        }
	    }
	}
	$('.homePaginator').bootstrapPaginator(options);*/
    
    $(window).scroll(function(event){
    	var len = $(this).scrollTop();
		if(len >= 100){
			$("#goTop").show();
		}else{
			$("#goTop").hide();
		}
    });
    
	$("#goTop").click(function(){
		alert("click top button");
		$('html,body').animate({scrollTop:0},1000);
	});
})

function doPost(to, p) {  // to:提交动作（action）,p:参数
	alert(to);
	
	var reqArticleForm = document.createElement("form");     
	reqArticleForm.method = "post";
	reqArticleForm.action = to; 
	for (var i in p){    
       	var paramInput = document.createElement("input");     
       	paramInput.setAttribute("name", i);  //为input对象设置name
       	paramInput.setAttribute("value", p[i]);  //为input对象设置value
       	reqArticleForm.appendChild(paramInput);
       	alert(i+p[i]);
     }   
     document.body.appendChild(reqArticleForm);   
     reqArticleForm.submit(); 
     document.body.removeChild(reqArticleForm);
}


function pageFunction(to, map){
	$("#about").hide();
	$("#contact").hide();
	$("#detail").hide();
	$("#preNext").hide();
	
	alert("pageFunction:"+to+","+map);
	var firstPage = 1;
    $.ajax({
        url: to,
        datatype: 'json',
        type: "Post",
        data: convertAjaxData(firstPage, map),
        success: function (data) {
        	alert("success:"+data);
        	var html = '';
            if (data != null) {
                $.each(eval("(" + data + ")").list, function (index, item) { //遍历返回的json
                	html += '<div class="post">'+
                		'<h3>'+
                		'<a href="javascript:void(0);" onclick="doPost(\'article/show\', {\'articleKey\':\''+item.id+'\'})">'+item.title+
                		'</a>'+
                		'</h3>'+
                		'<p class="text-muted">'+item.description+'</p>'+
                		'<ul class="list-inline">'+
                		'<li><i class="fa fa-calendar margin-r-5"></i>发表时间：'+item.createTime+'</li>'+
                		'<li><i class="fa fa-eye margin-r-5"></i>阅读数：'+item.readNum+'</li>'+
                		'<li><i class="fa fa-comments-o margin-r-5"></i>评论数：'+item.commentNum+'</li>'+
                		'</ul>'+
                		'</div>';
                	
                });
                $("#articlePanel").empty();
                $("#articlePanel").append(html);
                
                $('#homePaginator').empty();
                var totalPages = eval("(" + data + ")").totalPages; //取到totalPages的值(把返回数据转成object类型)
                var currentPage = eval("(" + data + ")").currentPage; //得到urrentPage
                var options = {
            	    currentPage: currentPage,    
            	    totalPages: totalPages,    
            	    size:"large",    
            	    bootstrapMajorVersion: 3,    
            	    alignment:"center",    
            	    numberOfPages:5,
            	    useBootstrapTooltip: true, 
            	    tooltipTitles: function (type, page, current) {
                        switch (type) {
                        case "first":
                            return "跳转到首页";
                        case "prev":
                            return "跳转到上一页";
                        case "next":
                            return "跳转到下一页";
                        case "last":
                            return "跳转到尾页";
                        case "page":
                            return "跳转到第" + page + "页";
                        }
                    },
            	    shouldShowPage:true,
            	    itemContainerClass: function (type, page, current) {
                        return (type=="page" && page === current) ? "active" : "pointer-cursor"
                    },
            	    itemTexts: function (type, page, current) {        
            	        switch (type) {            
            	        case "first": return "首页";            
            	        case "prev": return "上一页";            
            	        case "next": return "下一页";            
            	        case "last": return "末页";            
            	        case "page": return page;
            	        }
            	    },//点击事件，用于通过Ajax来刷新整个list列表
                    onPageClicked: function (event, originalEvent, type, page) {
                        $.ajax({
                            url: to,
                            type: "Post",
                            data: convertAjaxData(page, map),
                            success: function (result) {
                            	alert("page result:"+result)
                                if (result != null) {
                                	html = '';
                                	$.each(eval("(" + result + ")").list, function (index, item) { //遍历返回的json
                                		html += '<div class="post">'+
	                                		'<h3>'+
	                                		'<a href="javascript:void(0);" onclick="doPost(\'article/show\', {\'articleKey\':\''+item.id+'\'})">'+item.title+
	                                		'</a>'+
	                                		'</h3>'+
	                                		'<p class="text-muted">'+item.description+'</p>'+
	                                		'<ul class="list-inline">'+
	                                		'<li><i class="fa fa-calendar margin-r-5"></i>发表时间：'+item.createTime+'</li>'+
	                                		'<li><i class="fa fa-eye margin-r-5"></i>阅读数：'+item.readNum+'</li>'+
	                                		'<li><i class="fa fa-comments-o margin-r-5"></i>评论数：'+item.commentNum+'</li>'+
	                                		'</ul>'+
	                                		'</div>';
                                    });
                                	$("#articlePanel").empty();
                                    $("#articlePanel").append(html);
                                }
                            }
                        });
                    }
            	};
                $('#homePaginator').bootstrapPaginator(options);
            }
        }
    });
}

//分页
function convertAjaxData(page, map){
	var data = "page="+page;
	for (var i in map){
		data = data + "&" + i + "=" + map[i];
     }
	alert("组装分页请求数据"+data);
	
	return data;
}

//无分页
function convertAjaxDataNP(map){
	var data = "";
	for (var i in map){
		data = data + "&" + i + "=" + map[i];
     }
	alert("组装分页请求数据"+data);
	
	return data;
}

//回车换行符转换
var convertLineCtrl=function(strValue){
	return strValue.replace(/\r\n/g, '<br/>').replace(/\r/g, '<br/>').replace(/\n/g, '<br/>').replace(/\s/g, '&nbsp;');
}