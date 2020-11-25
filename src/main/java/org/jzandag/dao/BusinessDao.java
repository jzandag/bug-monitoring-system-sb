package org.jzandag.dao;

import java.util.List;

import org.jzandag.model.Bug;

/*
 * Impl methods for transaction specific query models 
 * Author: Zidrex Andag
*/

public interface BusinessDao {
	public List<Bug> getBugsByUsername(String username);
}
