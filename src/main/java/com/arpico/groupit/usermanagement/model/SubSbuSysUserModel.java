package com.arpico.groupit.usermanagement.model;


import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="SUB_SBU_SYSUSER")
public class SubSbuSysUserModel {
	
	@Column(name="SUB_SBU_SYSUSER_ID")
	private String subSbuSysUserId;
	
	private SubSbuModel subSbu;
	
	private SysUserModel sysUser;
	
	@Column(name="IS_ENABELED")
	private Integer isEnabled;
	
	@Column(name="CREATED_TIME")
	private Date createdTime;
	
	@Column(name="UPDATED_TIME")
	private Date updatedTime;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	@Column(name="UPDATED_BY")
	private String updatedBy;
	
	
	private List<SubSbuSysUserMenuModel> subSbuSysUserMenus;
	
	
	@Id
	public String getSubSbuSysUserId() {
		return subSbuSysUserId;
	}
	public void setSubSbuSysUserId(String subSbuSysUserId) {
		this.subSbuSysUserId = subSbuSysUserId;
	}
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="SUB_SBU_ID", nullable=false)
	public SubSbuModel getSubSbu() {
		return subSbu;
	}
	public void setSubSbu(SubSbuModel subSbu) {
		this.subSbu = subSbu;
	}
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="SYSUSER_ID", nullable=false)
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
	
	@OneToMany(mappedBy = "subSbuSysUser", targetEntity = SubSbuSysUserMenuModel.class)
	public List<SubSbuSysUserMenuModel> getSubSbuSysUserMenus() {
		return subSbuSysUserMenus;
	}
	public void setSubSbuSysUserMenus(List<SubSbuSysUserMenuModel> subSbuSysUserMenus) {
		this.subSbuSysUserMenus = subSbuSysUserMenus;
	}
}
