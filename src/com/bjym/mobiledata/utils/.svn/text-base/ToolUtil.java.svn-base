package com.bjym.mobiledata.utils;

import java.io.File;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToolUtil {
	public static synchronized String getUUID() {
		char[] cArr = { 'A', 'B', 'C', 'D', 'E', 'F' };
		Random random = new Random();
		char prefix = cArr[random.nextInt(6)];
		String suffix = String.valueOf(new Random().nextInt(100000));
		String base = String.valueOf(System.currentTimeMillis());
		return prefix + base + suffix;
	}

	public static String getSessionID() {
		char[] cArr = { 'A', 'B', 'C', 'D', 'E', 'F' };
		Random random = new Random();
		char suffix = cArr[random.nextInt(6)];

		long timeStamp = System.currentTimeMillis();

		int randomNum = random.nextInt(99);
		String str = String.valueOf(suffix) + String.valueOf(timeStamp)
				+ randomNum;

		return str;
		// return String.valueOf(timeStamp + randomNum);

	}

	public static String getProductID() {
		long timeStamp = System.currentTimeMillis();

		Random random = new Random();
		int randomNum = random.nextInt(9999);
		return String.valueOf(timeStamp + randomNum);
	}

	public static boolean isMobileNO(String mobile) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobile);
		return m.matches();
	}

	public static String getClassPath() {
		String classPath = ToolUtil.class.getResource("/").getPath();

		String rootPath = "";
		// windows
		if ("\\".equals(File.separator)) {
			rootPath = classPath.substring(1, classPath
					.indexOf("/WEB-INF/classes"));
			rootPath = rootPath.replace("/", "\\");
		}
		// linux
		if ("/".equals(File.separator)) {
			rootPath = classPath.substring(0, classPath
					.indexOf("/WEB-INF/classes"));
			rootPath = rootPath.replace("\\", "/");
		}
		return classPath;
	}

	public static String getAppClassPath() {
		return System.getProperty("user.dir");
	}

	public static void main(String[] args) {
		System.out.println(ToolUtil.getUUID());
	}
}
