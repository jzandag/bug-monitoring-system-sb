package org.jzandag.controller;

import java.util.List;

import org.jzandag.dao.BugRepository;
import org.jzandag.model.Bug;
import org.jzandag.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/bugs")
public class BugsController {
	
	@Autowired
	BusinessService bugService;
	@Autowired
	BugRepository bugDao;
	
	@GetMapping(value = "")
	public List<Bug> getBugs() {
		return bugService.getAll();
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
}