package com.arpico.groupit.usermanagement.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="SBU")
public class Sbu implements Serializable{
	
	@Column(name="SBU_ID")
	private String sbuId;
	@Column(name="SBU_NAME")
	private String sbuName;
	@Column(name="SBU_SHORT_NAME")
	private String sbuShortName;
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
	
	private List<Sbu> allSbus;
	
	@Id
	public String getSbuId() {
		return sbuId;
	}
	public void setSbuId(String sbuId) {
		this.sbuId = sbuId;
	}
	public String getSbuName() {
		return sbuName;
	}
	public void setSbuName(String sbuName) {
		this.sbuName = sbuName;
	}
	public String getSbuShortName() {
		return sbuShortName;
	}
	public void setSbuShortName(String sbuShortName) {
		this.sbuShortName = sbuShortName;
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

	@OneToMany(mappedBy = "sbu", targetEntity = SubSbu.class)
	public List<Sbu> getAllSbus() {
		return allSbus;
	}
	public void setAllSbus(List<Sbu> allSbus) {
		this.allSbus = allSbus;
	}
	
	@Override
	public String toString() {
		return "Sbu [sbuId=" + sbuId + ", sbuName=" + sbuName + ", sbuShortName=" + sbuShortName + ", isEnabled="
				+ isEnabled + ", createdTime=" + createdTime + ", updatedTime=" + updatedTime + ", createdBy="
				+ createdBy + ", updatedBy=" + updatedBy + ", allSbus=" + allSbus + "]";
	}
	
}
