package com.arpico.groupit.usermanagement.dto;

import java.util.List;

public class UserAssignDto {

	private List<String> roles;
	private List<String> users;
	private String userid;

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public List<String> getUsers() {
		return users;
	}

	public void setUsers(List<String> users) {
		this.users = users;
	}

	
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		return "UserAssignDto [roles=" + roles + ", users=" + users + "]";
	}

}
