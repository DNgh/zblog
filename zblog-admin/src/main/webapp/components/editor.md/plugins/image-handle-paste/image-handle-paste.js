/*!
 * editormd图片粘贴上传插件
 *
 * @file   image-handle-paste.js
 * @author zhangkaixing
 * @date   2019-09-23
 * @link   https://www.codehui.net
 */

(function() {

    var factory = function (exports) {
        var $            = jQuery;           // if using module loader(Require.js/Sea.js).
        var pluginName   = "image-handle-paste";  // 定义插件名称

        //图片粘贴上传方法
        exports.fn.imagePaste = function() {
            var _this       = this;
            var cm          = _this.cm;
            var settings    = _this.settings;
            var editor      = _this.editor;
            var classPrefix = _this.classPrefix;
            var id       = _this.id;
            
            var loading = function(dialog, show) {
                var _loading = dialog.find("." + classPrefix + "dialog-mask");
                _loading[(show) ? "show" : "hide"]();
            };

            if(!settings.imageUpload || !settings.imageUploadURL){
                console.log('你还未开启图片上传或者没有配置上传地址');
                return false;
            }

            //监听粘贴板事件
            $('#' + id).on('paste', function (e) {

                var items = (e.clipboardData || e.originalEvent.clipboardData).items;

                //判断图片类型
                if (items && items[0].type.indexOf('image') > -1) {

                    var file = items[0].getAsFile();

                    /*生成blob
                    var blobImg = URL.createObjectURL(file);
                    */

                    /*base64
                    var reader = new FileReader();
                    reader.readAsDataURL(file);
                    reader.onload = function (e) {
                        var base64Img = e.target.result //图片的base64
                    }
                    */

                    // 创建FormData对象进行ajax上传
                    var forms = new FormData(document.forms[0]); //Filename
                    var fileName=new Date().getTime()+"."+file.name.split(".").pop();
                    forms.append(classPrefix + "image-file", file, fileName); // 文件

                    //调用imageDialog插件，弹出对话框
                    _this.executePlugin("imageDialog", "image-dialog/image-dialog");
                    
                    _ajax(settings.imageUploadURL, forms, function(ret){
                        if(ret.success == 1){
                            //数据格式可以自定义，但需要把图片地址写入到该节点里面
                            $("." + classPrefix + "image-dialog").find("input[data-url]").val(ret.url);
                            //cm.replaceSelection("![](" + ret.url  + ")");
                        }else{
                        	alert("上传失败:"+ret.message);
                        }
                    })
                }
            })
            
            // ajax上传图片 可自行处理
	        var _ajax = function(url, data, callback) {
            	 //显示进度
                loading($("." + classPrefix + "image-dialog"), true);
                
	            $.ajax({
	                "type": 'post',
	                "cache": false,
	                "url": url,
	                "data": data,
	                "processData": false,
	                "contentType": false,
	                "mimeType": "multipart/form-data",
	                success: function(ret){
	                	//隐藏进度
	                    loading($("." + classPrefix + "image-dialog"), false);
	                	if ((typeof ret) == 'string') {
	                		callback(JSON.parse(ret));
	                	} else {
	                		callback(ret);
	                	}
	                    
	                },
	                error: function (err){
	                	//隐藏进度
	                    loading($("." + classPrefix + "image-dialog"), false);
	                    alert('请求失败:'+err);
	                }
	            })
	        }
        };
    };

    // CommonJS/Node.js
    if (typeof require === "function" && typeof exports === "object" && typeof module === "object")
    {
        module.exports = factory;
    }
    else if (typeof define === "function")  // AMD/CMD/Sea.js
    {
        if (define.amd) { // for Require.js

            define(["editormd"], function(editormd) {
                factory(editormd);
            });

        } else { // for Sea.js
            define(function(require) {
                var editormd = require("./../../editormd");
                factory(editormd);
            });
        }
    }
    else
    {
        factory(window.editormd);
    }

})();