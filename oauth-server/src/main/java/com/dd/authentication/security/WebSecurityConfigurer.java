package com.dd.authentication.security;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;


@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    @Bean
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        	
    	auth.inMemoryAuthentication()
        .withUser("ddas").password("password1").roles("USER")
        .and()
        .withUser("admin").password("password2").roles("USER","ADMIN");
    	/*
    	List<User> users = getUsers();
    	User ddas = users.get(0);
    	User admin = users.get(1);
        auth.inMemoryAuthentication()
            .withUser(ddas.getUser()).password(ddas.getPassword()).roles((String[]) ddas.getRoles().toArray())
            .and()
            .withUser(admin.getUser()).password(admin.getPassword()).roles((String[]) admin.getRoles().toArray());
        */
    }
    
    
    private List<User> getUsers(){
    	List<User> users = new LinkedList<>();
    	User ddas = new User();
    	String[] ddasroles = {"USER"};
    	ddas.setUser("ddas");
    	ddas.setPassword("password1");
    	ddas.setRoles(Arrays.asList(ddasroles));
    	users.add(ddas);
    	//
    	User admin = new User();
    	String[] adminroles = {"USER","ADMIN"};
    	admin.setUser("admin");
    	admin.setPassword("password2");
    	admin.setRoles(Arrays.asList(adminroles));
    	users.add(admin);
    	return users;
    	
    }
}
