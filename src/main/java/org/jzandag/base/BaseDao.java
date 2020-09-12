package org.jzandag.base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class BaseDao extends HibernateDaoSupport {
	
	@Autowired
	public BaseDao(EntityManagerFactory factory) {
		super.setSessionFactory(factory.unwrap(SessionFactory.class));
	}
	
	public Session getSession() {
		return getSessionFactory().openSession();
	}
	
	public <L> L get(Class<L> entityClass, Serializable id) {
		return (L) getHibernateTemplate().get(entityClass, id);
	}
	
	public Object save(Object entityClass) {
		return getHibernateTemplate().merge(entityClass);
	}
	
	public void delete(Class<?> entityClass, Serializable id) {
		Object entityObject = get(entityClass, id);
		delete(entityObject);
	}
	
	public void delete(Object entityObject) {
		getHibernateTemplate().delete(entityObject);
	}
	
	public List<?> getAll(Class<?> entityClass) {
		return getHibernateTemplate().loadAll(entityClass);
	}
	
	public List<?> getAllByHashMap(Class<?> entityClass, HashMap<String, Object> map) {
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);

		Set<String> keys = map.keySet();
		Iterator<String> iter = keys.iterator();

		Object date_from = null;
		Object date_to = null;
		String date_param = "";
		if (map.size() > 0) {
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if ((map.get(key) instanceof Object[])) {
					criteria.add(Expression.in(key, (Object[]) map.get(key)));
				} else if (key.contains("dateFrom%")) {
					date_from = map.get(key);
				} else if (key.equalsIgnoreCase("orderBy")) {
					criteria.addOrder(Order.asc(map.get(key).toString()));
				} else if (key.equalsIgnoreCase("orderByDesc")) {
					criteria.addOrder(Order.desc(map.get(key).toString()));
				} else if (key.contains("dateTo%")) {
					date_to = map.get(key);
					int index = key.indexOf("%");
					date_param = key.substring(index + 1);
				} else if (key.contains("description")) {
					criteria.add(Expression.isNotNull("description"));
				} else if (key.contains(">")) {
					criteria.add(Expression.gt(key.replace(">", ""), map.get(key)));
				} else if (key.contains("join")) {
					criteria.createAlias(map.get(key).toString(), "o");
				} else if (key.contains("!n")) {
					criteria.add(Restrictions.isNotNull(map.get(key).toString()));
				} else {
					criteria.add(Expression.eq(key, map.get(key)));
				}
			}
			if ((date_from != null) && (date_to != null)) {
				criteria.add(Expression.between(date_param, date_from, date_to));
			}
		}
		return getHibernateTemplate().findByCriteria(criteria);
	}
	
	
}
