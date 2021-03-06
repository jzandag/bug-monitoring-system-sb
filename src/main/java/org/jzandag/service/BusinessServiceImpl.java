package org.jzandag.service;

import java.util.List;

import org.jzandag.dao.BugRepository;
import org.jzandag.dao.BusinessDao;
import org.jzandag.model.Bug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessServiceImpl implements BusinessService{
	
	@Autowired
	BugRepository bugRepo;
	
	@Autowired
	BusinessDao businessDao;
	
	public List<Bug> getAll() {
		return (List<Bug>) bugRepo.findAll();
	}
	
	@Override
	public List<Bug> getBugsByUser(String username) {
		// TODO Auto-generated method stub
		return businessDao.getBugsByUsername(username);
	}

}
