package org.jzandag.service;

import java.util.List;

import org.jzandag.dao.BugRepository;
import org.jzandag.model.Bug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface BusinessService {
	
	public List<Bug> getAll();
	
	public List<Bug> getBugsByUser(String username);
}
