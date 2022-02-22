package com.example.security;

import javax.sql.DataSource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
//	  @Autowired
//	  DataSource dataSource;
	  
	  @Autowired
	  UserDetailsService userDetailsService;

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/resources/**");
//    }
//
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	http.csrf().disable();
    	
        http.authorizeRequests()
            .antMatchers("/welcome").permitAll()
            .antMatchers("/actuator/**").permitAll()
            .antMatchers("/welcomeadmin").hasAnyRole("ADMIN")
            .antMatchers("/welcomeuser").hasAnyRole("USER", "ADMIN")
            .and().formLogin();

       
    }

 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
//	   auth.inMemoryAuthentication().withUser("user").password("root")
//         .roles("USER").and().withUser("student").password("root")
//         .roles("ADMIN");
		
//		auth.jdbcAuthentication()
//		    .dataSource(dataSource)
//		    .withDefaultSchema()
//		    .withUser(
//		    		User.withUsername("admin")
//		    		.password("root")
//		    		.roles("ADMIN"));	
		
		
//		auth.jdbcAuthentication()
//	    .dataSource(dataSource);
		
//		auth.jdbcAuthentication()
//        .dataSource(dataSource)
//        .usersByUsernameQuery("select username,password,enabled from users where username = ?")
//        .authoritiesByUsernameQuery("select username,authority from authorities where username = ?");  
		
		
		auth.userDetailsService(userDetailsService);
		   
	    
	}
	

	
	@Bean
	public PasswordEncoder getPasswordEncoder()
	{
		return NoOpPasswordEncoder.getInstance();
	}
	
}
