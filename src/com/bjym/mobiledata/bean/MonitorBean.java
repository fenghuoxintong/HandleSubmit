package com.bjym.mobiledata.bean;

import java.io.Serializable;

public class MonitorBean implements Serializable{
	private static final long serialVersionUID = 8146936570947548961L;
	private String NodeCode;
	private Integer MtRecvNum;
	private Integer MtSendSuccNum;
	private Integer MtSendFailedNum;
	private Integer MtWaitSendNum;
	
	private Integer ReportNum;
	private Integer ReportSuccNum;
	private Integer ReportFailedNum;
	private Integer ReportWaitPushNum;
	
	public MonitorBean() {
		super();
	}
	public MonitorBean(String nodeCode, Integer mtRecvNum,
			Integer mtSendSuccNum, Integer mtSendFailedNum,
			Integer mtWaitSendNum, Integer reportNum, Integer reportSuccNum,
			Integer reportFailedNum, Integer reportWaitPushNum) {
		super();
		NodeCode = nodeCode;
		MtRecvNum = mtRecvNum;
		MtSendSuccNum = mtSendSuccNum;
		MtSendFailedNum = mtSendFailedNum;
		MtWaitSendNum = mtWaitSendNum;
		ReportNum = reportNum;
		ReportSuccNum = reportSuccNum;
		ReportFailedNum = reportFailedNum;
		ReportWaitPushNum = reportWaitPushNum;
	}
	public String getNodeCode() {
		return NodeCode;
	}
	public void setNodeCode(String nodeCode) {
		NodeCode = nodeCode;
	}
	public Integer getMtRecvNum() {
		return MtRecvNum;
	}
	public void setMtRecvNum(Integer mtRecvNum) {
		MtRecvNum = mtRecvNum;
	}
	public Integer getMtSendSuccNum() {
		return MtSendSuccNum;
	}
	public void setMtSendSuccNum(Integer mtSendSuccNum) {
		MtSendSuccNum = mtSendSuccNum;
	}
	public Integer getMtSendFailedNum() {
		return MtSendFailedNum;
	}
	public void setMtSendFailedNum(Integer mtSendFailedNum) {
		MtSendFailedNum = mtSendFailedNum;
	}
	public Integer getMtWaitSendNum() {
		return MtWaitSendNum;
	}
	public void setMtWaitSendNum(Integer mtWaitSendNum) {
		MtWaitSendNum = mtWaitSendNum;
	}
	public Integer getReportNum() {
		return ReportNum;
	}
	public void setReportNum(Integer reportNum) {
		ReportNum = reportNum;
	}
	public Integer getReportSuccNum() {
		return ReportSuccNum;
	}
	public void setReportSuccNum(Integer reportSuccNum) {
		ReportSuccNum = reportSuccNum;
	}
	public Integer getReportFailedNum() {
		return ReportFailedNum;
	}
	public void setReportFailedNum(Integer reportFailedNum) {
		ReportFailedNum = reportFailedNum;
	}
	public Integer getReportWaitPushNum() {
		return ReportWaitPushNum;
	}
	public void setReportWaitPushNum(Integer reportWaitPushNum) {
		ReportWaitPushNum = reportWaitPushNum;
	}
	
}
