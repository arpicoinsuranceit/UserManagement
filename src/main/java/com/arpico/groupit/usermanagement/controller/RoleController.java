package com.arpico.groupit.usermanagement.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.arpico.groupit.usermanagement.dto.MenuDto;
import com.arpico.groupit.usermanagement.dto.RoleDto;
import com.arpico.groupit.usermanagement.dto.SbuDto;
import com.arpico.groupit.usermanagement.service.MenuService;
import com.arpico.groupit.usermanagement.service.RoleService;
import com.arpico.groupit.usermanagement.service.SbuService;

@Controller
@PropertySource("classpath:application.properties")
@RequestMapping("role")
public class RoleController {
	
	@Value("${server.context-path}")
	private String path;
    
    @Autowired
	ServletContext context;
    
    @Autowired
    private SbuService sbuService;
    
    @Autowired
   private MenuService menuService;
    
    @Autowired
    private RoleService roleService;
    
    

    @RequestMapping("addrole")
	public ModelAndView navAddRole () throws Exception {
    	context.setAttribute("path", path);
		
    	List<SbuDto> sbuDtos = sbuService.getAll();
    	
    	ModelAndView mav = new ModelAndView("pages/role/role_add");
		
		mav.addObject("title", "ROLE | ADD ROLE");
		mav.addObject("sbus", sbuDtos);
		
		System.out.println(sbuDtos.size());
		
		return mav;
	}
    
    @RequestMapping("allroles")
	public ModelAndView navAllRole () throws Exception {
    	context.setAttribute("path", path);
		 
    	List<SbuDto> sbuDtos = sbuService.getAll();
    	
    	ModelAndView mav = new ModelAndView("pages/role/all_role");
		
		mav.addObject("title", "ROLE | ALL ROLE");
		mav.addObject("sbus", sbuDtos);
		
		System.out.println(sbuDtos.size());
		
		return mav;
	}
    
    @GetMapping(value ="/loadMenu/{type}/{id}")
	public ResponseEntity<Object> getSubSbus(@PathVariable String type, @PathVariable String id ) throws Exception{
		System.out.println("loadmenu");
    	
    	List<MenuDto> list =  menuService.getMenu(type, id);
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
    
    @PostMapping(value = "addrole")
    public ResponseEntity<Object> addRole(@RequestBody RoleDto roleDto) throws Exception{
		
    	System.out.println(roleDto.toString());
    	roleService.save(roleDto);
    	
		return new ResponseEntity<>("Work",HttpStatus.OK);
	}
    
    @RequestMapping("/all_role_dt")
	@ResponseBody
	public Map allErrorDetails() throws Exception {

		List entities = new ArrayList();

		List<RoleDto> roleDtos = roleService.getAll();

		if (roleDtos != null) {
			for (RoleDto roleDto : roleDtos) {
				List entity = new ArrayList<>();
				
				entity.add(roleDto.getSbu());
				entity.add(roleDto.getName());
				entity.add(roleDto.getDescription());

				entity.add("<button type=\"button\" class=\"btn btn-default\" id=\"" + roleDto.getId()
						+ "\" onclick = \"editError('" + roleDto.getId()
						+ "')\" ><i class=\"fa fa-edit\" aria-hidden=\"true\"></i></button>");
				

				entities.add(entity);
			}
		}

		Map responseMap = new HashMap();
		responseMap.put("data", entities);
		return responseMap;
	}
    
    @GetMapping("/get_role/{val}")
   	@ResponseBody
   	public ResponseEntity<Object> getRoles(@PathVariable String val) throws Exception {

   		List<RoleDto> roleDtos = roleService.getAll(val);

   		return new ResponseEntity<>(roleDtos, HttpStatus.OK);
   	}
	
}
