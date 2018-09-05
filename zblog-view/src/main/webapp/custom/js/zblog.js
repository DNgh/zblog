/**
 * ZBLOG JQuery
 * ------------------
 */
$(function () {
    
    var options = {
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
	$('.homePaginator').bootstrapPaginator(options);
    
    $(window).scroll(function(event){
    	var len = $(this).scrollTop();
		if(len >= 100){
			$("#goTop").show();
		}else{
			$("#goTop").hide();
		}
    });
    
	$("#goTop").click(function(event){
		event.preventDefault();
		$('body').animate({scrollTop:0},1000);
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

