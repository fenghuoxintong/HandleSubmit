package com.bjym.mobiledata.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bjym.mobiledata.bean.LogSend;
import com.bjym.mobiledata.utils.DateUtil;
import com.bjym.mobiledata.utils.LoggerUtil;

public class LogSendDaoImp {
	public void addLogsend(LogSend logSend, String tablesuffix) {
		Connection con = null;
		PreparedStatement pst = null;

		String tableName = "logsend_";
		if (tablesuffix == null || "".equals(tablesuffix)) {
			String MM = DateUtil.DateToMM(new Date());
			tableName = tableName + MM;
		} else {
			tableName = tableName + tablesuffix;
		}

		String sql = "insert into "
				+ tableName
				+ " (LOG_ID,CREATE_TIME,SEQ,SESSION_ID,TRANSIDO,request_id,USER_ID,DESMOBILE,RECV_TIME,ECODE, ECODE_DES, ISP_ID, SENDID, province, city,vcode,PRODUCT_ID,USER_PACKAGE,maskpackage,price,status) value(?,sysdate(),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		con = DBUtils.getConnection();
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, logSend.getLogID());
			pst.setString(2, logSend.getSeq());
			pst.setString(3, logSend.getSessionID());
			pst.setString(4, logSend.getTransido());
			pst.setString(5, logSend.getRequestid());
			pst.setInt(6, logSend.getUserID());
			pst.setString(7, logSend.getDesMobile());
			pst.setTimestamp(8, new Timestamp(logSend.getRecvTime().getTime()));
			pst.setString(9, logSend.getEcode());
			pst.setString(10, logSend.getEcodeDes());
			pst.setInt(11, logSend.getIspID());

			// pst.setTimestamp(12, new Timestamp(logSend.getSendTime()
			// .getTime()));

			pst.setString(12, logSend.getSendID());
			pst.setString(13, logSend.getProvice());
			pst.setString(14, logSend.getCity());
			pst.setString(15, logSend.getVcode());
			pst.setString(16, logSend.getProductID());
			pst.setString(17, logSend.getUserPackage());
			pst.setString(18, logSend.getMaskpackage());
			pst.setInt(19, logSend.getPrice());
			pst.setInt(20, 0);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(pst, con);
		}
	}

	public void addLogsends(List<LogSend> logSendList, String tablesuffix) {
		Connection con = null;
		PreparedStatement pst = null;

		String tableName = "logsend_";
		if (tablesuffix == null || "".equals(tablesuffix)) {
			String MM = DateUtil.DateToMM(new Date());
			tableName = tableName + MM;
		} else {
			tableName = tableName + tablesuffix;
		}

		String sql = "insert into "
				+ tableName
				+ " (LOG_ID,CREATE_TIME,SEQ,SESSION_ID,TRANSIDO,request_id,USER_ID,DESMOBILE,RECV_TIME,ECODE, ECODE_DES, ISP_ID, SENDID, province, city,vcode,PRODUCT_ID,USER_PACKAGE,maskpackage,price,status) value(?,sysdate(),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		con = DBUtils.getConnection();
		try {
			con.setAutoCommit(false);
			pst = con.prepareStatement(sql);

			for (int i = 0; i < logSendList.size(); i++) {
				LogSend logSend = logSendList.get(i);
				pst.setString(1, logSend.getLogID());
				pst.setString(2, logSend.getSeq());
				pst.setString(3, logSend.getSessionID());
				pst.setString(4, logSend.getTransido());
				pst.setString(5, logSend.getRequestid());
				pst.setInt(6, logSend.getUserID());
				pst.setString(7, logSend.getDesMobile());
				pst.setTimestamp(8, new Timestamp(logSend.getRecvTime()
						.getTime()));
				pst.setString(9, logSend.getEcode());
				pst.setString(10, logSend.getEcodeDes());
				pst.setInt(11, logSend.getIspID());

				// pst.setTimestamp(12, new Timestamp(logSend.getSendTime()
				// .getTime()));

				pst.setString(12, logSend.getSendID());
				pst.setString(13, logSend.getProvice());
				pst.setString(14, logSend.getCity());
				pst.setString(15, logSend.getVcode());
				pst.setString(16, logSend.getProductID());
				pst.setString(17, logSend.getUserPackage());
				pst.setString(18, logSend.getMaskpackage());
				pst.setInt(19, logSend.getPrice());
				pst.setInt(20, 0);
				pst.addBatch();
			}
			pst.executeBatch();
			con.commit();
		} catch (SQLException e) {
			LoggerUtil.error("批量增加logSend异常:" + e.getMessage() + "\r\n");
			try {
				con.rollback();
			} catch (SQLException e1) {
			}
		} finally {
			DBUtils.close(pst, con);
		}
	}

	public void queryForUpdate(List<String> mobileList, String rcode,
			String rcode_des, String seq) {
		String MM = DateUtil.DateToMM(new Date());

		StringBuffer mobileStr = new StringBuffer();
		int size = mobileList.size();
		for (int i = 0; i < size; i++) {
			String mobile = mobileList.get(i);
			if (i != size - 1) {
				mobileStr.append("'").append(mobile).append("'").append(",");
			} else {
				mobileStr.append("'").append(mobile).append("'");
			}
		}

		String query_sql = "select log_id, rcode , rcode_des, REPORT_TIME from logsend_"
				+ MM
				+ " where seq = '"
				+ seq
				+ "' and desmobile in ("
				+ mobileStr.toString() + ")";

		Connection conn = DBUtils.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn.setAutoCommit(false);
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE,
					ResultSet.CLOSE_CURSORS_AT_COMMIT);
			rs = stmt.executeQuery(query_sql);
			// 循环逐条更新查询结果集数据
			while (rs.next()) {
				// 更新数据的name列
				rs.updateString("rcode", rcode);
				rs.updateString("rcode_des", rcode_des);
				rs.updateTimestamp("REPORT_TIME", new Timestamp(new Date()
						.getTime()));
				// 保存更新行
				rs.updateRow();
			}
			conn.commit();
		} catch (Exception e) {
			LoggerUtil.info("查询更新logSend异常:" + e.getMessage() + "\r\n");
			e.printStackTrace();
		} finally {
			DBUtils.close(rs, stmt, conn);
		}
	}

	public void updateEcode(String logID, String ecode, String ecodeDes) {
		Connection con = null;
		PreparedStatement pst = null;

		String MM = DateUtil.DateToMM(new Date());
		String sql = "update logsend_" + MM
				+ "set ecode = ?, ecode_des = ? where LOG_ID = ?";
		con = DBUtils.getConnection();
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, ecode);
			pst.setString(2, ecodeDes);
			pst.setString(3, logID);

			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(pst, con);
		}
	}

	public void updateRcode(String mobile, String rcode, String rcodeDes) {
		Connection con = null;
		PreparedStatement pst = null;

		String MM = DateUtil.DateToMM(new Date());
		String sql = "update logsend_"
				+ MM
				+ "set rcode = ?, rcode_des = ? , report_time = sysdate() where desmobile = ?";
		con = DBUtils.getConnection();
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, rcode);
			pst.setString(2, rcodeDes);
			pst.setString(3, mobile);

			pst.executeUpdate();
		} catch (SQLException e) {
			LoggerUtil.info("updateRcode更新异常:" + e.getMessage() + "\r\n");
			e.printStackTrace();
		} finally {
			DBUtils.close(pst, con);
		}
	}

	public List<LogSend> find(int userID, String sendidORrequestid,
			String mobiles, String type) {
		List<LogSend> logsendList = new ArrayList<LogSend>();

		String MM = DateUtil.DateToMM(new Date());

		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer
				.append("select log_id, desmobile, sendid, rcode, maskpackage, recv_time ,request_id from logsend_");
		sqlBuffer.append(MM);
		sqlBuffer.append(" where user_id = '" + userID + "' and ");

		if (!"3".equals(type)) {
			sqlBuffer.append(" sendid = '");
			sqlBuffer.append(sendidORrequestid);
		} else {
			sqlBuffer.append(" request_id = '");
			sqlBuffer.append(sendidORrequestid);
		}

		sqlBuffer.append("'");

		if (!"".equals(mobiles) && mobiles != null) {
			sqlBuffer.append(" and desmobile in (");
			sqlBuffer.append(mobiles).append(")");
		}

		String query_sql = sqlBuffer.toString();
		// LoggerUtil.info(query_sql);
		Connection conn = DBUtils.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query_sql);
			// 循环逐条更新查询结果集数据
			while (rs.next()) {
				LogSend logSend = new LogSend();
				logSend.setDesMobile(rs.getString(2));
				logSend.setSendID(rs.getString(3));
				String rcode = rs.getString(4);
				rcode = "".equals(rcode) || "null".equals(rcode) ? "-1" : rcode;
				logSend.setRcode(rcode);
				logSend.setMaskpackage(rs.getString(5));
				logSend.setRecvTime(rs.getDate(6));
				logSend.setRequestid(rs.getString(7));
				logsendList.add(logSend);
			}
			conn.commit();
		} catch (Exception e) {
			LoggerUtil.error("查询订单状态异常:" + e.getMessage());
			e.printStackTrace();
		} finally {
			DBUtils.close(rs, stmt, conn);
		}
		return logsendList;
	}
}
