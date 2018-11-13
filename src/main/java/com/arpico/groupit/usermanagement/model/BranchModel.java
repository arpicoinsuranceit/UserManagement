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

@Table(name = "BRANCH")
@Entity
public class BranchModel {

	@Column(name = "BRANCH_ID")
	private String id;
	@Column(name = "BRANCH_CODE")
	private String code;
	@Column(name = "BRANCH_NAME")
	private String name;
	@Column(name = "BRANCH_SHORT_NAME")
	private String SName;
	@Column(name = "BRANCH_TELE")
	private String tele;
	@Column(name = "BRANCH_WEB")
	private String webSite;
	@Column(name = "BRANCH_EMAIL")
	private String email;
	@Column(name = "BRANCH_FAX")
	private String fax;
	@Column(name = "IS_PHYSICAL")
	private String physical;
	@Column(name = "CREATE_BY")
	private String createBy;
	@Column(name = "CREATE_DATE")
	private Date createdate;
	@Column(name = "MODIFY_BY")
	private String modifyBy;
	@Column(name = "MODIFY_DATE")
	private Date modifydate;

	private RegionModel region;
	private List<SysUserBranchModel> sysUserBranchModels;

	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSName() {
		return SName;
	}

	public void setSName(String sName) {
		SName = sName;
	}

	public String getTele() {
		return tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getPhysical() {
		return physical;
	}

	public void setPhysical(String physical) {
		this.physical = physical;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifydate() {
		return modifydate;
	}

	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}

	@JoinColumn(name = "REGION_ID")
	@ManyToOne(cascade = CascadeType.ALL)
	public RegionModel getRegion() {
		return region;
	}

	public void setRegion(RegionModel region) {
		this.region = region;
	}

	@OneToMany(mappedBy = "branch", targetEntity = SysUserBranchModel.class)
	public List<SysUserBranchModel> getSysUserBranchModels() {
		return sysUserBranchModels;
	}

	public void setSysUserBranchModels(List<SysUserBranchModel> sysUserBranchModels) {
		this.sysUserBranchModels = sysUserBranchModels;
	}

}
