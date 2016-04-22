package com.bjym.mobiledata.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.bjym.mobiledata.bean.PhoneAreaISP;
import com.bjym.mobiledata.utils.LoggerUtil;

public class PhoneDaoimp {
	public PhoneAreaISP find(String phone) {
		phone = phone.substring(0, 7);
		PhoneAreaISP phoneAreaISP = null;
		String query_sql = "select * from logic_full_cityname where seg = ?";

		Connection conn = DBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(query_sql);
			pst.setString(1, phone);
			rs = pst.executeQuery();
			if (rs.next()) {
				phoneAreaISP = new PhoneAreaISP();
				phoneAreaISP.setPhone(rs.getString(1));
				phoneAreaISP.setProvince(rs.getString(2));
				phoneAreaISP.setCity(rs.getString(3));
				int ispCode = rs.getInt(4);
				phoneAreaISP.setIspCode(ispCode);
				String ispDes = "移动";
				if (ispCode == 1) {
					ispDes = "联通";
				} else if (ispCode == 4) {
					ispDes = "电信";
				}
				phoneAreaISP.setIspDes(ispDes);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(rs, pst, conn);
		}
		return phoneAreaISP;
	}

	public static void main(String[] args) {
		System.out.println("1527493560".substring(0, 7));
	}
}
