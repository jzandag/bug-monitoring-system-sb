package org.jzandag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class BugMonitoringSystem {
	
	public static void main(String[] args) {
		SpringApplication.run(BugMonitoringSystem.class, args);
	}
}
