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
@Table(name="SUB_SBU_SYSUSER")
public class SubSbuSysUser implements Serializable{
	
	@Column(name="SUB_SBU_SYSUSER_ID")
	private String subSbuSysUserId;
	private SubSbu subSbuId;
	private SysUser sysUserId;
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
	public String getSubSbuSysUserId() {
		return subSbuSysUserId;
	}
	public void setSubSbuSysUserId(String subSbuSysUserId) {
		this.subSbuSysUserId = subSbuSysUserId;
	}
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="SUB_SBU_ID", nullable=false)
	public SubSbu getSubSbuId() {
		return subSbuId;
	}
	public void setSubSbuId(SubSbu subSbuId) {
		this.subSbuId = subSbuId;
	}
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="SYSUSER_ID", nullable=false)
	public SysUser getSysUserId() {
		return sysUserId;
	}
	public void setSysUserId(SysUser sysUserId) {
		this.sysUserId = sysUserId;
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
		return "SubSbuSysUser [subSbuSysUserId=" + subSbuSysUserId + ", subSbuId=" + subSbuId + ", sysUserId="
				+ sysUserId + ", isEnabled=" + isEnabled + ", createdTime=" + createdTime + ", updatedTime="
				+ updatedTime + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy + "]";
	}
	
	
	

}
