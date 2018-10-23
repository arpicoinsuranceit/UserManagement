package com.arpico.groupit.usermanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.arpico.groupit.usermanagement.dto.SubSbuDto;
import com.arpico.groupit.usermanagement.service.SubSbuService;

@Controller
public class SubSbuController {
	
	@Autowired
	private SubSbuService subSbuService;
	
	@GetMapping(value ="/getSubSbu/{sbu}")
	public ResponseEntity<Object> getSubSbus(@PathVariable String sbu ) throws Exception{
		List<SubSbuDto> list =  subSbuService.getAllBuSbu(sbu);
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}

}
