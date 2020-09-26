package com.min.zblog;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

import com.min.zblog.facility.utils.ImageUtil;

/**
 * <p>Title: ImageUtilTest</p>
 * <p>Description: </p>
 * @author	zhouzm
 * @date	2020年9月27日
 * @version 1.0
 */
public class ImageUtilTest {
	public static void main(String[] args) {
		try {
			compressImg();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void compressImg() throws Exception{
		byte[] readAllBytes = Files.readAllBytes(new File("/Users/zhiminzhou/Documents/temp/img/2.jpg").toPath());
		//压缩图片到指定120K以内,不管你的图片有多少兆,都不会超过120kb,精度还算可以,不会模糊
        byte[] bytes = ImageUtil.compressPicForScale(readAllBytes, 120);
        FileOutputStream fos = new FileOutputStream(new File("/Users/zhiminzhou/Documents/temp/img/2_compress.jpg"));
        fos.write(bytes);
        fos.close();
	}
}
