package com.bjym.mobiledata.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpUtil {

	public static String sendGet(String url, String param) {
		StringBuffer stringBuffer = new StringBuffer();
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			URLConnection conn = realUrl.openConnection();
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("Content-Type", "multipart/form-data");
			// 设置连接超时
			conn.setConnectTimeout(3 * 1000);
			// 设置读超时
			conn.setReadTimeout(3 * 1000);
			conn.connect();
			Map<String, List<String>> map = conn.getHeaderFields();

			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				stringBuffer.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return stringBuffer.toString();
	}

	public static String sendPost(String urls, String param) {
		String result = "";
		URL url = null;
		HttpURLConnection httpurl = null;
		try {
			url = new URL(urls);
			httpurl = (HttpURLConnection) url.openConnection();
			// 设置连接超时
			httpurl.setConnectTimeout(3 * 1000);
			// 设置读超时
			httpurl.setReadTimeout(3 * 1000);
			httpurl.setRequestMethod("POST");
			httpurl.setRequestProperty("content-type", "multipart/form-data");
			httpurl.setDoOutput(true);
			OutputStream out = httpurl.getOutputStream();
			out.write(param.getBytes("UTF-8"));
			out.flush();
			out.close();

			BufferedReader bufferreader = new BufferedReader(
					new InputStreamReader(httpurl.getInputStream(), "UTF-8"));
			StringBuffer stringbuffer = new StringBuffer();
			int ch;
			while ((ch = bufferreader.read()) > -1) {
				stringbuffer.append((char) ch);
			}
			result = stringbuffer.toString().trim();
			bufferreader.close();
			httpurl.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String upload(String urls, String xmlheader, String xmlbody) {
		StringBuffer stringBuffer = new StringBuffer();

		List<String> list = new ArrayList<String>(); // 要上传的文件名,如：d:\haha.doc.你要实现自己的业务。我这里就是一个空list.
		try {
			String BOUNDARY = "---------7d4a6d158c9"; // 定义数据分隔线
			URL url = new URL(urls);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 设置连接超时
			conn.setConnectTimeout(3 * 1000);
			// 设置读超时
			conn.setReadTimeout(10 * 1000);

			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			conn.setRequestProperty("Charsert", "UTF-8");
			conn.setRequestProperty("Content-Type",
					"multipart/form-data; boundary=" + BOUNDARY);

			OutputStream out = new DataOutputStream(conn.getOutputStream());
			byte[] end_data = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();// 定义最后数据分隔线

			// =======head部分========
			StringBuilder sb = new StringBuilder();
			sb.append("--");
			sb.append(BOUNDARY);
			sb.append("\r\n");
			sb.append("Content-Disposition: form-data;name=\"xmlhead\"; \r\n");
			sb.append("Content-Type:text/plain;charset=utf-8\r\n\r\n");
			byte[] data = sb.toString().getBytes();
			out.write(data);
			out.write(xmlheader.getBytes());
			out.write("\r\n".getBytes()); // 多个文件时，二个文件之间加入这个
			// =======head end========

			// =======body部分========
			StringBuilder sb1 = new StringBuilder();
			sb1.append("--");
			sb1.append(BOUNDARY);
			sb1.append("\r\n");
			sb1.append("Content-Disposition: form-data;name=\"xmlbody\"; \r\n");
			sb1.append("Content-Type:text/plain;charset=utf-8\r\n\r\n");
			byte[] data1 = sb1.toString().getBytes();
			out.write(data1);
			out.write(xmlbody.getBytes());
			out.write("\r\n".getBytes()); // 多个文件时，二个文件之间加入这个
			// =======body end========

			out.write(end_data);
			out.flush();
			out.close();

			// 定义BufferedReader输入流来读取URL的响应
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "utf-8"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				stringBuffer.append(line);
			}

		} catch (Exception e) {
			LoggerUtil.info("发送POST请求出现异常:" + e);
			e.printStackTrace();
		}

		return stringBuffer.toString();
	}

}
