package com.arpico.groupit.usermanagement.dao;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.arpico.groupit.usermanagement.model.BranchModel;
import com.arpico.groupit.usermanagement.model.SysUserBranchModel;
import com.arpico.groupit.usermanagement.model.SysUserModel;

public interface SysUserBranchDao extends CrudRepository<SysUserBranchModel, String>{

	SysUserBranchModel findOneByBranchAndSysUser(BranchModel branchModel,SysUserModel sysUserModel)throws Exception;
}
