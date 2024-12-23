package com.example.coursework;

import org.springframework.context.annotation.Configuration; // аннотация для объедин. методов в 1 прилож.
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Конфигурационный класс для настройки MVC в приложении.
 */
@Configuration
public class
MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    }
}