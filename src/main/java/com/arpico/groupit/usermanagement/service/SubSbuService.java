package com.arpico.groupit.usermanagement.service;

import java.util.List;

import com.arpico.groupit.usermanagement.dto.SubSbuDto;

public interface SubSbuService {

	List<SubSbuDto> getAllBuSbu(String sbu) throws Exception;
}
