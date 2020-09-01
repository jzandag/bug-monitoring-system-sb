package org.jzandag;

import org.jzandag.dao.BugRepository;
import org.jzandag.dao.UserRepository;
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
	
	private final UserRepository userRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public DataLoader(BugRepository repository, UserRepository userRepo) {
		this.userRepo = userRepo;
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
		
		bug.setDescription("first Issue");
		bug.setProject(proj);
		bug.setUser(user);
		
		this.bugRepo.save(bug);
	}
 
}
