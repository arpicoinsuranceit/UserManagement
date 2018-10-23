package com.arpico.groupit.usermanagement.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@PropertySource("classpath:application.properties")
public class HomeController {
	
	@Value("${server.context-path}")
	private String path;
    
    @Autowired
	ServletContext context;
	
	@RequestMapping("/")
	public ModelAndView navHome () {
		context.setAttribute("path", path);
		ModelAndView mav = new ModelAndView("home");
		
		mav.addObject("title", "USER | HOME");
		
		return mav;
	}

}
