package org.zs.audit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.zs.audit.model.Graph;

@SpringBootApplication
public class AuditdDashboard {
	
	public static void main (String[] args) {
		SpringApplication.run(AuditdDashboard.class, args);
	}
	
	@Bean
	public Graph createGraph() {
		return new Graph();
	}
}
