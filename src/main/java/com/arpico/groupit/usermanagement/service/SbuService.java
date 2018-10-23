package com.arpico.groupit.usermanagement.service;

import java.util.List;

import com.arpico.groupit.usermanagement.dto.SbuDto;

public interface SbuService {
	
	List<SbuDto> getAll () throws Exception;

}
