package com.example.wsconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

import java.util.Properties;

/**
 * Created by zhougb on 2016/8/19.
 */
//@Configuration
public class WebMvcConfig{


    public AbstractAnnotationConfigDispatcherServletInitializer myDispatcherServletInitializer (){
        return new AbstractAnnotationConfigDispatcherServletInitializer() {
            @Override
            protected String[] getServletMappings() {
                return new String[]{"/", "*.service"};
            }

            @Override
            protected Class<?>[] getRootConfigClasses() {
                return new Class<?>[0];
            }

            @Override
            protected Class<?>[] getServletConfigClasses() {
                return new Class<?>[0];
            }
        };
    }

    //@Bean
    public HandlerMapping hessianMapping(){
        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        Properties mappings = new Properties();
        mappings.setProperty("/person.service", "/hessianExportedPersonService");
        mapping.setMappings(mappings);
        return mapping;
    }
}
