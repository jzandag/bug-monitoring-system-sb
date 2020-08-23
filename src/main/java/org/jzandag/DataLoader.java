package org.jzandag;

import org.jzandag.dao.BugRepository;
import org.jzandag.model.Bug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner{

	private final BugRepository bugRepo;
	
	@Autowired
	public DataLoader(BugRepository repository) {
		this.bugRepo = repository;
	}
	
	public void run(String... args) throws Exception {
		this.bugRepo.save(new Bug("issue"));
		this.bugRepo.save(new Bug("issue2"));
	}
 
}
