package com.min.zblog.facility.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class CommonUtils {
	public static Map<String, Object> convertToMap(Object obj){
		if(obj == null){
            return null;  
        }
		
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			Class<?> objClass = obj.getClass();
			Field[] fields = objClass.getDeclaredFields();
			for(Field field:fields){
				field.setAccessible(true);
				map.put(field.getName(), field.get(obj));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return map;
	}
}
