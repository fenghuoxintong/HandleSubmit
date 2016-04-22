package com.bjym.mobiledata.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.bjym.mobiledata.bean.PackageRelation;
import com.bjym.mobiledata.utils.LoggerUtil;

public class PackageRelationDaoImp {

	public PackageRelation findPcodeBySmart(String userId, int userType,
			int isp, String packageSize, int price, String province) {
		PackageRelation packageRelation = null;

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = " select vcode ,pcode, packagesize, realcode, assignprice from packagerelation"
				+ " where status = 1 and clientid = ? and supporttype >= ? and isp_id = ? and packagesize >= ? and assignprice = ?"
				+ " and ((province = '0000' and FIND_IN_SET(?, delprovince)=0) or (FIND_IN_SET(?, province)!=0))"
				+ " order by weight DESC ,(assignprice - dprice) desc  limit 1";

		LoggerUtil.info(userId + "," + userType + "," + isp + "," + packageSize
				+ "," + price + "," + province);
		LoggerUtil.info(sql);
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, userId);
			pst.setInt(2, userType);
			pst.setInt(3, isp);
			pst.setString(4, packageSize);
			pst.setInt(5, price);
			pst.setString(6, province);
			pst.setString(7, province);
			rs = pst.executeQuery();
			if (rs.next()) {
				packageRelation = new PackageRelation();
				packageRelation.setVcode(rs.getString(1));
				packageRelation.setPcode(rs.getString(2));
				packageRelation.setPackagesize(rs.getString(3));
				packageRelation.setRealcode(rs.getString(4));
				packageRelation.setAssignprice(rs.getInt(5));
			}
		} catch (Exception e) {
			LoggerUtil.error(e.getMessage());
			e.printStackTrace();
		} finally {
			DBUtils.close(rs, pst, con);
		}
		return packageRelation;
	}
}
