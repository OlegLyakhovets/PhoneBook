package com.project.configMain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Test Spring Boot web project
 * @author Oleg Liakhovets
 */

@SpringBootApplication
@ComponentScan(basePackages={"com.project"})
@EnableJpaRepositories("com.project")
@EntityScan("com.project")
public class PhoneBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhoneBookApplication.class, args);
	}

}
