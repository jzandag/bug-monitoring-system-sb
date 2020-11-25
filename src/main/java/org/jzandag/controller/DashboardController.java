package org.jzandag.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jzandag.dao.BugRepository;
import org.jzandag.model.Users;
import org.jzandag.security.MyUserDetails;
import org.jzandag.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DashboardController {
	
	@Autowired
	BusinessService businessService;
	@Autowired
	BugRepository bugRepo;
	
	@RequestMapping(method = RequestMethod.GET, value = "/login")
	public String login(@ModelAttribute("userCommand") Users user, ModelMap map) {
		return "login";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public void dashboard(HttpServletRequest request, HttpServletResponse response, ModelMap map) throws IOException {
		map.addAttribute("myBugs", bugRepo.findAll());
		Users user = (Users) request.getSession().getAttribute("userSessionObj");
		
		map.addAttribute("user", user);
		if(user.getRole().equals("ROLE_USER")) {
			//Bugs
			response.sendRedirect("/user/dashboard");
		}else if(user.getRole().equals("ROLE_ADMIN")) {
			response.sendRedirect("/admin/dashboard");
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/user/dashboard")
	public void dashboardUser(HttpServletRequest request, HttpServletResponse response, ModelMap map) throws IOException {
		map.addAttribute("myBugs", bugRepo.findAll());
		Users user = (Users) request.getSession().getAttribute("userSessionObj");
		
		System.out.println(user.getUsername());
		map.addAttribute("user", user);
		if(user.getRole().equals("ROLE_USER")) {
			//Bugs
			response.sendRedirect("/user/dashboard");
		}else if(user.getRole().equals("ROLE_ADMIN")) {
			response.sendRedirect("/admin/dashboard");
		}
	}
	
	
}
