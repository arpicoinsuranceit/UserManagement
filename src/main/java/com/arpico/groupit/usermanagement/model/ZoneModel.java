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
@Table(name = "ZONE")
public class ZoneModel {
	@Column(name = "ZONE_ID")
	private String id;
	@Column(name = "ZONE_CODE")
	private String code;
	@Column(name = "ZONE_NAME")
	private String name;
	@Column(name = "CREATE_BY")
	private String createBy;
	@Column(name = "CREATE_DATE")
	private Date createdate;
	@Column(name = "MODIFY_BY")
	private String modifyBy;
	@Column(name = "MODIFY_DATE")
	private Date modifydate;
	
	private BusinessSegmentModel segment;
	
	private List<RegionModel> regions;
	
	
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
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "BUSINESS_SEGMENT_ID")
	public BusinessSegmentModel getSegment() {
		return segment;
	}
	public void setSegment(BusinessSegmentModel segment) {
		this.segment = segment;
	}
	
	@OneToMany(mappedBy = "zone", targetEntity = RegionModel.class)
	public List<RegionModel> getRegions() {
		return regions;
	}
	public void setRegions(List<RegionModel> regions) {
		this.regions = regions;
	}
	
}
