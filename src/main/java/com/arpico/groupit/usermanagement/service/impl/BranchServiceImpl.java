package com.arpico.groupit.usermanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arpico.groupit.usermanagement.dao.BranchDao;
import com.arpico.groupit.usermanagement.dao.SysUserDao;
import com.arpico.groupit.usermanagement.model.BranchModel;
import com.arpico.groupit.usermanagement.model.SysUserBranchModel;
import com.arpico.groupit.usermanagement.model.SysUserModel;
import com.arpico.groupit.usermanagement.service.BranchService;

@Service
@Transactional
public class BranchServiceImpl implements BranchService{

	@Autowired
	private SysUserDao sysUserDao;
	
	@Autowired
	private BranchDao branchDao;
	
	@Override
	public String getBranch(String userName) throws Exception {
		SysUserModel sysUserModel = sysUserDao.findOneByUserCode(userName);
		
		System.out.println(userName);
		
		String branchs = "";
		
		for (SysUserBranchModel sysUserBranchModel : sysUserModel.getSysUserBranchModels()) {
			branchs += sysUserBranchModel.getBranch().getCode() + ",";
		}
		
		branchs = branchs.substring(0 , branchs.length()-1);
		
		
		return branchs; //sysUserModel.getBranch().getCode();
	}

	@Override
	public List<String> getAllPhysicalBranches() throws Exception {
		
		List<BranchModel> branchModels=branchDao.findByPhysical("1");
		List<String> branchcodes=new ArrayList<>();
		branchModels.forEach(branch -> {
			
			branchcodes.add(branch.getCode());
		});
		
		return branchcodes;
	}

}
