package com.arpico.groupit.usermanagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.arpico.groupit.usermanagement.model.SubSbuModel;
import com.arpico.groupit.usermanagement.model.SubSbuSysUserModel;
import com.arpico.groupit.usermanagement.model.SysUserModel;

public interface SubSbuSysUserDao extends JpaRepository<SubSbuSysUserModel, String>{

	SubSbuSysUserModel findOneBySubSbuAndSysUser(SubSbuModel subSbu, SysUserModel sysUser);
	
	@Query(value = "SELECT * FROM WHERE sub_sbu_sys_user_id= ff993e55-f331-4b07-8488-ce1524947d1c",nativeQuery = true)
	List<SubSbuSysUserModel> findAllSubSbuSysUserModel(@Param("id") String id);
	
	List<SubSbuSysUserModel> findAll();

	
}
