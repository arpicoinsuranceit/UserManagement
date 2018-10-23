package com.arpico.groupit.usermanagement.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.arpico.groupit.usermanagement.dto.RoleDto;
import com.arpico.groupit.usermanagement.dto.UserAssignDto;
import com.arpico.groupit.usermanagement.dto.UserTokenDto;
import com.arpico.groupit.usermanagement.service.SysUserService;

@Controller
@PropertySource("classpath:application.properties")
@RequestMapping("user")
public class UserController {
	

	@Value("${server.context-path}")
	private String path;
    
    @Autowired
	ServletContext context;
    
    @Autowired
    private SysUserService sysUserService;
    
    @RequestMapping("assignroles")
	public ModelAndView navAddRole () throws Exception {
    	context.setAttribute("path", path);
		
    	ModelAndView mav = new ModelAndView("pages/user/assign_role");
		
		mav.addObject("title", "USER | ASSIGN ROLE");
		
		return mav;
	}
    
    @GetMapping("/get_user/{val}")
   	@ResponseBody
   	public ResponseEntity<Object> getUsers(@PathVariable String val) throws Exception {

   		List<UserTokenDto> userTokenDtos = sysUserService.getAll(val);
   		
   		System.out.println(val);

   		return new ResponseEntity<>(userTokenDtos, HttpStatus.OK);
   	}

    
    @PostMapping("/assign_role")
   	@ResponseBody
   	public ResponseEntity<Object> assignRoles(@RequestBody UserAssignDto userAssignDto) throws Exception {

    	String resp = sysUserService.assignUser(userAssignDto);

    	if(resp.equals("200")) {
   		return new ResponseEntity<>("Work", HttpStatus.OK);
    	} else {
    		return new ResponseEntity<>("Fail", HttpStatus.INTERNAL_SERVER_ERROR);
    	}
   	}

}
