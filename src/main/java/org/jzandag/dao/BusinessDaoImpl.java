package org.jzandag.dao;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jzandag.base.BaseDao;
import org.jzandag.model.Bug;
import org.jzandag.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class BusinessDaoImpl extends BaseDao implements BusinessDao{
	
	// Implemented Impl here because of transactional management TODO
	// Reference in creating native methods
	
	@Autowired
	public BusinessDaoImpl(EntityManagerFactory factory) {
		super(factory);
	}

	@Override
	@Transactional
	public List<Bug> getBugsByUsername(String username) {
		StringBuilder hql = new StringBuilder();
		
		hql.append("from Bug b ");
		hql.append("inner join fetch b.assignedTo u ");
		hql.append("where u.username = :username");
		Query q = getSessionFactory().openSession().createQuery(hql.toString());
		q.setParameter("username", username);
		
		List<Bug> list = q.getResultList();
		return list;
	}

}
