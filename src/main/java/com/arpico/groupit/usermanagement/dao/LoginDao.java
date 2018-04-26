package com.arpico.groupit.usermanagement.dao;

import org.springframework.data.repository.CrudRepository;

import com.arpico.groupit.usermanagement.model.Login;

public interface LoginDao extends CrudRepository<Login, Integer>{

}
