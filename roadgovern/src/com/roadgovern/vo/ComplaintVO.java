/**
 * 
 */
package com.roadgovern.vo;

import java.util.Date;
import java.util.List;


/**
 * @author Prakash
 *
 */
public class ComplaintVO {

	private int stateId;
	private int cityId;
	private int districtId;
	private int roadId;
	private IssueTypeVO issueTypeVO;
	private int complaintId;
	
	private List<StateVO> states;
	private List<DistrictVO> districts;
	private List<CityVO> cities;
	private List<RoadVO> roads;
	private List<IssueTypeVO> issueTypes;
	
	private UserVO complaintUser;
	private UserVO complaintCreatedBy;
	private UserVO loggedBy;
	
	private String complaintDesc;
	private String cityName;
	private String districtName;
	private String roadName;
	private String priority;
	private Date createTS;
	private Date lastChangeTS;
	private int issueTypeName;
	
	private String assignedTo;
	private String severity;
	private List<ItemLogVO> itemLogs;
	private List<AttachmentVO> itemAttachments;
	private String status;
	private int statusId;
	private AttachmentVO attachmentVO;
	private boolean allLogsRequired	=	false;
	
	
	
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getRoadName() {
		return roadName;
	}
	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public UserVO getComplaintCreatedBy() {
		return complaintCreatedBy;
	}
	public void setComplaintCreatedBy(UserVO complaintCreatedBy) {
		this.complaintCreatedBy = complaintCreatedBy;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	public int getComplaintId() {
		return complaintId;
	}
	public void setComplaintId(int complaintId) {
		this.complaintId = complaintId;
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
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public AttachmentVO getAttachmentVO() {
		return attachmentVO;
	}
	public void setAttachmentVO(AttachmentVO attachmentVO) {
		this.attachmentVO = attachmentVO;
	}
	public boolean isAllLogsRequired() {
		return allLogsRequired;
	}
	public void setAllLogsRequired(boolean allLogsRequired) {
		this.allLogsRequired = allLogsRequired;
	}
	public int getRoadId() {
		return roadId;
	}
	public void setRoadId(int roadId) {
		this.roadId = roadId;
	}
	public List<RoadVO> getRoads() {
		return roads;
	}
	public void setRoads(List<RoadVO> roads) {
		this.roads = roads;
	}
	public int getStateId() {
		return stateId;
	}
	public void setStateId(int stateId) {
		this.stateId = stateId;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public int getDistrictId() {
		return districtId;
	}
	public void setDistrictId(int districtId) {
		this.districtId = districtId;
	}
	public List<StateVO> getStates() {
		return states;
	}
	public void setStates(List<StateVO> states) {
		this.states = states;
	}
	public List<DistrictVO> getDistricts() {
		return districts;
	}
	public void setDistricts(List<DistrictVO> districts) {
		this.districts = districts;
	}
	public List<CityVO> getCities() {
		return cities;
	}
	public void setCities(List<CityVO> cities) {
		this.cities = cities;
	}
	public UserVO getComplaintUser() {
		return complaintUser;
	}
	public void setComplaintUser(UserVO complaintUser) {
		this.complaintUser = complaintUser;
	}
	public List<IssueTypeVO> getIssueTypes() {
		return issueTypes;
	}
	public void setIssueTypes(List<IssueTypeVO> issueTypes) {
		this.issueTypes = issueTypes;
	}
	public String getComplaintDesc() {
		return complaintDesc;
	}
	public void setComplaintDesc(String complaintDesc) {
		this.complaintDesc = complaintDesc;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public Date getCreateTS() {
		return createTS;
	}
	public void setCreateTS(Date createTS) {
		this.createTS = createTS;
	}
	public Date getLastChangeTS() {
		return lastChangeTS;
	}
	public void setLastChangeTS(Date lastChangeTS) {
		this.lastChangeTS = lastChangeTS;
	}
	public IssueTypeVO getIssueTypeVO() {
		return issueTypeVO;
	}
	public void setIssueTypeVO(IssueTypeVO issueTypeVO) {
		this.issueTypeVO = issueTypeVO;
	}
	public int getIssueTypeName() {
		return issueTypeName;
	}
	public void setIssueTypeName(int issueTypeName) {
		this.issueTypeName = issueTypeName;
	}
	public UserVO getLoggedBy() {
		return loggedBy;
	}
	public void setLoggedBy(UserVO loggedBy) {
		this.loggedBy = loggedBy;
	}
	
	
}
