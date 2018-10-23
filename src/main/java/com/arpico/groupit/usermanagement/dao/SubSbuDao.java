package com.arpico.groupit.usermanagement.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.arpico.groupit.usermanagement.model.SbuModel;
import com.arpico.groupit.usermanagement.model.SubSbuModel;

public interface SubSbuDao extends CrudRepository<SubSbuModel, String>{

	public List<SubSbuModel> findAllBySbuAndIsEnabled(SbuModel sbuModel, Integer isEnabled) throws Exception;
	
}
