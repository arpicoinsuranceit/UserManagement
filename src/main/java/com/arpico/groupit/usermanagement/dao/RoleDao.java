package com.arpico.groupit.usermanagement.dao;

import java.util.List;


import org.springframework.data.repository.CrudRepository;
import com.arpico.groupit.usermanagement.model.RoleModel;

public interface RoleDao extends CrudRepository<RoleModel, String>{

	List<RoleModel> findAllByNameContaining(String val) throws Exception;
	
	
}
