package com.arpico.groupit.usermanagement.dao;

import org.springframework.data.repository.CrudRepository;

import com.arpico.groupit.usermanagement.model.MenuModel;
import com.arpico.groupit.usermanagement.model.SubSbuSysUserMenuModel;
import com.arpico.groupit.usermanagement.model.SubSbuSysUserModel;

public interface SubSbuSysUserMenuDao extends CrudRepository<SubSbuSysUserMenuModel, String>{

	SubSbuSysUserMenuModel findOneBySubSbuSysUserAndMenu(SubSbuSysUserModel subSbuSysUser, MenuModel menu);

	
	
}
