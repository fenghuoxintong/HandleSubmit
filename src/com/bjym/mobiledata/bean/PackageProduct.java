package com.bjym.mobiledata.bean;

import java.io.Serializable;
import java.util.Date;

public class PackageProduct implements Serializable {
	private int pid;
	private String pcode;
	private String pname;
	//π©”¶…Ã
	private String vcode;
	private String ppackage;
	private int price;
	private String size;
	private String discount;
	private int dprice;
	private int existnum;
	private int isp_id;
	// toB toC
	private int supportype;
	private String province;
	private int weight;
	private Date createDate;
	private Date startDate;
	private Date endDate;
	private String memo;
	private String operuser;
	private int status;

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public int getDprice() {
		return dprice;
	}

	public void setDprice(int dprice) {
		this.dprice = dprice;
	}

	public int getExistnum() {
		return existnum;
	}

	public void setExistnum(int existnum) {
		this.existnum = existnum;
	}

	public int getIsp_id() {
		return isp_id;
	}

	public void setIsp_id(int ispId) {
		isp_id = ispId;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	public int getSupportype() {
		return supportype;
	}

	public void setSupportype(int supportype) {
		this.supportype = supportype;
	}

	public String getPpackage() {
		return ppackage;
	}

	public void setPpackage(String ppackage) {
		this.ppackage = ppackage;
	}

	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}
	
}
