package com.arpico.groupit.usermanagement.dto;

import java.util.List;

public class SubMenusDto {

	private String menuId;
	private String menuName;
	private String menuDescription;
	private String href;
	private String parent;
	private Integer level;
	private String icon;
	private Integer subMenuBr;
	private String system;
	private List<MenuDto> menu;
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
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getSubMenuBr() {
		return subMenuBr;
	}
	public void setSubMenuBr(Integer subMenuBr) {
		this.subMenuBr = subMenuBr;
	}
	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}
	public List<MenuDto> getMenu() {
		return menu;
	}
	public void setMenu(List<MenuDto> menu) {
		this.menu = menu;
	}
	
	
	
}
