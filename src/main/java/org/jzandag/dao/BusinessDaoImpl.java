package org.jzandag.dao;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.jzandag.controller.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BusinessDaoImpl extends BaseDao{
	
	@Autowired
	public BusinessDaoImpl(EntityManagerFactory factory) {
		super(factory);
	}

}
