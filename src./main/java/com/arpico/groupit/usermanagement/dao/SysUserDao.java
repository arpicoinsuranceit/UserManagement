package com.arpico.groupit.usermanagement.dao;

import org.springframework.data.repository.CrudRepository;
import com.arpico.groupit.usermanagement.model.Login;
import com.arpico.groupit.usermanagement.model.SysUser;

public interface SysUserDao extends CrudRepository<SysUser, String>{
	
	SysUser findOneByUserNameAndPassword(String userName, String password) throws Exception;
	
	SysUser findOneByUserName(String userName) throws Exception;

}
