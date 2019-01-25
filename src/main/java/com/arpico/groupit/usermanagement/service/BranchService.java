package com.arpico.groupit.usermanagement.service;

import java.util.List;

import com.arpico.groupit.usermanagement.dto.BranchAssignDto;
import com.arpico.groupit.usermanagement.dto.BranchDto;

public interface BranchService {

	String getBranch(String userName) throws Exception;
	
	List<String> getAllPhysicalBranches() throws Exception;
	
	List<BranchDto> getAllBranch(String code)throws Exception;

	String assignBranch(BranchAssignDto branchAssignDto)throws Exception;
	
}
