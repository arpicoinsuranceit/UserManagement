package com.arpico.groupit.usermanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arpico.groupit.usermanagement.dao.SbuDao;
import com.arpico.groupit.usermanagement.dto.SbuDto;
import com.arpico.groupit.usermanagement.model.SbuModel;
import com.arpico.groupit.usermanagement.service.SbuService;

@Service
@Transactional
public class SbuServiceImpl implements SbuService{

	@Autowired
	private SbuDao sbuDao;
	
	@Override
	public List<SbuDto> getAll() throws Exception {
		
		List<SbuModel> models = sbuDao.findAllByIsEnabled(1);
		
		List<SbuDto> dtos = new ArrayList<>();
		
		models.forEach(e -> {
			dtos.add(getSbuDto(e));
		});
		
		return dtos;
	}

	private SbuDto getSbuDto(SbuModel e) {
		SbuDto dto = new SbuDto();
		
		dto.setSbuId(e.getSbuId());
		dto.setSbuDescription(e.getSbuName());
		dto.setSbuShortName(e.getSbuShortName());
		
		return dto;
	}
	
	

}
