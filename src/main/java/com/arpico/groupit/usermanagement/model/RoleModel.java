package com.arpico.groupit.usermanagement.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ROLE")
public class RoleModel {

	private String id;

	private String name;
	private String description;
	private Integer enable;
	private Date createDate;
	private String createBy;
	private Date updateDate;
	private String updateBy;

	private List<RoleMenuModel> roleMenuModels;
	private List<SysUserRoleModel> sysUserRoleModels;

	@Id
	@Column(name = "ROLE_ID")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "ROLE_NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "IS_ENABLED")
	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	@Column(name = "CREATED_DATE")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "CREATED_BY")
	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@Column(name = "UPDATED_DATE")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "UPDATED_BY")
	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	@OneToMany(mappedBy = "roleModel", targetEntity = RoleMenuModel.class)
	public List<RoleMenuModel> getRoleMenuModels() {
		return roleMenuModels;
	}

	public void setRoleMenuModels(List<RoleMenuModel> roleMenuModels) {
		this.roleMenuModels = roleMenuModels;
	}

	@OneToMany(mappedBy = "roleModel", targetEntity = SysUserRoleModel.class)
	public List<SysUserRoleModel> getSysUserRoleModels() {
		return sysUserRoleModels;
	}

	public void setSysUserRoleModels(List<SysUserRoleModel> sysUserRoleModels) {
		this.sysUserRoleModels = sysUserRoleModels;
	}

}
