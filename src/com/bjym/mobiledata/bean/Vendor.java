package com.bjym.mobiledata.bean;

import java.util.Date;

public class Vendor {
	private int vid;
	private String vcode;
	private String vname;
	private int isp_id;
	private int vendortype;
	private String vendorserver;
	private Date createDate;
	private String memo;
	private String operuser;
	private int status;

	public int getVid() {
		return vid;
	}

	public void setVid(int vid) {
		this.vid = vid;
	}

	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	public int getIsp_id() {
		return isp_id;
	}

	public void setIsp_id(int ispId) {
		isp_id = ispId;
	}

	public int getVendortype() {
		return vendortype;
	}

	public void setVendortype(int vendortype) {
		this.vendortype = vendortype;
	}

	public String getVendorserver() {
		return vendorserver;
	}

	public void setVendorserver(String vendorserver) {
		this.vendorserver = vendorserver;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getOperuser() {
		return operuser;
	}

	public void setOperuser(String operuser) {
		this.operuser = operuser;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
