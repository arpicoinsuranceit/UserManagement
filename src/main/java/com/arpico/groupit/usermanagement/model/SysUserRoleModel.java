package com.arpico.groupit.usermanagement.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SYSUSER_ROLE")
public class SysUserRoleModel {

	private String sysUserRoleId;
	private SysUserModel sysUserModel;
	private RoleModel roleModel;
	private Integer enabled;

	@Id
	@Column(name = "SYS_USER_ROLE_ID")
	public String getSysUserRoleId() {
		return sysUserRoleId;
	}

	public void setSysUserRoleId(String sysUserRoleId) {
		this.sysUserRoleId = sysUserRoleId;
	}

	@JoinColumn(name = "SYS_USER_ID")
	@ManyToOne(cascade = CascadeType.ALL)
	public SysUserModel getSysUserModel() {
		return sysUserModel;
	}

	public void setSysUserModel(SysUserModel sysUserModel) {
		this.sysUserModel = sysUserModel;
	}

	@JoinColumn(name = "ROLE_ID")
	@ManyToOne(cascade = CascadeType.ALL)
	public RoleModel getRoleModel() {
		return roleModel;
	}

	public void setRoleModel(RoleModel roleModel) {
		this.roleModel = roleModel;
	}

	@Column(name = "IS_ENABLED")
	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

}
