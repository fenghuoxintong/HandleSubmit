package com.bjym.mobiledata.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.bjym.mobiledata.bean.PackageProduct;
import com.bjym.mobiledata.utils.LoggerUtil;

public class PackageProductDaoImp {
	public PackageProduct findProduct(String pcode) {
		PackageProduct packageProduct = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		String sql = "select * from packageproduct where pcode = ?";
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, pcode);
			rs = pst.executeQuery();
			while (rs.next()) {
				packageProduct = new PackageProduct();
				packageProduct.setPcode(rs.getString(2));
				packageProduct.setPname(rs.getString(3));
				packageProduct.setVcode(rs.getString(4));
				packageProduct.setPpackage(rs.getString(5));
				packageProduct.setPrice(rs.getInt(6));
				packageProduct.setSize(rs.getString(7));
				packageProduct.setDiscount(rs.getString(8));
				packageProduct.setDprice(rs.getInt(9));
				packageProduct.setExistnum(rs.getInt(10));
				packageProduct.setIsp_id(rs.getInt(11));
				packageProduct.setSupportype(rs.getInt(12));
				packageProduct.setProvince(rs.getString(13));
				packageProduct.setWeight(rs.getInt(14));
				packageProduct.setCreateDate(rs.getDate(15));
				packageProduct.setStartDate(rs.getDate(16));
				packageProduct.setEndDate(rs.getDate(17));
				packageProduct.setMemo(rs.getString(18));
				packageProduct.setOperuser(rs.getString(19));
				packageProduct.setStatus(rs.getInt(20));
			}
		} catch (Exception e) {
			LoggerUtil.info(e.getMessage());
			e.printStackTrace();
		} finally {
			DBUtils.close(rs, pst, con);
		}
		return packageProduct;
	}

	public void update(String pcode, int cusumeNum) {
		Connection con = null;
		PreparedStatement pst = null;

		String sql = "update packageproduct set existnum = existnum - ? where pcode = ?";
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(sql);
			pst.setInt(1, cusumeNum);
			pst.setString(2, pcode);
			pst.executeUpdate();
		} catch (Exception e) {
			LoggerUtil.info(e.getMessage());
			e.printStackTrace();
		} finally {
			DBUtils.close(pst, con);
		}
	}
}