package com.roadgovern.hbm;

// Generated Dec 15, 2013 12:26:56 AM by Hibernate Tools 3.4.0.CR1

/**
 * IssuePocId generated by hbm2java
 */
public class IssuePocId implements java.io.Serializable {

	private int issueTypeId;
	private String userId;
	private int cityId;

	public IssuePocId() {
	}

	public IssuePocId(int issueTypeId, String userId, int cityId) {
		this.issueTypeId = issueTypeId;
		this.userId = userId;
		this.cityId = cityId;
	}

	public int getIssueTypeId() {
		return this.issueTypeId;
	}

	public void setIssueTypeId(int issueTypeId) {
		this.issueTypeId = issueTypeId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getCityId() {
		return this.cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof IssuePocId))
			return false;
		IssuePocId castOther = (IssuePocId) other;

		return (this.getIssueTypeId() == castOther.getIssueTypeId())
				&& ((this.getUserId() == castOther.getUserId()) || (this
						.getUserId() != null && castOther.getUserId() != null && this
						.getUserId().equals(castOther.getUserId())))
				&& (this.getCityId() == castOther.getCityId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIssueTypeId();
		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37 * result + this.getCityId();
		return result;
	}

}
