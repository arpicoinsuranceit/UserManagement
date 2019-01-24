package com.arpico.groupit.usermanagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.arpico.groupit.usermanagement.model.SysUserModel;

public interface SysUserDao extends JpaRepository<SysUserModel, String>{
	//SysUserModel findOneByUserNameAndUserPassword(String userName, String password) throws Exception;
	SysUserModel findOneByUserNameAndIsEnabeled (String userName, Integer isEnabeled) throws Exception;

	List<SysUserModel> findAllByIsEnabeled(Integer isEnabeled) throws Exception;

	List<SysUserModel> findAllByIsEnabeledAndUserFirstNameContaining(Integer isEnabeled, String firstName) throws Exception;

	SysUserModel findByUserCode(String userName) throws Exception;

	SysUserModel findOneByUserCode(String userName);
	
	@Query("SELECT s FROM SysUserModel s")
	List<SysUserModel> findallSysUserModel() throws Exception;
}
