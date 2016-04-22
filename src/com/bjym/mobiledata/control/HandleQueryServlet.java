package com.bjym.mobiledata.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.bjym.mobiledata.bean.LogSend;
import com.bjym.mobiledata.bean.UserReg;
import com.bjym.mobiledata.busbean.BalanceResp;
import com.bjym.mobiledata.busbean.ReportMessage;
import com.bjym.mobiledata.busbean.ReportResp;
import com.bjym.mobiledata.busbean.ResponseData;
import com.bjym.mobiledata.db.LogSendDaoImp;
import com.bjym.mobiledata.db.RegUserDaoimp;
import com.bjym.mobiledata.utils.DateUtil;
import com.bjym.mobiledata.utils.JsonUtil;
import com.bjym.mobiledata.utils.LoggerUtil;
import com.bjym.mobiledata.utils.PropertiesUtil;
import com.bjym.mobiledata.utils.ToolUtil;

public class HandleQueryServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String requestURL = request.getRequestURL().toString();

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("pragma", "no-cache");
		response.setHeader("expires", "0");

		PrintWriter out = response.getWriter();

		String sendID = ToolUtil.getUUID();
		String submitStr = request.getParameter("param");
		// LoggerUtil.info("提交数据:" + submitStr);

		if (requestURL.indexOf("querybalance") != -1) {
			JSONObject obj = null;
			String type = null;
			try {
				obj = JsonUtil.strToJSON(submitStr);
				type = String.valueOf(obj.get("type"));
				if ("1".equals(type)) {
					queryBalance(request, response, obj);
					return;
				}
			} catch (Exception e) {
				responseInterupe(out, sendID, "0", "10");
				return;
			}
		} else if (requestURL.indexOf("querystatus") != -1) {
			JSONObject obj = null;
			String type = null;
			try {
				obj = JsonUtil.strToJSON(submitStr);
				queryStatus(request, response, obj);
				return;
			} catch (Exception e) {
				e.printStackTrace();
				responseInterupe(out, sendID, "0", "10");
				return;
			}
		} else if (requestURL.indexOf("test") != -1) {
			String param = request.getParameter("param");
			// LoggerUtil.info("param参数:" + param);
		}
	}

	private void queryStatus(HttpServletRequest request,
			HttpServletResponse response, JSONObject obj) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
		}

		String username = String.valueOf(obj.get("clientID"));
		String password = String.valueOf(obj.get("share_secret"));

		RegUserDaoimp userDaoimp = new RegUserDaoimp();

		String sendID = ToolUtil.getUUID();
		UserReg userReg = userDaoimp.login(username, password);
		if (userReg == null) {
			responseInterupe(out, sendID, "0", "5");
			return;
		}

		// ip鉴权
		String sourceIP = getIpAddr(request);
		String databaseip = userReg.getServerip();
		// fo("提交数据ip:" + sourceIP + ",服务器ip:" + databaseip + "\r\n");

		if (!validateServerIP(databaseip, sourceIP)) {
			responseInterupe(out, sendID, "0", "9");
			return;
		}

		LogSendDaoImp logSendDaoImp = new LogSendDaoImp();

		// 获取数据
		String type = String.valueOf(obj.get("type"));
		String sendid = String.valueOf(obj.get("sendid"));
		String mobiles = "";
		String requestid = String.valueOf(obj.get("requestid"));
		if ("3".equals(type)) {
			requestid = String.valueOf(obj.get("requestid"));
			if ("".equals(requestid) || requestid == null) {
				out.println("查询方式不正确type=3时,requestid不能为空");
				out.flush();
				out.close();
			}

			mobiles = String.valueOf(obj.get("mobile"));

			ReportResp reportResp = new ReportResp();
			reportResp.setType(type);
			int userID = userReg.getUSER_ID();
			StringBuffer buffer = new StringBuffer();
			if (!"".equals(mobiles)) {
				String[] mobileArr = mobiles.split("\\,");
				int size = mobileArr.length;
				for (int i = 0; i < size; i++) {
					if (i != size - 1) {
						buffer.append("'").append(mobileArr[i]).append("'")
								.append(",");
					} else {
						buffer.append("'").append(mobileArr[i]).append("'");
					}
				}
			}

			List<LogSend> logsendList = logSendDaoImp.find(userID, requestid,
					buffer.toString(), type);
			int size = logsendList.size();
			reportResp.setSize(size);

			if (size == 0) {
				String resp = JsonUtil.objToString(reportResp);
				// LoggerUtil.info("querystatus 响应数据:" + resp + "\r\n");
				out.println(resp);
				out.flush();
				out.close();
				return;
			}

			List<ReportMessage> messageList = new ArrayList<ReportMessage>();
			for (LogSend logSend : logsendList) {
				messageList.add(new ReportMessage(logSend.getDesMobile(),
						logSend.getRcode(), logSend.getSendID(), DateUtil
								.DateToString_yyyyMMddHHmmss(logSend
										.getRecvTime()), logSend
								.getMaskpackage(), requestid));
			}
			reportResp.setMessageList(messageList);

			String resp = JsonUtil.objToString(reportResp);

			// LoggerUtil.info("querystatus 响应数据:" + resp + "\r\n");
			out.println(resp);
			out.flush();
			out.close();
			return;

		}

		// type == 1 or 2 的情况
		if ("1".equals(type)) {
			mobiles = String.valueOf(obj.get("mobile"));
		}

		ReportResp reportResp = new ReportResp();
		reportResp.setType(type);
		int userID = userReg.getUSER_ID();
		StringBuffer buffer = new StringBuffer();
		if (!"".equals(mobiles)) {
			String[] mobileArr = mobiles.split("\\,");
			int size = mobileArr.length;
			for (int i = 0; i < size; i++) {
				if (i != size - 1) {
					buffer.append("'").append(mobileArr[i]).append("'").append(
							",");
				} else {
					buffer.append("'").append(mobileArr[i]).append("'");
				}
			}
		}

		List<LogSend> logsendList = logSendDaoImp.find(userID, sendid, buffer
				.toString(), type);
		int size = logsendList.size();
		reportResp.setSize(size);

		if (size == 0) {
			String resp = JsonUtil.objToString(reportResp);
			// LoggerUtil.info("querystatus 响应数据:" + resp + "\r\n");
			out.println(resp);
			out.flush();
			out.close();
			return;
		}

		List<ReportMessage> messageList = new ArrayList<ReportMessage>();
		for (LogSend logSend : logsendList) {
			messageList.add(new ReportMessage(logSend.getDesMobile(), logSend
					.getRcode(), logSend.getSendID(), DateUtil
					.DateToString_yyyyMMddHHmmss(logSend.getRecvTime()),
					logSend.getMaskpackage(), requestid));
		}
		reportResp.setMessageList(messageList);

		String resp = JsonUtil.objToString(reportResp);

		// LoggerUtil.info("querystatus 响应数据:" + resp + "\r\n");
		out.println(resp);
		out.flush();
		out.close();
		return;
	}

	private void queryBalance(HttpServletRequest request,
			HttpServletResponse response, JSONObject obj) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
		}

		String username = String.valueOf(obj.get("clientID"));
		String password = String.valueOf(obj.get("share_secret"));
		RegUserDaoimp userDaoimp = new RegUserDaoimp();

		String sendID = ToolUtil.getUUID();
		UserReg userReg = userDaoimp.login(username, password);
		if (userReg == null) {
			responseInterupe(out, sendID, "0", "5");
			return;
		}

		// ip鉴权
		String sourceIP = getIpAddr(request);
		String databaseip = userReg.getServerip();
		// LoggerUtil.info("提交数据ip:" + sourceIP + ",服务器ip:" + databaseip +
		// "\r\n");

		if (!validateServerIP(databaseip, sourceIP)) {
			responseInterupe(out, sendID, "0", "9");
			return;
		}

		int totalBalance = userReg.getTotalBalance();
		int usedBalance = userReg.getUsedBalance();
		int dayExpense = userReg.getDayExpense();

		BalanceResp balanceResp = new BalanceResp();
		balanceResp.setMaxbalance((float) totalBalance / 100);
		balanceResp.setUsedbalance((float) usedBalance / 100);
		balanceResp.setDayexpense((float) dayExpense / 100);

		String resp = JsonUtil.objToString(balanceResp);
		// LoggerUtil.info("queryBalance 响应数据:" + resp + "\r\n");
		out.println(resp);
		out.flush();
		out.close();
	}

	private String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	private boolean validateServerIP(String dbServerIP, String sourceIP) {
		boolean pass = false;
		if (dbServerIP != null && !"".equals(dbServerIP)
				&& !"-".equals(dbServerIP)) {
			String[] iparr = dbServerIP.split("\\,");
			for (int j = 0; j < iparr.length; j++) {
				if (sourceIP.equals(iparr[j])) {
					pass = true;
					break;
				}
			}
		}
		return pass;
	}

	private void responseInterupe(PrintWriter out, String sendID,
			String requestid, String code) {
		String errorPrefix = "ETB_superpose_response_ERROR";
		String errorDesSuffix = "DESC";

		ResponseData responseData = new ResponseData(sendID, requestid,
				PropertiesUtil.getResource(errorPrefix + code), PropertiesUtil
						.getResource(errorPrefix + code + errorDesSuffix));
		String resp = JsonUtil.objToString(responseData);
		// LoggerUtil.info(resp);
		out.println(resp);
		out.close();
	}

	public static void main(String[] args) {
		String requetURL = "http://127.0.0.1/handlesubmit/querybalance";
		System.out.println(requetURL.indexOf("querystatus"));
	}
}
