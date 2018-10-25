package com.dd;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableResourceServer
@EnableAuthorizationServer //Tell spring cloud that this service is going to act as OAuth2 service
public class CAuthServiceApplication {
	private static final Logger logger = LoggerFactory.getLogger(CAuthServiceApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(CAuthServiceApplication.class, args);
	}
	
	 @RestController
	 //@RequestMapping("/users")
	 @CrossOrigin
	 class LoginController {
			@RequestMapping(value= {"/user"},produces="application/json")
			public Map<String,Object> user(OAuth2Authentication user){
				logger.info("#@@@@# CAuthServiceApplication Calling #@@@@#");
				logger.info("#@@@@# CAuthServiceApplication Principal:" + user.getUserAuthentication().getPrincipal());
				logger.info("#@@@@# CAuthServiceApplication authorities:" + user.getUserAuthentication().getAuthorities());
				
				
				Map<String,Object> userinfo = new HashMap<>();
				userinfo.put("user", user.getUserAuthentication().getPrincipal());
				userinfo.put("authorities", AuthorityUtils.authorityListToSet(user.getUserAuthentication().getAuthorities()));
				return userinfo;
			}
	 }
}
