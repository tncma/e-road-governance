package com.roadgovern.vo;

import java.util.List;
import java.util.TreeSet;

public class FilterVO {

	private String assignedTo;
	private List<String> assignedToList; 
	private String type;
	private List<String> typeList; 
	private int statusId;
	private List<StatusVO> statusList;
	private int groupId;
	private String groupName;
	private String severity;
	private List<String> severityList; 
	
	
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
	/**
	 * @return the severityList
	 */
	public List<String> getSeverityList() {
		return severityList;
	}
	/**
	 * @param severityList the severityList to set
	 */
	public void setSeverityList(List<String> severityList) {
		this.severityList = severityList;
	}
	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}
	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	public List<String> getAssignedToList() {
		return assignedToList;
	}
	public void setAssignedToList(List<String> assignedToList) {
		this.assignedToList = assignedToList;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<String> getTypeList() {
		return typeList;
	}
	public void setTypeList(List<String> typeList) {
		this.typeList = typeList;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public List<StatusVO> getStatusList() {
		return statusList;
	}
	public void setStatusList(List<StatusVO> statusList) {
		this.statusList = statusList;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	
}
