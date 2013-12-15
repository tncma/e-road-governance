package com.roadgovern.vo;

import java.util.Date;
import java.util.List;

public class ItemVO {

	private int itemId;
	private String itemDesc;
	private String itemCreatedBy;
	private String itemUpdatedBy;
	private String createTs;
	private Date lastChangeTS;
	private String module;
	private String subModule;
	private int groupId;
	private String assignedTo;
	private List<ItemLogVO> itemLogs;
	private List<AttachmentVO> itemAttachments;
	private String status;
	private int statusId;
	private String priority;
	private String severity;
	private AttachmentVO attachmentVO;
	private boolean allLogsRequired	=	false;
	
	
	/**
	 * @return the severity
	 */
	public String getSeverity() {
		return severity;
	}
	/**
	 * @param severity the severity to set
	 */
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemDesc() {
		return itemDesc;
	}
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
	public String getItemCreatedBy() {
		return itemCreatedBy;
	}
	public void setItemCreatedBy(String itemCreatedBy) {
		this.itemCreatedBy = itemCreatedBy;
	}
	public Date getLastChangeTS() {
		return lastChangeTS;
	}
	public void setLastChangeTS(Date lastChangeTS) {
		this.lastChangeTS = lastChangeTS;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	public List<ItemLogVO> getItemLogs() {
		return itemLogs;
	}
	public void setItemLogs(List<ItemLogVO> itemLogs) {
		this.itemLogs = itemLogs;
	}
	public List<AttachmentVO> getItemAttachments() {
		return itemAttachments;
	}
	public void setItemAttachments(List<AttachmentVO> itemAttachments) {
		this.itemAttachments = itemAttachments;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSubModule() {
		return subModule;
	}
	public void setSubModule(String subModule) {
		this.subModule = subModule;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public void setAttachmentVO(AttachmentVO attachmentVO) {
		this.attachmentVO = attachmentVO;
	}
	public AttachmentVO getAttachmentVO() {
		return attachmentVO;
	}
	public void setItemUpdatedBy(String itemUpdatedBy) {
		this.itemUpdatedBy = itemUpdatedBy;
	}
	public String getItemUpdatedBy() {
		return itemUpdatedBy;
	}
	public String getCreateTs() {
		return createTs;
	}
	public void setCreateTs(String createTs) {
		this.createTs = createTs;
	}
	public void setAllLogsRequired(boolean allLogsRequired) {
		this.allLogsRequired = allLogsRequired;
	}
	public boolean isAllLogsRequired() {
		return allLogsRequired;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getPriority() {
		return priority;
	}
}
