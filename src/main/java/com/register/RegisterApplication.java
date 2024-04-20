package com.register;

import org.springframework.boot.SpringApplication;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@ComponentScan

public class RegisterApplication {

	public static void main(String[] args) {
	  SpringApplication.run(RegisterApplication.class, args);

	  
	} 

}
