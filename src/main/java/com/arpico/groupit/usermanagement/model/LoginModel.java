package com.arpico.groupit.usermanagement.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LOGIN")
public class LoginModel {

	@Column(name = "LOGIN_ID")
	private String LoginId;
	
	@Column(name = "PASSWORD")
	private String Password;
	
	@Column(name = "FAIL_COUNT")
	private Integer failCount;
	
	private Integer locked;
	
	@Column(name = "LAST_MODIFIED")
	private Date LastModified;
	
	private SysUserModel sysUserModel;

	@Id
	public String getLoginId() {
		return LoginId;
	}

	public void setLoginId(String loginId) {
		LoginId = loginId;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public Integer getFailCount() {
		return failCount;
	}

	public void setFailCount(Integer failCount) {
		this.failCount = failCount;
	}

	@Column(name = "IS_LOCKED")
	public Integer getLocked() {
		return locked;
	}

	public void setLocked(Integer locked) {
		this.locked = locked;
	}

	public Date getLastModified() {
		return LastModified;
	}

	public void setLastModified(Date lastModified) {
		LastModified = lastModified;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "SYSUSER_ID")
	public SysUserModel getSysUserModel() {
		return sysUserModel;
	}

	public void setSysUserModel(SysUserModel sysUserModel) {
		this.sysUserModel = sysUserModel;
	}

	@Override
	public String toString() {
		return "LoginModel [LoginId=" + LoginId + ", Password=" + Password + ", failCount=" + failCount + ", locked="
				+ locked + ", LastModified=" + LastModified + ", sysUserModel=" + sysUserModel + "]";
	}
	
	
	
}
