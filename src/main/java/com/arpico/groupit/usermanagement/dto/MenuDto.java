package com.arpico.groupit.usermanagement.dto;

public class MenuDto {

	private String menuName;
	private String menuDescription;
	private String href;

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

	@Override
	public String toString() {
		return "MenuDto [menuName=" + menuName + ", menuDescription=" + menuDescription + ", href=" + href + "]";
	}

}
