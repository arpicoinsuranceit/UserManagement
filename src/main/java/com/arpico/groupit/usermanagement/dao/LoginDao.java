package com.arpico.groupit.usermanagement.dao;

import org.springframework.data.repository.CrudRepository;

import com.arpico.groupit.usermanagement.model.LoginModel;

public interface LoginDao extends CrudRepository<LoginModel, String>{

}
