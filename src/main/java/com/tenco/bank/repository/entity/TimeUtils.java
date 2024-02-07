package com.tenco.bank.repository.entity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TimeUtils {
	public static String timestampToString(Timestamp timestamp) {
		String currentTimestampToString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timestamp);
		return currentTimestampToString;
	};
	
	
}
