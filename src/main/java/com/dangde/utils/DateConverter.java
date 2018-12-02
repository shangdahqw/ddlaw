package com.dangde.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class DateConverter implements Converter<String, Date> {

	public Date convert(String source) {
		
		if(!source.equals("")){
			
			SimpleDateFormat format;
			if(source.length()==10){
				 format=new SimpleDateFormat("yyyy-MM-dd");

			}else{
				 format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
			}
			
			
			Date date=null;
			try {
				date = format.parse(source);
			} catch (ParseException e) {
				e.printStackTrace();;
			}
			return date;
		}
		
		return null;
		
	}


}
