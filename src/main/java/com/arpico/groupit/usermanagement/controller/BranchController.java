package com.arpico.groupit.usermanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arpico.groupit.usermanagement.dto.ResponseDto;
import com.arpico.groupit.usermanagement.service.BranchService;

@RestController
@CrossOrigin(origins = "*")
public class BranchController {

	@Autowired
	private BranchService branchService;

	@PostMapping("/findbranch")
	public ResponseEntity<Object> findBranch(@RequestParam("userName") String userName) {
		String branch;
		try {
			branch = branchService.getBranch(userName);
			System.out.println(branch);
			
			ResponseDto dto = new ResponseDto();
			dto.setCode("200");
			dto.setMessage(branch);
			
			return new ResponseEntity<Object>(dto, HttpStatus.OK);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ResponseDto dto = new ResponseDto();
			dto.setCode("404");
			dto.setMessage(e.getMessage());
			
			return new ResponseEntity<Object>(dto, HttpStatus.OK);
		}

	}
	
	@RequestMapping(value = "/physicalBranchAll", method = RequestMethod.GET)
	public List<String> getAllPhysicalBranches() throws Exception {
		
		return branchService.getAllPhysicalBranches();
		
	}

}
