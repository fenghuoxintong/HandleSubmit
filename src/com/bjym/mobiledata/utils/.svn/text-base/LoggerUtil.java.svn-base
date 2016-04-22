package com.bjym.mobiledata.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.log4j.Logger;

public class LoggerUtil {
	static {
		// org.apache.log4j.PropertyConfigurator.configure("log4j.properties");

		String classPath = ToolUtil.getClassPath();
		try {
			org.apache.log4j.PropertyConfigurator
					.configure(new FileInputStream(classPath
							+ "/log4j.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static final Logger serviceLog = Logger.getLogger("serviceLog");

	public static void info(Object obj) {
		serviceLog.info(obj);
	}

	public static void error(Object obj) {
		serviceLog.error(obj);
	}
}
