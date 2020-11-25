package org.jzandag.dao;

import java.util.List;

import org.jzandag.model.Bug;

/*
 * Impl methods for transaction specific query models 
 * Author: Zidrex Andag
*/

public interface BusinessDao {
	
	//Refer to userRepo to get bugs by user, hehe
	public List<Bug> getBugsByUsername(String username);
}
