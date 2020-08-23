package org.jzandag.controller;

import org.jzandag.dao.BugRepository;
import org.jzandag.model.Users;
import org.jzandag.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/secured/user")
public class DashboardController {
	
	@Autowired
	BusinessService businessService;
	@Autowired
	BugRepository bugRepo;
	
	@RequestMapping(method = RequestMethod.GET, value = "/k")
	public String homepage(ModelMap map) {
		System.out.println("depota?");
		map.addAttribute("myBugs", bugRepo.findAll());
		return "index";
	}
	@RequestMapping(method = RequestMethod.GET, value = "/login")
	public String login(@ModelAttribute("userCommand") Users user, ModelMap map) {
		System.out.println("depota?");
		return "login";
	}
}
