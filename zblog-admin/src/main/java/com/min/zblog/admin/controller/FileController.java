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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file")
public class FileController {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Value("#{env['rootPath']?:'/home/files'}")
	String rootPath;
	
	private SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
	
	@ResponseBody
	@RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public Map<String,Object> uploadImage(HttpServletRequest request, HttpServletResponse response, 
    		@RequestParam(value = "editormd-image-file", required = false) MultipartFile file){
        logger.debug("进入uploadImage");
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
        try {
            /*request.setCharacterEncoding( "utf-8" );
            response.setHeader( "Content-Type" , "text/html" );*/
            
            //用于保存上传文件的保存路径：/home/files/yyyyMMdd/
            String dateString = dateformat.format(new Date());
            StringBuffer filePathBuffer = new StringBuffer();
            String filePath = filePathBuffer.append(rootPath).append(File.separator)
            		.append(dateString).toString();
            //文件路径不存在，则创建
            File fileDir = new File(filePath);
            if(!fileDir.exists()){
            	fileDir.mkdirs();
            }
            
            //重命名文件名：uuid-time.原文件后缀
            UUID uuid = UUID.randomUUID();
            long time = System.currentTimeMillis();
            //原始文件名
            String originName = file.getOriginalFilename();
            StringBuffer nameBuffer = new StringBuffer();
            //新文件名
            String newName = nameBuffer.append(uuid.toString()).append("-").append(time)
            		.append(originName.substring(originName.lastIndexOf('.'))).toString();
            //生成绝对路径文件名，写入文件
            String absoluteFileName = filePathBuffer.append(File.separator).append(newName).toString();
            File realFile = new File(absoluteFileName);
            FileUtils.copyInputStreamToFile(file.getInputStream(), realFile);
            
            //回传文件路径：域名/image/yyyyMMdd/文件名
            StringBuffer url = request.getRequestURL();
            String showUrl = url.delete(url.length() - request.getRequestURI().length(), url.length())
            		.append("/image/").append(dateString).append("/").append(newName).toString();
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
