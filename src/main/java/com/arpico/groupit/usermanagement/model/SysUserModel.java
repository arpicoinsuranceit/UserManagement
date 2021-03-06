package com.arpico.groupit.usermanagement.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SYSUSER")
public class SysUserModel implements Serializable {

	@Column(name = "SYSUSER_ID")
	private String userId;

	@Column(name = "USER_SALUTION")
	private String userSalutation;

	@Column(name = "USER_FIRST_NAME")
	private String userFirstName;

	@Column(name = "USER_LAST_NAME")
	private String userLastName;

	@Column(name = "USER_NIC")
	private String userNic;

	@Column(name = "USER_PASSPORT")
	private String userPassport;

	@Column(name = "USER_ADDRESS_1")
	private String userAddress1;

	@Column(name = "USER_ADDRESS_2")
	private String userAddress2;

	@Column(name = "USER_ADDRESS_3")
	private String userAddress3;

	@Column(name = "USER_MOBILE_NUMBER")
	private String userMobileNumber;

	@Column(name = "USER_TELEPHONE_NUMBER")
	private String userTelephoneNumber;

	@Column(name = "USER_EMAIL")
	private String userEmail;

	@Column(name = "USER_CODE")
	private String userCode;

	@Column(name = "USER_EMPLOYEE_NO")
	private String userEmployeeNo;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "IS_ENABELED")
	private Integer isEnabeled;

	@Column(name = "CREATED_TIME")
	private Date createdTime;

	@Column(name = "UPDATED_TIME")
	private Date updatedTime;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_BY")
	private String updatedBy;

	private List<SubSbuSysUserModel> sbuSysUsers;
	private List<SysUserRoleModel> sysUserRoleModels;
	private List<SysUserBranchModel> sysUserBranchModels;

	private LoginModel loginModel;

	@Id
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserEmployeeNo() {
		return userEmployeeNo;
	}

	public void setUserEmployeeNo(String userEmployeeNo) {
		this.userEmployeeNo = userEmployeeNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getIsEnabeled() {
		return isEnabeled;
	}

	public void setIsEnabeled(Integer isEnabeled) {
		this.isEnabeled = isEnabeled;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@OneToMany(mappedBy = "sysUser", targetEntity = SubSbuSysUserModel.class)
	public List<SubSbuSysUserModel> getSbuSysUsers() {
		return sbuSysUsers;
	}

	public void setSbuSysUsers(List<SubSbuSysUserModel> sbuSysUsers) {
		this.sbuSysUsers = sbuSysUsers;
	}

	@OneToOne(mappedBy = "sysUserModel", targetEntity = LoginModel.class)
	public LoginModel getLoginModel() {
		return loginModel;
	}

	public void setLoginModel(LoginModel loginModel) {
		this.loginModel = loginModel;
	}

	@OneToOne(mappedBy = "sysUserModel", targetEntity = SysUserRoleModel.class)
	public List<SysUserRoleModel> getSysUserRoleModels() {
		return sysUserRoleModels;
	}

	public void setSysUserRoleModels(List<SysUserRoleModel> sysUserRoleModels) {
		this.sysUserRoleModels = sysUserRoleModels;
	}

	@OneToMany(mappedBy = "sysUser", targetEntity = SysUserBranchModel.class)
	public List<SysUserBranchModel> getSysUserBranchModels() {
		return sysUserBranchModels;
	}

	public void setSysUserBranchModels(List<SysUserBranchModel> sysUserBranchModels) {
		this.sysUserBranchModels = sysUserBranchModels;
	}

}
