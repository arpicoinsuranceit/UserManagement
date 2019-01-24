package com.arpico.groupit.usermanagement.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.arpico.groupit.usermanagement.model.RoleMenuModel;
import com.arpico.groupit.usermanagement.model.RoleModel;

public interface RoleMenuDao extends CrudRepository<RoleMenuModel, String>{

	List<RoleMenuModel> findByRoleModelAndEnabled(RoleModel roleModel, Integer enabled);

	List<RoleMenuModel> findAllByEnabled(Integer enabled);
}
