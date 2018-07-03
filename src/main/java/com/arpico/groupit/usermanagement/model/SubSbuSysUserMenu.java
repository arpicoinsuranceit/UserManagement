package com.arpico.groupit.usermanagement.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="SUB_SBU_SYSUSER_MENU")
public class SubSbuSysUserMenu implements Serializable{
	
	@Column(name="SUB_SBU_SYSUSER_MENU_ID")
	private String subSbuSysUserMenu;
	private SubSbuSysUser subSbuSysUser;
	private Menu menu;
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
	
	@Id
	public String getSubSbuSysUserMenu() {
		return subSbuSysUserMenu;
	}
	public void setSubSbuSysUserMenu(String subSbuSysUserMenu) {
		this.subSbuSysUserMenu = subSbuSysUserMenu;
	}
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="SUB_SBU_SYSUSER_ID", nullable=false)
	public SubSbuSysUser getSubSbuSysUser() {
		return subSbuSysUser;
	}
	public void setSubSbuSysUser(SubSbuSysUser subSbuSysUser) {
		this.subSbuSysUser = subSbuSysUser;
	}
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="MENU_ID", nullable=false)
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
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
	@Override
	public String toString() {
		return "SubSbuSysUserMenu [subSbuSysUserMenu=" + subSbuSysUserMenu + ", subSbuSysUser=" + subSbuSysUser
				+ ", menu=" + menu + ", isEnabled=" + isEnabled + ", createdTime=" + createdTime + ", updatedTime="
				+ updatedTime + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy + "]";
	}
	
	
	
	

}
