package org.jzandag;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DataConfiguration {
	
	@Autowired
	EntityManagerFactory factory;
	
	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager t = new JpaTransactionManager();
		t.setEntityManagerFactory(factory);
		return t;
	}
}	
