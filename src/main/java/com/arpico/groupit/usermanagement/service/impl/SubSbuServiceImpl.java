package com.arpico.groupit.usermanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arpico.groupit.usermanagement.dao.SbuDao;
import com.arpico.groupit.usermanagement.dao.SubSbuDao;
import com.arpico.groupit.usermanagement.dto.SubSbuDto;
import com.arpico.groupit.usermanagement.model.SbuModel;
import com.arpico.groupit.usermanagement.model.SubSbuModel;
import com.arpico.groupit.usermanagement.service.SubSbuService;

@Service
@Transactional
public class SubSbuServiceImpl implements SubSbuService {

	@Autowired
	private SubSbuDao subSbuDao;

	@Autowired
	private SbuDao sbuDao;

	@Override
	public List<SubSbuDto> getAllBuSbu(String sbu) throws Exception {

		SbuModel sbuModel = sbuDao.findOne(sbu);

		List<SubSbuModel> subSbuModels = subSbuDao.findAllBySbuAndIsEnabled(sbuModel, 1);

		List<SubSbuDto> dtos = new ArrayList<>();

		subSbuModels.forEach(e -> {
			dtos.add(getSubSbuDto(e));
		});

		System.out.println(dtos.size());
		
		return dtos;
	}

	private SubSbuDto getSubSbuDto(SubSbuModel e) {
		SubSbuDto dto = new SubSbuDto();
		
		dto.setDescription(e.getSubSbuName());
		dto.setId(e.getSubSbuId());
		
		return dto;
	}

}
