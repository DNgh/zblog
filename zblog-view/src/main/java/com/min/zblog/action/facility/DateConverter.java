package com.min.zblog.action.facility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.conversion.TypeConversionException;

public class DateConverter extends StrutsTypeConverter {
	private final DateFormat[] sdf = {
		new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒"),
		new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
	};
	
	@Override
	public Object convertFromString(Map context, String[] values, Class toType) {
		String dateStr = values[0];
		for(int i=0;i<sdf.length;i++) {
			try {
				return sdf[i].parse(dateStr);
			} catch (Exception e) {
				continue;
			}
		}
		throw new TypeConversionException();
	}

	@Override
	public String convertToString(Map context, Object object) {
		Date date = (Date)object;
		
		return sdf[1].format(date);
	}

}
