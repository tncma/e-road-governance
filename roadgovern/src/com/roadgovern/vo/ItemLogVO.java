package com.roadgovern.vo;

import java.util.Date;
import java.util.List;

public class ItemLogVO {

	private int itemId;
	private int logId;
	private Date createTS;
	private String remarks;
	private int statusId;
	private String status;
	private String loggedBy;
	private String assignedTo;
	private List<UserVO> pocs;
	private List<StatusVO> statusList;
	private boolean displayFlag;
	private AttachmentVO attachmentVO;
	private List<AttachmentVO> logAttachments;
	
	
	public List<UserVO> getPocs() {
		return pocs;
	}
	public void setPocs(List<UserVO> pocs) {
		this.pocs = pocs;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getLogId() {
		return logId;
	}
	public void setLogId(int logId) {
		this.logId = logId;
	}
	public Date getCreateTS() {
		return createTS;
	}
	public void setCreateTS(Date createTS) {
		this.createTS = createTS;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getLoggedBy() {
		return loggedBy;
	}
	public void setLoggedBy(String loggedBy) {
		this.loggedBy = loggedBy;
	}
	public List<StatusVO> getStatusList() {
		return statusList;
	}
	public void setStatusList(List<StatusVO> statusList) {
		this.statusList = statusList;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public boolean isDisplayFlag() {
		return displayFlag;
	}
	public void setDisplayFlag(boolean displayFlag) {
		this.displayFlag = displayFlag;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	public String getAssignedTo() {
		return assignedTo;
	}
	public void setAttachmentVO(AttachmentVO attachmentVO) {
		this.attachmentVO = attachmentVO;
	}
	public AttachmentVO getAttachmentVO() {
		return attachmentVO;
	}
	public List<AttachmentVO> getLogAttachments() {
		return logAttachments;
	}
	public void setLogAttachments(List<AttachmentVO> logAttachments) {
		this.logAttachments = logAttachments;
	}
}
