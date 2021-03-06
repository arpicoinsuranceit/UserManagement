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
@Table(name = "MENU")
public class MenuModel implements Serializable {

	@Column(name = "MENU_ID")
	private String menuId;

	@Column(name = "MENU")
	private String menuName;

	@Column(name = "MENU_DESCRIPTION")
	private String menuDescription;

	@Column(name = "HREF")
	private String href;

	@Column(name = "ICON")
	private String icon;

	@Column(name = "PARENT")
	private String parent;

	@Column(name = "LEVEL")
	private Integer level;

	@Column(name = "SUB_MENU_BR")
	private Integer subMenuBr;

	@Column(name = "GRID_NO")
	private Integer grid;

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

	private SubSbuModel subSbuModel;
	
	private List<RoleMenuModel> roleMenuModels;
	
	@Id
	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuDescription() {
		return menuDescription;
	}

	public void setMenuDescription(String menuDescription) {
		this.menuDescription = menuDescription;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getSubMenuBr() {
		return subMenuBr;
	}

	public void setSubMenuBr(Integer subMenuBr) {
		this.subMenuBr = subMenuBr;
	}

	public Integer getGrid() {
		return grid;
	}

	public void setGrid(Integer grid) {
		this.grid = grid;
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

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "SUB_SBU_ID")
	public SubSbuModel getSubSbuModel() {
		return subSbuModel;
	}

	public void setSubSbuModel(SubSbuModel subSbuModel) {
		this.subSbuModel = subSbuModel;
	}

	@OneToMany(mappedBy = "menuModel", targetEntity = RoleMenuModel.class)
	public List<RoleMenuModel> getRoleMenuModels() {
		return roleMenuModels;
	}

	public void setRoleMenuModels(List<RoleMenuModel> roleMenuModels) {
		this.roleMenuModels = roleMenuModels;
	}

	
	@Override
	public String toString() {
		return "MenuModel [menuId=" + menuId + ", menuName=" + menuName + ", menuDescription=" + menuDescription
				+ ", href=" + href + ", icon=" + icon + ", parent=" + parent + ", level=" + level + ", subMenuBr="
				+ subMenuBr + ", grid=" + grid + ", isEnabled=" + isEnabled + ", createdTime=" + createdTime
				+ ", updatedTime=" + updatedTime + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy + "]";
	}

	
}
