package org.jzandag.dao;

import java.util.List;

import org.jzandag.model.Bug;

public interface BusinessDao {
	public List<Bug> getBugsByUsername(String username);
}
