package com.project.configMain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
/**
 * Test Spring Boot web project
 * @author Oleg Liakhovets
 */
class MainConfigTest {

    ApplicationContext context = SpringApplication.run(PhoneBookApplication.class);

    @Test
    void viewResolver() {
        ViewResolver resolver = context.getBean(InternalResourceViewResolver.class);
    }
}