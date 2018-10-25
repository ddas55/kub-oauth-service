package com.dd.authentication.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

	private static final Logger logger = LoggerFactory.getLogger(OAuth2Config.class);
	
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Value("${authapp.services.secrets}")
	private String client_secret;
    
    

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    	String[] arr = client_secret.split("\\:");
    	logger.info("** OAuth2Config.configure.client:" + arr[0] + ",secret:" + arr[1]);
    	
        clients.inMemory()
                .withClient(arr[0])//brands-consumer
                .secret(arr[1])//secret4Producer
                .authorizedGrantTypes("refresh_token", "password", "authorization_code")
                .scopes("webclient", "mobileclient");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
      endpoints
        .authenticationManager(authenticationManager)
        .userDetailsService(userDetailsService);
    }
}
