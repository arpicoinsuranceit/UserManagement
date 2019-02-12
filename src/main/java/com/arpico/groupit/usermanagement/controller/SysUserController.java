package com.arpico.groupit.usermanagement.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.arpico.groupit.usermanagement.dto.BranchAssignDto;
import com.arpico.groupit.usermanagement.dto.MenuDto;
import com.arpico.groupit.usermanagement.dto.RoleDto;
import com.arpico.groupit.usermanagement.dto.SbuDto;
import com.arpico.groupit.usermanagement.dto.SysUserDto;
import com.arpico.groupit.usermanagement.dto.UserAssignDto;
import com.arpico.groupit.usermanagement.service.SysUserService;

@RestController
@PropertySource("classpath:application.properties")
@CrossOrigin()
@RequestMapping("sysUser")
public class SysUserController {
	@Autowired
	private SysUserService sysUserService;
	
	@Value("${server.context-path}")
	private String path;
	
	@Autowired
	ServletContext context;
	
	public List<MenuDto> getMenuByUser(@RequestParam String token){
		try {
			return sysUserService.getAllByUser(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	 @RequestMapping("AllUsers")
	public ModelAndView navAllUsers () throws Exception {
    	context.setAttribute("path", path);
		
    	
    	ModelAndView mav = new ModelAndView("pages/user/AllUsers");
		
		mav.addObject("title", "USER | ALL USERS");
		return mav;
 }
	 
	 
	
	 @RequestMapping("addUser")
		public ModelAndView navAddRole () throws Exception {
	    	context.setAttribute("path", path);
			
	    	
	    	ModelAndView mav = new ModelAndView("pages/user/AddUser");
			
			mav.addObject("title", "USER | ADD USER");
			return mav;
	 }
	 
	 @RequestMapping(value = "savesysuser")
	 @ResponseBody
		public ResponseEntity<Object> saveSysUser (@RequestBody SysUserDto sysUserDto) throws Exception {
	    	
		 	sysUserService.saveSysUser(sysUserDto);
			
	    	return new ResponseEntity<>("Work",HttpStatus.OK);
			
	 }
	 
	 @RequestMapping(value = "updatesysuser")
	 @ResponseBody
		public ResponseEntity<Object> updateSysUser (@RequestBody SysUserDto sysUserDto) throws Exception {
	    	
		 	sysUserService.updateUser(sysUserDto);
			
	    	return new ResponseEntity<>("Work",HttpStatus.OK);
			
	 }
	 
	 @GetMapping("/get_Usercode/{userName}")
	 @ResponseBody
	 public String searchUserCode(@PathVariable String userName) throws Exception {
	    	return sysUserService.searchUserCode(userName); 
		}
	  
	 
	 @GetMapping("/getAllUsers")
	 public Map getAllUsers() throws Exception{
		 
		 List entities = new ArrayList();

			List<SysUserDto> sysUserdtos = sysUserService.getAllUsers();

			if (sysUserdtos != null) {
				for (SysUserDto sysUserDto : sysUserdtos) {
					List entity = new ArrayList<>();
					
					entity.add(sysUserDto.getUserFirstName());
					entity.add(sysUserDto.getUserEmail());
					entity.add(sysUserDto.getUserName());
					entity.add(sysUserDto.getUserEmployeeNo());

					entity.add("<button type=\"button\" class=\"btn btn-default\" id=\"" + sysUserDto.getId()
							+ "\" onclick = \"editRole('" + sysUserDto.getId()
							+ "')\" ><i class=\"fa fa-edit\" aria-hidden=\"true\"></i></button>");
					
					
					
					entity.add("<button type=\"button\" class=\"btn btn-danger\"  id=\"" + sysUserDto.getId()
					+ "\" onclick = \"removeUser('" + sysUserDto.getId()
					+ "')\" ><i class=\"fa fa-trash\" aria-hidden=\"true\"></i>&nbsp;Delete</button>");
					
					
					entities.add(entity);
				}
	 }
			
			Map responseMap = new HashMap();
			responseMap.put("data", entities);
			return responseMap;
	 }
	 
	 @RequestMapping("usereditnav/{id}")
     public ModelAndView naveditRole (@PathVariable String id) throws Exception {
 	context.setAttribute("path", path);
 	
 	SysUserDto sysUserDto=sysUserService.findbyUser(id);
 	ModelAndView mav = new ModelAndView("pages/user/edit_user");
		
		mav.addObject("title", "USER | EDIT USER");
		mav.addObject("sbus", sysUserDto);
		mav.addObject("id", id);
		
		return mav;
		
	}
	 
	 @GetMapping(value ="/getSelectUser/{id}")
		public ResponseEntity<Object> getSelectedRole(@PathVariable String id ) throws Exception{
		
	    	SysUserDto user=sysUserService.findbyUser(id);
	 
	    	
	    	return new ResponseEntity<>(user, HttpStatus.OK);
		}
	 
	 @GetMapping(value="/removeusers/{id}")
	 public ResponseEntity<Object> removeUser(@PathVariable String id) throws Exception {
	    	 sysUserService.removedublicateSysUser(id); 
	    	 return new ResponseEntity<>("Work", HttpStatus.OK);
	    	 
		}
	 
	 @RequestMapping("remove")
		public ModelAndView navRemove () throws Exception {
	    	context.setAttribute("path", path);
			
	    	
	    	ModelAndView mav = new ModelAndView("pages/user/remove");
			
			mav.addObject("title", "USER | ALL USERS");
			return mav;
	 }
	  
	 @RequestMapping(value="/edituser",method = RequestMethod.POST)
	 public String editUser(@RequestBody BranchAssignDto branchAssignDto) throws Exception {
		 
		 return sysUserService.edituser(branchAssignDto);
	 }
	 
	 @RequestMapping(value="/edituserRoles",method = RequestMethod.POST)
	 public String editUserRoles(@RequestBody UserAssignDto userAssignDto) throws Exception {
		 return sysUserService.editUserRole(userAssignDto);
	 }
}