package com.bjym.mobiledata.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class PropertiesUtil {
	private static ResourceBundle appBundle;
	private static ResourceBundle resourceBundle;
	private static ResourceBundle c3p0Bundle;

	private static Map<String, String> appMap = new HashMap<String, String>();
	private static Map<String, String> resourceMap = new HashMap<String, String>();
	private static Map<String, String> c3p0Map = new HashMap<String, String>();

	static {
		InputStream is = null;
		try {
			String classPath = ToolUtil.getClassPath();

			is = new FileInputStream(new File(classPath
					+ "resource.properties"));

			appBundle = new PropertyResourceBundle(is);

			Enumeration<String> e = appBundle.getKeys();
			while (e.hasMoreElements()) {
				String key = e.nextElement();
				String value = appBundle.getString(key);
				appMap.put(key, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			String classPath = ToolUtil.getClassPath();

			is = new FileInputStream(
					new File(classPath + "resource.properties"));

			resourceBundle = new PropertyResourceBundle(is);

			Enumeration<String> e = resourceBundle.getKeys();
			while (e.hasMoreElements()) {
				String key = e.nextElement();
				String value = resourceBundle.getString(key);
				resourceMap.put(key, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			is = PropertiesUtil.class.getResourceAsStream("/c3p0.properties");
			c3p0Bundle = new PropertyResourceBundle(is);

			Enumeration<String> e = c3p0Bundle.getKeys();
			while (e.hasMoreElements()) {
				String key = e.nextElement();
				String value = c3p0Bundle.getString(key);
				c3p0Map.put(key, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String getParameterApplication(String name) {
		return appMap.get(name);
	}

	public static String getResource(String name) {
		return resourceMap.get(name);
	}

	public static String getParameterC3P0(String name) {
		return c3p0Map.get(name);
	}
}
