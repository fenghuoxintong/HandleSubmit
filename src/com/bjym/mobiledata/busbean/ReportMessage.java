package com.bjym.mobiledata.busbean;

public class ReportMessage {
	private String mobiles;
	private String state;
	private String sendID;
	private String recvTime;
	private String userPackage;
	private String requestid;

	public ReportMessage() {
	}

	public ReportMessage(String mobiles, String state, String sendID,
			String recvTime, String userPackage, String requestid) {
		this.mobiles = mobiles;
		this.state = state;
		this.sendID = sendID;
		this.recvTime = recvTime;
		this.userPackage = userPackage;
		this.requestid = requestid;
	}

	public String getMobiles() {
		return mobiles;
	}

	public void setMobiles(String mobiles) {
		this.mobiles = mobiles;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSendID() {
		return sendID;
	}

	public void setSendID(String sendID) {
		this.sendID = sendID;
	}

	public String getRecvTime() {
		return recvTime;
	}

	public void setRecvTime(String recvTime) {
		this.recvTime = recvTime;
	}

	public String getUserPackage() {
		return userPackage;
	}

	public void setUserPackage(String userPackage) {
		this.userPackage = userPackage;
	}

	public String getRequestid() {
		return requestid;
	}

	public void setRequestid(String requestid) {
		this.requestid = requestid;
	}

}
