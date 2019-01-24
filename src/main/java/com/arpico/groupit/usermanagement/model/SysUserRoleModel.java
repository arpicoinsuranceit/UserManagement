package com.arpico.groupit.usermanagement.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "SYSUSER_ROLE")
public class SysUserRoleModel implements Serializable{

	private String sysUserRoleId;
	private SysUserModel sysUserModel;
	private RoleModel roleModel;
	private Integer enabled;

	@Id
	public String getSysUserRoleId() {
		return sysUserRoleId;
	}

	public void setSysUserRoleId(String sysUserRoleId) {
		this.sysUserRoleId = sysUserRoleId;
	}

	@JoinColumn(name = "SYSUSER_ID")
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

	@Override
	public String toString() {
		return "SysUserRoleModel [sysUserRoleId=" + sysUserRoleId + ", sysUserModel=" + sysUserModel + ", roleModel="
				+ roleModel + ", enabled=" + enabled + "]";
	}


}
