package com.arpico.groupit.usermanagement.dto;

import java.util.List;

public class BranchAssignDto {

	private List<String> users;
	private List<String> branch;
	private SysUserDto userdto;
	private String userid;
	
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
	public SysUserDto getUserdto() {
		return userdto;
	}
	public void setUserdto(SysUserDto userdto) {
		this.userdto = userdto;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	
}
