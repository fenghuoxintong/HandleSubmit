package com.bjym.mobiledata.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.bjym.mobiledata.bean.UserReg;
import com.bjym.mobiledata.utils.LoggerUtil;

public class RegUserDaoimp {
	public UserReg login(String username, String password) {
		UserReg userReg = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		String sql = "select * from traffic_user_reg where username = ? and password = ?";
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			rs = pst.executeQuery();
			while (rs.next()) {
				userReg = new UserReg();
				userReg.setUSER_ID(rs.getInt(1));
				userReg.setUsername(rs.getString(2));
				userReg.setPassword(rs.getString(3));
				userReg.setStatus(rs.getInt(4));
				userReg.setTotalBalance(rs.getInt(5));
				userReg.setUsedBalance(rs.getInt(6));
				userReg.setDayExpense(rs.getInt(7));
				userReg.setCreateTime(rs.getDate(8));
				userReg.setLastTime(rs.getDate(9));
				userReg.setLinkman(rs.getString(10));
				userReg.setPhone(rs.getString(11));
				userReg.setCompany(rs.getString(12));
				userReg.setMemo(rs.getString(13));
				userReg.setOperatorid(rs.getInt(14));
				userReg.setPriority(rs.getInt(15));
				userReg.setCmppgw(rs.getInt(16));
				userReg.setSgipgw(rs.getInt(17));
				userReg.setSmgpgw(rs.getInt(18));
				userReg.setUsertype(rs.getInt(19));
				userReg.setServerip(rs.getString(20));
			}
		} catch (Exception e) {
			LoggerUtil.info(e.getMessage());
			e.printStackTrace();
		} finally {
			DBUtils.close(rs, pst, con);
		}
		return userReg;
	}

	public void update(int userid, int dayExpense) {
		Connection con = null;
		PreparedStatement pst = null;

		String sql = "update traffic_user_reg set TOTAL_MAX_BALANCE = TOTAL_MAX_BALANCE - ?,  TOTAL_USED_BALANCE = TOTAL_USED_BALANCE + ?, DAY_EXPENSE = DAY_EXPENSE + ? , last_time = sysdate() where USER_ID = ?";
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(sql);
			pst.setInt(1, dayExpense);
			pst.setInt(2, dayExpense);
			pst.setInt(3, dayExpense);
			pst.setInt(4, userid);
			pst.executeUpdate();
		} catch (Exception e) {
			LoggerUtil.info(e.getMessage());
			e.printStackTrace();
		} finally {
			DBUtils.close(pst, con);
		}
	}
}
