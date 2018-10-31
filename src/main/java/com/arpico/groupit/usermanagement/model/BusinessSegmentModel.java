package com.arpico.groupit.usermanagement.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "BUSINESS_SEGMENT")
public class BusinessSegmentModel {

	@Column(name = "SEGMENT_ID")
	private String id;
	@Column(name = "SEGMENT_CODE")
	private String code;
	@Column(name = "SEGMENT_NAME")
	private String name;
	@Column(name = "CREATE_BY")
	private String createBy;
	@Column(name = "CREATE_DATE")
	private Date createdate;
	@Column(name = "MODIFY_BY")
	private String modifyBy;
	@Column(name = "MODIFY_DATE")
	private Date modifydate;
	
	private List<ZoneModel> zones;
	
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
	
	@OneToMany(mappedBy = "segment", targetEntity = ZoneModel.class)
	public List<ZoneModel> getZones() {
		return zones;
	}
	public void setZones(List<ZoneModel> zones) {
		this.zones = zones;
	}
	
}
