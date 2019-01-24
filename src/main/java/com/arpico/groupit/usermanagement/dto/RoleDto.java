package com.arpico.groupit.usermanagement.dto;

import java.util.List;

public class RoleDto {

	private String id;
	private String sbu;
	private String name;
	private String description;
	private List<String> menus;
	private List<MenuDto> menuDto;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSbu() {
		return sbu;
	}

	public void setSbu(String sbu) {
		this.sbu = sbu;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getMenus() {
		return menus;
	}

	public void setMenus(List<String> menus) {
		this.menus = menus;
	}
	
	

	public List<MenuDto> getMenuDto() {
		return menuDto;
	}

	public void setMenuDto(List<MenuDto> menuDto) {
		this.menuDto = menuDto;
	}

	@Override
	public String toString() {
		return "RoleDto [name=" + name + ", description=" + description + ", menus=" + menus + "]";
	}

}
