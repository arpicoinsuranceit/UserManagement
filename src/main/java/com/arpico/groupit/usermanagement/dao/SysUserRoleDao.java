package com.arpico.groupit.usermanagement.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.arpico.groupit.usermanagement.dto.RoleDto;
import com.arpico.groupit.usermanagement.model.RoleModel;
import com.arpico.groupit.usermanagement.model.SysUserModel;
import com.arpico.groupit.usermanagement.model.SysUserRoleModel;

public interface SysUserRoleDao extends JpaRepository<SysUserRoleModel, String> {
	
	
	@Query(value="SELECT * FROM sysuser_role",nativeQuery=true)
	ArrayList<SysUserRoleModel> findallSysUserRoleModel() throws Exception;
	
	
	@Query(value="SELECT s FROM SysUserRoleModel s")
	List<SysUserRoleModel> getAll();
	
	SysUserRoleModel findByRoleModel(RoleModel roleModel)throws Exception;
	
 }
