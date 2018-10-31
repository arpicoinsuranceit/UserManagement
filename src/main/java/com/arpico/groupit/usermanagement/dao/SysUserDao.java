package com.arpico.groupit.usermanagement.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.arpico.groupit.usermanagement.model.SysUserModel;

public interface SysUserDao extends CrudRepository<SysUserModel, String>{
	//SysUserModel findOneByUserNameAndUserPassword(String userName, String password) throws Exception;
	SysUserModel findOneByUserNameAndIsEnabeled (String userName, Integer isEnabeled) throws Exception;

	List<SysUserModel> findAllByIsEnabeled(Integer isEnabeled) throws Exception;

	List<SysUserModel> findAllByIsEnabeledAndUserFirstNameContaining(Integer isEnabeled, String firstName) throws Exception;

	SysUserModel findByUserCode(String userName) throws Exception;

	SysUserModel findOneByUserCode(String userName);
}
