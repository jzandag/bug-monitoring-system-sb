package org.jzandag.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

import javax.servlet.http.HttpServletRequest;

import org.jzandag.dao.BugRepository;
import org.jzandag.dao.ProjectRepository;
import org.jzandag.dao.UserRepository;
import org.jzandag.model.Bug;
import org.jzandag.model.Users;
import org.jzandag.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping(value = "/user/bugs")
public class BugsController {
	
	@Autowired
	BusinessService bugService;
	@Autowired
	BugRepository bugDao;
	@Autowired
	UserRepository userDao;
	@Autowired
	ProjectRepository projectDao;
	
	@GetMapping(value = "")
	public String getBugs(HttpServletRequest request, ModelMap model) {
		Users user = (Users) request.getSession().getAttribute("userSessionObj");
		
		model.addAttribute("myBugs", bugService.getBugsByUser(user.getUsername()));
		
		return "viewBugs";
	}
	
	@GetMapping(value = "/edit/{id}")
	public String saveBug(@PathVariable("id") Long id, ModelMap model) {
		
		//get bug by userid (username);
		//if wala redirect to /403
		model.addAttribute("errpr", "No bug with such id");
		
		return "viewBugs";
	}
	
	@GetMapping(value = "/save")
	public String saveBug(HttpServletRequest request,@ModelAttribute("bugsCommand") Bug bug, ModelMap model) {
		
		bugDao.save(bug);
		model.addAttribute("success", "Successful save!");
		return "viewBugs";
	}
	
	@PostMapping("")
	public void saveBug(@RequestBody Bug bug) {
		bugDao.save(bug);
	}
	
	@PutMapping("")
	public Bug updateBug(@RequestBody Bug bug) {
		return bugDao.save(bug);
	}
	
	@DeleteMapping("/{id}")
	public void deleteBug(@PathVariable Long id) {
		bugDao.deleteById(id);
	}
	
	@ModelAttribute("projectList")
	public Map<Long, String> getProjectList(){
		Map<Long, String> map = new HashMap<>();
		
		StreamSupport.stream(this.projectDao.findAll().spliterator(),false).map(m -> map.put(m.getId(), m.getProjectName()));
		
		return map;
	}
}