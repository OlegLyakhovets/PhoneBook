package com.project.main;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.junit.jupiter.api.Assertions.*;

class MainConfigTest {

    ApplicationContext context = SpringApplication.run(PhoneBookApplication.class);

    @Test
    void viewResolver() {
        ViewResolver resolver = context.getBean(InternalResourceViewResolver.class);
    }
}