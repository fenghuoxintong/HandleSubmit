package com.bjym.mobiledata.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static String DateToString(Date dd, String fmt) {
		String time = "";
		SimpleDateFormat tmp = new SimpleDateFormat(fmt);
		try {
			time = tmp.format(dd);
		} catch (Exception E) {
			time = "";
		}
		return time;
	}

	public static Date StringToDate(String DT, String fmt, Date DefaultDT) {
		Date dt;
		try {
			dt = new SimpleDateFormat(fmt).parse(DT);
		} catch (Exception E) {
			dt = DefaultDT;
		}
		return dt;
	}

	public static String DateToString_yyyyMMddHHmmssSSS(Date date) {
		return DateToString(date, "yyyyMMddHHmmssSSS");
	}

	public static String DateToString_yyyyMMddHHmmss(Date date) {
		return DateToString(date, "yyyyMMddHHmmss");
	}

	public static String DateToMM(Date date) {
		return DateToString(date, "MM");
	}

	public static Date StringToDate(String DT, String fmt) {
		return StringToDate(DT, fmt, new Date());
	}
}
