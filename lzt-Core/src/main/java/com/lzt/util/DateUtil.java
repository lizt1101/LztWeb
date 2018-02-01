package com.lzt.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static Timestamp getTimeStamp(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(new Date());
		Timestamp timestamp = Timestamp.valueOf(date);
		return timestamp;
	}

	public static String getYear(Date createDate){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		return sdf.format(createDate);
	}

	public static String getYMD(Date createDate){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(createDate);
	}

	public static void main(String[] args) {
		System.out.println(DateUtil.getTimeStamp());
	}
}
