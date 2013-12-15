package com.roadgovern.vo;

import java.util.List;

public class UserVO {

	private String userId;
	private String firstName;
	private String lastName;
	private String contactNo;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String district;
	private String postalCode;
	private String email;
	private String password;
	private String userRole;
	private String reportee;
	private int issueTypeId;
	private List<IssueTypeVO> issueTypes;
	private int cityId;
	private List<CityVO> cities;
	
	
	public int getIssueTypeId() {
		return issueTypeId;
	}
	public void setIssueTypeId(int issueTypeId) {
		this.issueTypeId = issueTypeId;
	}
	public List<IssueTypeVO> getIssueTypes() {
		return issueTypes;
	}
	public void setIssueTypes(List<IssueTypeVO> issueTypes) {
		this.issueTypes = issueTypes;
	}
	public List<CityVO> getCities() {
		return cities;
	}
	public void setCities(List<CityVO> cities) {
		this.cities = cities;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getReportee() {
		return reportee;
	}
	public void setReportee(String reportee) {
		this.reportee = reportee;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	
}
