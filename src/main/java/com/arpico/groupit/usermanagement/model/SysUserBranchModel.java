package com.arpico.groupit.usermanagement.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SYS_USER_BRANCH")
public class SysUserBranchModel {

	@Column(name = "SYS_USER_BRANCH_ID")
	private String id;
	private BranchModel branch;
	private SysUserModel sysUser;
	private Integer isEnabled;

	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "BRANCH_ID")
	public BranchModel getBranch() {
		return branch;
	}

	public void setBranch(BranchModel branch) {
		this.branch = branch;
	}
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "SYS_USER_ID")
	public SysUserModel getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUserModel sysUser) {
		this.sysUser = sysUser;
	}

	public Integer getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Integer isEnabled) {
		this.isEnabled = isEnabled;
	}

	
}
