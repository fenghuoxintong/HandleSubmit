package com.bjym.mobiledata.busbean;

import java.io.Serializable;

import com.bjym.mobiledata.bean.LogSend;
import com.bjym.mobiledata.bean.UserReg;

public class SubmitTask implements Serializable {
	private String startTime;
	private String lastProcessTime;
	private LogSend logSend;
	private UserReg userReg;

	public SubmitTask() {
	}

	public SubmitTask(String startTime, String lastProcessTime,
			LogSend logSend, UserReg userReg) {
		this.startTime = startTime;
		this.lastProcessTime = lastProcessTime;
		this.logSend = logSend;
		this.userReg = userReg;
	}

	public LogSend getLogSend() {
		return logSend;
	}

	public void setLogSend(LogSend logSend) {
		this.logSend = logSend;
	}

	public UserReg getUserReg() {
		return userReg;
	}

	public void setUserReg(UserReg userReg) {
		this.userReg = userReg;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getLastProcessTime() {
		return lastProcessTime;
	}

	public void setLastProcessTime(String lastProcessTime) {
		this.lastProcessTime = lastProcessTime;
	}

}
