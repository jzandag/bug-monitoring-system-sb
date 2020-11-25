package org.jzandag.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jzandag.dao.BugRepository;
import org.jzandag.model.Users;
import org.jzandag.security.MyUserDetails;
import org.jzandag.service.BusinessService;
import org.jzandag.utils.BMUtils;
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
	
	private static final String DASHBOARD = "dashboard";
	
	@RequestMapping(method = RequestMethod.GET, value = "/login")
	public String login(@ModelAttribute("userCommand") Users user, ModelMap map) {
		return "login";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public void dashboard(HttpServletRequest request, HttpServletResponse response, ModelMap map) throws IOException {
		Users user = BMUtils.getCurrentUserSession(request);
		
		if(BMUtils.isNull(user)) {
			response.sendRedirect("/login");
		}else {			
			if(user.getRole().equals("ROLE_USER")) {
				//Bugs
				response.sendRedirect("/user/dashboard");
			}else if(user.getRole().equals("ROLE_ADMIN")) {
				response.sendRedirect("/admin/dashboard");
			}
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/user/dashboard")
	public String dashboardUser(HttpServletRequest request, HttpServletResponse response, ModelMap map) throws IOException {
		map.addAttribute("myBugs", bugRepo.findAll());
		Users user = BMUtils.getCurrentUserSession(request);
		
		map.addAttribute("user", user);
		// I added if and else if stmt, in case we needed to add custom model attributes to each dashboard, mkay?
		if(user.getRole().equals("ROLE_USER"))
			return DASHBOARD;
		else if(user.getRole().equals("ROLE_ADMIN")){
			return DASHBOARD;
		}
		return  null;
	}
	
	
}
