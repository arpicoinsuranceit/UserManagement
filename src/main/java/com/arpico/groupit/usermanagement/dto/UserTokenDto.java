package com.arpico.groupit.usermanagement.dto;

public class UserTokenDto {
	private String userId;
	private String userCode;
	private String userFullName;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserFullName() {
		return userFullName;
	}
	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}
	
	@Override
	public String toString() {
		return "UserTokenDto [userId=" + userId + ", userCode=" + userCode + ", userFullName=" + userFullName + "]";
	}
}
