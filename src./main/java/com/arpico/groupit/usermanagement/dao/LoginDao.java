package com.arpico.groupit.usermanagement.dao;

import org.springframework.data.repository.CrudRepository;

import com.arpico.groupit.usermanagement.model.Login;

public interface LoginDao extends CrudRepository<Login, Integer>{

	Login findOneByUserNameAndPassword(String userName, String password) throws Exception;
	
	Login findOneByUserName(String userName) throws Exception;
	
}
