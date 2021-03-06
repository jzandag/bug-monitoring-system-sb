package org.jzandag.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.StreamSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jzandag.dao.BugRepository;
import org.jzandag.dao.ProjectRepository;
import org.jzandag.dao.UserRepository;
import org.jzandag.dto.BugDTO;
import org.jzandag.model.Bug;
import org.jzandag.model.Project;
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
	
	private static final String VIEW_BUG = "viewBugs";
	private static final String BUG_PROFILE = "bugsProfile";
	
	
	@GetMapping(value = "")
	public String getBugs(HttpServletRequest request, ModelMap model) {
		Users user = (Users) request.getSession().getAttribute("userSessionObj");
		
		model.addAttribute("myBugs", userDao.getBugsByUsername(user.getUsername()));
		
		return VIEW_BUG;
	}
	
	@GetMapping(value = "/edit/{id}")
	public String saveBug(HttpServletResponse response,  @PathVariable("id") Long id, ModelMap model) throws IOException {
		
		//get bug by userid (username);
		Optional<Bug> opBug = this.bugDao.findById(id);
		//if wala redirect to /403
		if(opBug.isPresent()) {
			Bug bug = opBug.get();
			model.addAttribute("bugCommand", bug);
			initModel(model);
		}else {
			model.addAttribute("error", "No bug with such id");
			response.sendError(403);
		}
		
		return BUG_PROFILE;
	}
	
	@GetMapping(value = "/save")
	public String saveBug(HttpServletRequest request,@ModelAttribute("bugsCommand") Bug bug, ModelMap model) {
		
		bugDao.save(bug);
		model.addAttribute("success", "Successful save!");
		return VIEW_BUG;
	}
	
	@PostMapping("")
	public void saveBug(@RequestBody BugDTO bugDto) {
		Bug bug = new Bug();
		if(bugDto.getId() != null) {
			bug = this.bugDao.findById(bugDto.getId()).get();
		}
		bug.setId(bugDto.getId());
		bug.setDescription(bugDto.getDescription());
		bug.setProject(this.projectDao.findById(bugDto.getProject()).get());
		bug.setAssignedTo(this.userDao.findById(bugDto.getAssignedTo()).get());
		bug.setReportedBy(this.userDao.findById(bugDto.getAssignedTo()).get());
		
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
	
	private void initModel(ModelMap model) {
		model.addAttribute("projectList", getProjectList());
		model.addAttribute("userList", getUserList());
	}
	
	public List<Project> getProjectList(){		
		List<Project> proj = (List<Project>) this.projectDao.findAll();
		return proj;
	}
	
	public List<Users> getUserList(){
		
		//Map<Long, String> map = new HashMap<>();
		//StreamSupport.stream(this.userDao.findAll().spliterator(),false).map(m -> map.put(m.getId(), m.getUsername()));
		List<Users> users = (List<Users>) this.userDao.findAll();
		return users;
	}
}