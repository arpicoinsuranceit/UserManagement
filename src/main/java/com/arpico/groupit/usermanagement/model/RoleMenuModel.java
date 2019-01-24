package com.arpico.groupit.usermanagement.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ROLE_MENU")
public class RoleMenuModel implements Serializable{

	private String id;
	private RoleModel roleModel;
	private MenuModel menuModel;
	private Integer enabled;

	@Id
	@Column(name = "ROLE_MENU_ID")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JoinColumn(name = "ROLE_ID")
	@ManyToOne(cascade = CascadeType.ALL)
	public RoleModel getRoleModel() {
		return roleModel;
	}

	public void setRoleModel(RoleModel roleModel) {
		this.roleModel = roleModel;
	}

	@JoinColumn(name = "MENU_ID")
	@ManyToOne(cascade = CascadeType.ALL)
	public MenuModel getMenuModel() {
		return menuModel;
	}

	public void setMenuModel(MenuModel menuModel) {
		this.menuModel = menuModel;
	}

	@Column(name = "IS_ENABLED")
	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}


}
