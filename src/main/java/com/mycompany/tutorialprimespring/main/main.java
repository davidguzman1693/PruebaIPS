/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tutorialprimespring.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 *
 * @author ADMIN
 */
@EnableWebMvc
@Configuration
@ComponentScan
public class main implements WebApplicationInitializer {
 
    @Override
    public void onStartup(ServletContext sc) throws ServletException {
        
     /*   AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        sc.addListener(new ContextLoaderListener(context));*/
    }
}
 
