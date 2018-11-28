package com.arpico.groupit.usermanagement.service;

import java.util.List;

public interface BranchService {

	String getBranch(String userName) throws Exception;
	
	List<String> getAllPhysicalBranches() throws Exception;

}
