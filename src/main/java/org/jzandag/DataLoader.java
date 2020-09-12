package org.jzandag;

import org.jzandag.dao.BugRepository;
import org.jzandag.model.Bug;
import org.jzandag.model.Project;
import org.jzandag.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner{

	private final BugRepository bugRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public DataLoader(BugRepository repository) {
		this.bugRepo = repository;
	}
	
	public void run(String... args) throws Exception {
		Bug bug = new Bug("issue");
		Project proj = new Project();
			proj.setProjectName("BMS");
			proj.setDescription("bms spring boot");
		
		Users user = new Users();
			user.setName("zid");
			user.setPassword(passwordEncoder.encode("zid"));
			user.setUsername("zid");
			user.setRole("ROLE_USER");
			
		Users user2 = new Users();
			user2.setName("dess");
			user2.setPassword(passwordEncoder.encode("dess"));
			user2.setUsername("dess");
			user2.setRole("ROLE_ADMIN");
		
		bug.setDescription("first Issue");
		bug.setProject(proj);
		bug.setAssignedTo(user);
		bug.setReportedBy(user2);
		
		this.bugRepo.save(bug);
	}
 
}
