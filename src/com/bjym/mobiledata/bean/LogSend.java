package com.bjym.mobiledata.bean;

import java.io.Serializable;
import java.util.Date;

public class LogSend implements Serializable {
	private static final long serialVersionUID = 8146936570947548961L;
	
	private String logID;
	private Date createDate;
	private String seq;
	private String sessionID;
	private String transido;
	private String requestid;
	private int userID;
	private String desMobile;
	private Date recvTime;
	private String ecode;
	private String ecodeDes;
	private String rcode;
	private String rcodeDes;
	private int ispID;
	private Date sendTime;
	private String sendID;
	private Date reportTime;
	private String provice;
	private String city;
	private String vcode;
	private String productID;
	private String userPackage;
	private String maskpackage;
	private int price;
	private int status;

	public String getLogID() {
		return logID;
	}

	public void setLogID(String logID) {
		this.logID = logID;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public String getTransido() {
		return transido;
	}

	public void setTransido(String transido) {
		this.transido = transido;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getDesMobile() {
		return desMobile;
	}

	public void setDesMobile(String desMobile) {
		this.desMobile = desMobile;
	}

	public Date getRecvTime() {
		return recvTime;
	}

	public void setRecvTime(Date recvTime) {
		this.recvTime = recvTime;
	}

	public String getEcode() {
		return ecode;
	}

	public void setEcode(String ecode) {
		this.ecode = ecode;
	}

	public String getEcodeDes() {
		return ecodeDes;
	}

	public void setEcodeDes(String ecodeDes) {
		this.ecodeDes = ecodeDes;
	}

	public String getRcode() {
		return rcode;
	}

	public void setRcode(String rcode) {
		this.rcode = rcode;
	}

	public String getRcodeDes() {
		return rcodeDes;
	}

	public void setRcodeDes(String rcodeDes) {
		this.rcodeDes = rcodeDes;
	}

	public int getIspID() {
		return ispID;
	}

	public void setIspID(int ispID) {
		this.ispID = ispID;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getSendID() {
		return sendID;
	}

	public void setSendID(String sendID) {
		this.sendID = sendID;
	}

	public Date getReportTime() {
		return reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	public String getProvice() {
		return provice;
	}

	public void setProvice(String provice) {
		this.provice = provice;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUserPackage() {
		return userPackage;
	}

	public void setUserPackage(String userPackage) {
		this.userPackage = userPackage;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getMaskpackage() {
		return maskpackage;
	}

	public void setMaskpackage(String maskpackage) {
		this.maskpackage = maskpackage;
	}

	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

	public String getRequestid() {
		return requestid;
	}

	public void setRequestid(String requestid) {
		this.requestid = requestid;
	}

}
