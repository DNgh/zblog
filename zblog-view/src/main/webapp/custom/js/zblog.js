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
		
})
