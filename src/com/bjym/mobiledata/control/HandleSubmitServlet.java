package com.bjym.mobiledata.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bjym.mobiledata.Time.OnTime;
import com.bjym.mobiledata.Time.ThreadLocalSend;
import com.bjym.mobiledata.bean.LogSend;
import com.bjym.mobiledata.bean.MonitorBean;
import com.bjym.mobiledata.bean.UserReg;
import com.bjym.mobiledata.busbean.ResponseData;
import com.bjym.mobiledata.busbean.SubmitTask;
import com.bjym.mobiledata.db.LogSendDaoImp;
import com.bjym.mobiledata.db.RegUserDaoimp;
import com.bjym.mobiledata.db.utils.DBConstants;
import com.bjym.mobiledata.redis.impl.RedisImpl;
import com.bjym.mobiledata.redis.interfaces.RedisInterface;
import com.bjym.mobiledata.redis.utils.RedisConfigKeyConstants;
import com.bjym.mobiledata.utils.DateUtil;
import com.bjym.mobiledata.utils.JsonUtil;
import com.bjym.mobiledata.utils.LoggerUtil;
import com.bjym.mobiledata.utils.PropertiesUtil;
import com.bjym.mobiledata.utils.SerializeUtils;
import com.bjym.mobiledata.utils.ToolUtil;

public class HandleSubmitServlet extends HttpServlet {
	private RegUserDaoimp userDaoimp = new RegUserDaoimp();
	private LogSendDaoImp logSendDaoImp = new LogSendDaoImp();
	private RedisInterface redisInterface = new RedisImpl();
	public static Boolean IsSend=false;
	public static  Integer allcout=0;
	public static Integer success=0;
	public static Integer fail=0;

	static{
		OnTime time=new OnTime("20001",20);
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("pragma", "no-cache");
		response.setHeader("expires", "0");

		Date recvTime = new Date(System.currentTimeMillis());

		PrintWriter out = response.getWriter();

		String submitStr = request.getParameter("param");

		// LoggerUtil.info("提交数据:" + submitStr);

		JSONObject obj = JsonUtil.strToJSON(submitStr);
		String username = String.valueOf(obj.get("clientID"));
		String password = String.valueOf(obj.get("share_secret"));
		String requestid = String.valueOf(obj.get("requestid"));
		if (requestid == null || "null".equalsIgnoreCase(requestid)
				|| "".equals(requestid)) {
			requestid = "0";
		}

		// 生成 sendID
		String sendID = ToolUtil.getUUID();

		// 用户身份鉴权
		UserReg userReg = userDaoimp.login(username, password);
		if (userReg == null) {
			allcout++;
			fail++;
			responseInterupe(out, sendID, requestid, "5");
			return;
		}

		// ip鉴权
		String sourceIP = getIpAddr(request);
		String databaseip = userReg.getServerip();
		LoggerUtil.info("提交数据ip:" + sourceIP + ",服务器ip:" + databaseip);
		if (!validateServerIP(databaseip, sourceIP)) {
			allcout++;
			fail++;
			responseInterupe(out, sendID, requestid, "9");
			return;
		}

		int userID = userReg.getUSER_ID();

		// 预充值用户 余额校验
		if (userReg.getTotalBalance() != -1) {
			int totalBalance = userReg.getTotalBalance();

			// 此处 225 为最小金额 ， 最好写入配置...
			if (totalBalance <= 0 || totalBalance < 225) {
				allcout++;
				fail++;
				responseInterupe(out, sendID, requestid, "7");
				return;
			}
		}

		List<LogSend> logsendList = new ArrayList<LogSend>();

		String userdataList = String.valueOf(obj.get("userdataList"));
		JSONArray jsonArray = JsonUtil.strToJSONArray(userdataList);

		List<byte[]> taskList = new ArrayList<byte[]>();

		int size = jsonArray.size();
		if (size == 0) {
			allcout++;
			fail++;
			responseInterupe(out, sendID, requestid, "10");
			return;
		}

		for (int i = 0; i < size; i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			String userPackage = String.valueOf(jsonObject.get("userPackage"));

			String mobiles = String.valueOf(jsonObject.get("mobiles"));

			String[] mobArr = mobiles.split("\\,");

			if (mobArr.length == 1) {
				String mobile = mobArr[0];
				if ("".equals(mobile) || mobile == null
						|| mobile.length() != 11) {
					allcout++;
					fail++;
					responseInterupe(out, sendID, requestid, "6");
					return;
				}
			}

			for (int j = 0; j < mobArr.length; j++) {
				allcout++;
				String mobile = mobArr[j];
				if ("".equals(mobile) || mobile.length() <= 8 || mobile == null) {
					continue;
				}
				LogSend logSend = new LogSend();
				logSend.setUserID(userID);
				logSend.setRequestid(requestid);
				String logID = ToolUtil.getUUID();
				LoggerUtil.info(logID);
				logSend.setLogID(logID);
				logSend.setSendID(sendID);
				logSend.setDesMobile(mobile);
				logSend.setMaskpackage(userPackage);
				logSend.setRecvTime(recvTime);

				logsendList.add(logSend);
				SubmitTask submitTask = new SubmitTask(DateUtil.DateToString(
						new Date(), "yyyy-MM-dd HH:mm:ss"), "", logSend,
						userReg);
				try {
					taskList.add(SerializeUtils.serialize(submitTask));
				} catch (Exception e) {
					LoggerUtil
							.error("submittask对象转存byte数组异常:" + e.getMessage());
					responseInterupe(out, sendID, requestid, "3");
					return;
				}
				success++;
			}
		}

		
		if (redisInterface.addByteToList(
				RedisConfigKeyConstants.logic_redis_listname, taskList) == 1) {
			responseSuccess(out, sendID, requestid);
			return;
		} else {
			for (int i = 0; i < logsendList.size(); i++) {
				LogSend logSend = logsendList.get(i);
				logSend.setEcode(DBConstants.ecode_redis_submitfail);
				logSend.setEcodeDes("task"
						+ DBConstants.ecodedesc_redis_submitfail);
				logsendList.set(i, logSend);
			}
			logSendDaoImp.addLogsends(logsendList,
					DBConstants.logSend_Type_Submitfail);
			responseInterupe(out, sendID, "3", requestid);
			return;
		}
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

	private void responseSuccess(PrintWriter out, String sendID,
			String requestid) {
		String success = "ETB_superpose_response_success";
		String success_desc = "ETB_superpose_response_successDesc";

		ResponseData responseData = new ResponseData(sendID, requestid,
				PropertiesUtil.getResource(success), PropertiesUtil
						.getResource(success_desc));
		String resp = JsonUtil.objToString(responseData);
		// LoggerUtil.info(resp);
		out.println(resp);
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

	public static void main(String[] args) {
		String s = DateUtil.DateToString(new Date(), "yyyyMMddHHmmssSSS");
		System.out.println(s);
	}
}
