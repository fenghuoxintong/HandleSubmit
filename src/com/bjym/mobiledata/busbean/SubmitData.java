package com.bjym.mobiledata.busbean;

import java.util.List;

public class SubmitData {
	private String clientID;
	private String share_secret;
	private List<UserData> userdataList;

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public String getShare_secret() {
		return share_secret;
	}

	public void setShare_secret(String shareSecret) {
		share_secret = shareSecret;
	}

	public List<UserData> getUserdataList() {
		return userdataList;
	}

	public void setUserdataList(List<UserData> userdataList) {
		this.userdataList = userdataList;
	}

}
