package com.min.zblog.admin.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file")
public class FileController {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Value("#{env['rootPath']?:'/home/files'}")
	String rootPath;
	
	@RequestMapping("/uploadImage")
    public @ResponseBody
    Map<String,Object> Image(HttpServletRequest request, HttpServletResponse response, 
    		@RequestParam(value = "editormd-image-file", required = false) MultipartFile file){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        try {
//            request.setCharacterEncoding( "utf-8" );
//            response.setHeader( "Content-Type" , "text/html" );
            //文件路径不存在则需要创建文件路径,目录/home/file/yyyyMMdd/
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
            String dateString = dateformat.format(new Date());
            String filePath = rootPath + File.separator + dateString;
            File fileDir = new File(filePath);
            if(!fileDir.exists()){
            	fileDir.mkdirs();
            }
            //重命名文件名，uuid+time
            UUID uuid = UUID.randomUUID();
            long time = System.currentTimeMillis();
            String originName = file.getName();
            String newName = uuid.toString() + time + originName.substring(originName.lastIndexOf('.')+1);
            String absoluteFileName = filePath + File.separator + newName;
            File realFile = new File(absoluteFileName);
            FileUtils.copyInputStreamToFile(file.getInputStream(), realFile);
            
            //访问文件路径
            StringBuffer url = request.getRequestURL();
            String showUrl = url.delete(url.length() - request.getRequestURI().length(), url.length())
            		.append("/file/show/").append(dateString).append(newName).toString();
            resultMap.put("success", 1);
            resultMap.put("message", "上传成功");
            resultMap.put("url", showUrl);
            
            return resultMap;
        } catch (Exception e) {
        	logger.error("上传文件异常", e);
        	resultMap.put("success", 0);
        	return resultMap;
        }
    }
}
