package com.arpico.groupit.usermanagement.dao;

import org.springframework.data.repository.CrudRepository;

import com.arpico.groupit.usermanagement.model.MenuModel;

public interface MenuDao extends CrudRepository<MenuModel, String>{

}
