package com.example.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@ComponentScan
@RestController
@RefreshScope
public class SchoolApiController {
	
	@Value("${message.admin}")String adminMsg;
	@Value("${message.user}")String userMsg;
	
	@GetMapping("/welcome")
	public String Welcome()
	{
		return "<h1>Welcome to School</h1>";
	}
	
	@GetMapping("/welcomeadmin")
	public String Welcomeadmin()
	{
		return "<h1>Welcome Admin to School</h1>"+adminMsg;
	}
	
	@GetMapping("/welcomeuser")
	public String Welcomeuser()
	{
		return "<h1>Welcome User to School</h1>"+userMsg;
	}

}
