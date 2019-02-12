package com.arpico.groupit.usermanagement.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.arpico.groupit.usermanagement.dto.MenuDto;
import com.arpico.groupit.usermanagement.dto.SbuDto;
import com.arpico.groupit.usermanagement.dto.SubMenusDto;
import com.arpico.groupit.usermanagement.service.MenuService;
import com.arpico.groupit.usermanagement.service.SbuService;
import com.arpico.groupit.usermanagement.service.SubSbuService;

@RestController
@RequestMapping("menu")
@CrossOrigin
public class MenuController {

	@Autowired
	private MenuService menuService;
	
	@Value("${server.context-path}")
	private String path;
	
	@Autowired
	ServletContext context;
	
	@Autowired
    private SbuService sbuService;
	
	@RequestMapping(value="/addMunu",method = RequestMethod.POST)
	public String addMunu(@RequestBody MenuDto menuDto) throws Exception {
		return menuService.addMenu(menuDto);
	}
	
	@RequestMapping("/navaddmenu")
	public ModelAndView navAddRole () throws Exception {
    	context.setAttribute("path", path);
    	List<SbuDto> sbuDtos = sbuService.getAll();
		ModelAndView mav = new ModelAndView("pages/menus/add_menu");
		mav.addObject("title", "Menus | Add New Menus");
		mav.addObject("sbus", sbuDtos);
		return mav;
 }
	
	
	@RequestMapping(value="/savemenu",method = RequestMethod.POST)
	public String saveMunu(@RequestBody  SubMenusDto menuDto) throws Exception {
		return menuService.saveMenu(menuDto);
	}
	
	
}
