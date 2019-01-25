package com.arpico.groupit.usermanagement.dto;

import java.util.List;

public class BranchAssignDto {

	private List<String> users;
	private List<String> branch;
	
	public List<String> getUsers() {
		return users;
	}
	public void setUsers(List<String> users) {
		this.users = users;
	}
	public List<String> getBranch() {
		return branch;
	}
	public void setBranch(List<String> branch) {
		this.branch = branch;
	}
	
	
}
