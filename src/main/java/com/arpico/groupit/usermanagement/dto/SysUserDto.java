package com.arpico.groupit.usermanagement.dto;

import java.util.Date;

import javax.persistence.Column;

public class SysUserDto {

	private String userSalutation;
	private String userFirstName;
	private String userLastName;
	private String userNic;
	private String userPassport;
	private String userAddress1;
	private String userAddress2;
	private String userAddress3;
	private String userMobileNumber;
	private String userTelephoneNumber;
	private String userEmail;
	private String userName;
	private String userEmployeeNo;
	
	
	
	public String getUserEmployeeNo() {
		return userEmployeeNo;
	}
	public void setUserEmployeeNo(String userEmployeeNo) {
		this.userEmployeeNo = userEmployeeNo;
	}
	public String getUserSalutation() {
		return userSalutation;
	}
	public void setUserSalutation(String userSalutation) {
		this.userSalutation = userSalutation;
	}
	public String getUserFirstName() {
		return userFirstName;
	}
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	public String getUserLastName() {
		return userLastName;
	}
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	public String getUserNic() {
		return userNic;
	}
	public void setUserNic(String userNic) {
		this.userNic = userNic;
	}
	public String getUserPassport() {
		return userPassport;
	}
	public void setUserPassport(String userPassport) {
		this.userPassport = userPassport;
	}
	public String getUserAddress1() {
		return userAddress1;
	}
	public void setUserAddress1(String userAddress1) {
		this.userAddress1 = userAddress1;
	}
	public String getUserAddress2() {
		return userAddress2;
	}
	public void setUserAddress2(String userAddress2) {
		this.userAddress2 = userAddress2;
	}
	public String getUserAddress3() {
		return userAddress3;
	}
	public void setUserAddress3(String userAddress3) {
		this.userAddress3 = userAddress3;
	}
	public String getUserMobileNumber() {
		return userMobileNumber;
	}
	public void setUserMobileNumber(String userMobileNumber) {
		this.userMobileNumber = userMobileNumber;
	}
	public String getUserTelephoneNumber() {
		return userTelephoneNumber;
	}
	public void setUserTelephoneNumber(String userTelephoneNumber) {
		this.userTelephoneNumber = userTelephoneNumber;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	

}
