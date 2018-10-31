package com.arpico.groupit.usermanagement.dao;

import org.springframework.data.repository.CrudRepository;

import com.arpico.groupit.usermanagement.model.SubSbuModel;
import com.arpico.groupit.usermanagement.model.SubSbuSysUserModel;
import com.arpico.groupit.usermanagement.model.SysUserModel;

public interface SubSbuSysUserDao extends CrudRepository<SubSbuSysUserModel, String>{

	SubSbuSysUserModel findOneBySubSbuAndSysUser(SubSbuModel subSbu, SysUserModel sysUser);

}
