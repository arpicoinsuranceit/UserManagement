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
import javax.persistence.Table;

@Entity
@Table(name = "SUB_SBU")
public class SubSbuModel implements Serializable {

	@Column(name = "SUB_SBU_ID")
	private String subSbuId;

	private SbuModel sbu;

	@Column(name = "SUB_SBU_NAME")
	private String subSbuName;

	@Column(name = "SUB_SBU_SHORT_NAME")
	private String subSbuShortName;

	@Column(name = "SUB_SBU_ADDRESS_1")
	private String subSbuAddress1;

	@Column(name = "SUB_SBU_ADDRESS_2")
	private String subSbuAddress2;

	@Column(name = "SUB_SBU_ADDRESS_3")
	private String subSbuAddress3;

	@Column(name = "SUB_SBU_CITY")
	private String subSbuCity;

	@Column(name = "SUB_SBU_EMAIL")
	private String subSbuEmail;

	@Column(name = "SUB_SBU_FAX")
	private String subSbuFax;

	@Column(name = "SUB_SBU_TAX_NO")
	private String subSbuTaxNo;

	@Column(name = "SUB_SBU_TELE")
	private String subSbuTele;

	@Column(name = "SUB_SBU_TELE_X")
	private String subSbuTeleX;

	@Column(name = "IS_ENABELED")
	private Integer isEnabled;

	@Column(name = "CREATED_TIME")
	private Date createdTime;

	@Column(name = "UPDATED_TIME")
	private Date updatedTime;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_BY")
	private String updatedBy;

	private List<SubSbuSysUserModel> subSbuSysUsers;

	private List<MenuModel> menuModels;

	@Id
	public String getSubSbuId() {
		return subSbuId;
	}

	public void setSubSbuId(String subSbuId) {
		this.subSbuId = subSbuId;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "SBU_ID")
	public SbuModel getSbu() {
		return sbu;
	}

	public void setSbu(SbuModel sbu) {
		this.sbu = sbu;
	}

	public String getSubSbuName() {
		return subSbuName;
	}

	public void setSubSbuName(String subSbuName) {
		this.subSbuName = subSbuName;
	}

	public String getSubSbuShortName() {
		return subSbuShortName;
	}

	public void setSubSbuShortName(String subSbuShortName) {
		this.subSbuShortName = subSbuShortName;
	}

	public String getSubSbuAddress1() {
		return subSbuAddress1;
	}

	public void setSubSbuAddress1(String subSbuAddress1) {
		this.subSbuAddress1 = subSbuAddress1;
	}

	public String getSubSbuAddress2() {
		return subSbuAddress2;
	}

	public void setSubSbuAddress2(String subSbuAddress2) {
		this.subSbuAddress2 = subSbuAddress2;
	}

	public String getSubSbuAddress3() {
		return subSbuAddress3;
	}

	public void setSubSbuAddress3(String subSbuAddress3) {
		this.subSbuAddress3 = subSbuAddress3;
	}

	public String getSubSbuCity() {
		return subSbuCity;
	}

	public void setSubSbuCity(String subSbuCity) {
		this.subSbuCity = subSbuCity;
	}

	public String getSubSbuEmail() {
		return subSbuEmail;
	}

	public void setSubSbuEmail(String subSbuEmail) {
		this.subSbuEmail = subSbuEmail;
	}

	public String getSubSbuFax() {
		return subSbuFax;
	}

	public void setSubSbuFax(String subSbuFax) {
		this.subSbuFax = subSbuFax;
	}

	public String getSubSbuTaxNo() {
		return subSbuTaxNo;
	}

	public void setSubSbuTaxNo(String subSbuTaxNo) {
		this.subSbuTaxNo = subSbuTaxNo;
	}

	public String getSubSbuTele() {
		return subSbuTele;
	}

	public void setSubSbuTele(String subSbuTele) {
		this.subSbuTele = subSbuTele;
	}

	public String getSubSbuTeleX() {
		return subSbuTeleX;
	}

	public void setSubSbuTeleX(String subSbuTeleX) {
		this.subSbuTeleX = subSbuTeleX;
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

	@OneToMany(targetEntity = SubSbuSysUserModel.class)
	public List<SubSbuSysUserModel> getSubSbuSysUsers() {
		return subSbuSysUsers;
	}

	public void setSubSbuSysUsers(List<SubSbuSysUserModel> subSbuSysUsers) {
		this.subSbuSysUsers = subSbuSysUsers;
	}

	@OneToMany(mappedBy = "subSbuModel", targetEntity = MenuModel.class)
	public List<MenuModel> getMenuModels() {
		return menuModels;
	}

	public void setMenuModels(List<MenuModel> menuModels) {
		this.menuModels = menuModels;
	}

	@Override
	public String toString() {
		return "SubSbuModel [subSbuId=" + subSbuId + ", sbu=" + sbu + ", subSbuName=" + subSbuName
				+ ", subSbuShortName=" + subSbuShortName + ", subSbuAddress1=" + subSbuAddress1 + ", subSbuAddress2="
				+ subSbuAddress2 + ", subSbuAddress3=" + subSbuAddress3 + ", subSbuCity=" + subSbuCity
				+ ", subSbuEmail=" + subSbuEmail + ", subSbuFax=" + subSbuFax + ", subSbuTaxNo=" + subSbuTaxNo
				+ ", subSbuTele=" + subSbuTele + ", subSbuTeleX=" + subSbuTeleX + ", isEnabled=" + isEnabled
				+ ", createdTime=" + createdTime + ", updatedTime=" + updatedTime + ", createdBy=" + createdBy
				+ ", updatedBy=" + updatedBy + ", subSbuSysUsers=" + subSbuSysUsers + ", menuModels=" + menuModels
				+ "]";
	}

}
