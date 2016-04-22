package com.bjym.mobiledata.bean;

import java.io.Serializable;
import java.util.Date;

public class PackageRelation implements Serializable {
	private int rid;
	private String maskcode;
	private String rname;

	// π©”¶…Ã¬Î
	private String vcode;
	private String pname;
	private String pcode;
	private String packagesize;
	private String realcode;
	
	private int price;
	private int dprice;
	private String clientid;
	private String clientname;
	private int assignprice;
	private String province;
	private String delprovince;
	private int weight;
	// toB=1 toC=2
	private int supporttyp;
	private int ispid;
	private Date createDate;
	private String operuser;
	private int status;

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public String getMaskcode() {
		return maskcode;
	}

	public void setMaskcode(String maskcode) {
		this.maskcode = maskcode;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
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

	public int getDprice() {
		return dprice;
	}

	public void setDprice(int dprice) {
		this.dprice = dprice;
	}

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public String getClientname() {
		return clientname;
	}

	public void setClientname(String clientname) {
		this.clientname = clientname;
	}

	public int getAssignprice() {
		return assignprice;
	}

	public void setAssignprice(int assignprice) {
		this.assignprice = assignprice;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getDelprovince() {
		return delprovince;
	}

	public void setDelprovince(String delprovince) {
		this.delprovince = delprovince;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getSupporttyp() {
		return supporttyp;
	}

	public void setSupporttyp(int supporttyp) {
		this.supporttyp = supporttyp;
	}

	public int getIspid() {
		return ispid;
	}

	public void setIspid(int ispid) {
		this.ispid = ispid;
	}

	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

	public String getPackagesize() {
		return packagesize;
	}

	public void setPackagesize(String packagesize) {
		this.packagesize = packagesize;
	}

	public String getRealcode() {
		return realcode;
	}

	public void setRealcode(String realcode) {
		this.realcode = realcode;
	}
	
}
