package com.arpico.groupit.usermanagement.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.arpico.groupit.usermanagement.dto.BranchAssignDto;
import com.arpico.groupit.usermanagement.dto.BranchDto;
import com.arpico.groupit.usermanagement.dto.ResponseDto;
import com.arpico.groupit.usermanagement.dto.RoleDto;
import com.arpico.groupit.usermanagement.service.BranchService;

@RestController
@PropertySource("classpath:application.properties")
@CrossOrigin()
@RequestMapping("branch")
public class BranchController {

	@Autowired
	private BranchService branchService;
	
	@Value("${server.context-path}")
	private String path;
	
	@Autowired
	ServletContext context;

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
	
	
	@RequestMapping("navUserAssignBranch")
	public ModelAndView navAddRole () throws Exception {
    	context.setAttribute("path", path);
		ModelAndView mav = new ModelAndView("pages/user/assign_branch");
		mav.addObject("title", "USER | ASSIGN BRANCH");
		return mav;
 }
	
	@GetMapping("/getAllbranch/{code}")
	public ResponseEntity<Object> getAllBranch(@PathVariable String code) throws Exception{
		System.out.println(code);
		List<BranchDto> getall=branchService.getAllBranch(code);
		
		return new ResponseEntity<Object>(getall, HttpStatus.OK);
		
	}
	
	
	 @PostMapping(value = "asignBranch")
	    public ResponseEntity<Object> addRole(@RequestBody BranchAssignDto branchAssignDto) throws Exception{
			branchService.assignBranch(branchAssignDto);
	    	return new ResponseEntity<>("Work",HttpStatus.OK);
		}
	

}
