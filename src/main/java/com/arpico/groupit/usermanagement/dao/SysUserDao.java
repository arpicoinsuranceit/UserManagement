package com.arpico.groupit.usermanagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.arpico.groupit.usermanagement.model.RoleMenuModel;
import com.arpico.groupit.usermanagement.model.SysUserModel;

public interface SysUserDao extends JpaRepository<SysUserModel, String>{

	SysUserModel findOneByUserNameAndIsEnabeled (String userName, Integer isEnabeled) throws Exception;

	List<SysUserModel> findAllByIsEnabeled(Integer isEnabeled) throws Exception;

	List<SysUserModel> findAllByIsEnabeledAndUserFirstNameContaining(Integer isEnabeled, String firstName) throws Exception;

	SysUserModel findByUserCode(String userName) throws Exception;

	SysUserModel findOneByUserCode(String userName);
	
	SysUserModel findOneByUserId(String userid)throws Exception;
		
	
}
