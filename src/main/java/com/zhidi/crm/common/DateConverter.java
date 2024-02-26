package com.zhidi.crm.common;

import java.util.Calendar;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
/**
 *  yyyy-MM-dd
 * @author jqllxew
 *
 */
public class DateConverter implements Converter<String,Date>{

	@Override
	public Date convert(String source) {
		
		String[] split = source.split("-");
		Calendar calendar = Calendar.getInstance();
		if(split.length > 2){
			calendar.set(Integer.parseInt(split[0]), Integer.parseInt(split[1])-1, Integer.parseInt(split[2]));
			return calendar.getTime();
		}
		if(split.length > 1){
			calendar.set(Integer.parseInt(split[0]), Integer.parseInt(split[1])-1, 1);
			return calendar.getTime();
		}
		return null;
	}
}
