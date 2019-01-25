package com.arpico.groupit.usermanagement.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.arpico.groupit.usermanagement.model.BranchModel;

public interface BranchDao extends CrudRepository<BranchModel, String>{
	
	List<BranchModel> findByPhysical(String physical)throws Exception;

	List<BranchModel> findAllByCode(String code)throws Exception;
}
