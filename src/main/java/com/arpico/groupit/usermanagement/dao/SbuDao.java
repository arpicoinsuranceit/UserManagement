package com.arpico.groupit.usermanagement.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.arpico.groupit.usermanagement.model.SbuModel;

public interface SbuDao extends CrudRepository<SbuModel, String>{

	public List<SbuModel> findAllByIsEnabled(Integer isEnabled) throws Exception;
	
}
