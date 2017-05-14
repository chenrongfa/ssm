package com.yy.ssm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.core.convert.converter.Converter;
import org.springframework.format.datetime.DateFormatter;

public class StringToDate implements Converter<String, Date>{
	
	@Override
	public Date convert(String source) {
		// TODO Auto-generated method stub
		DateFormatter date=new DateFormatter("yyyy-MM-dd");
		Locale locale=new Locale("zh_CH");
		Date time=null;
		try {
			time=date.parse(source, locale);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return time;
	}

}
