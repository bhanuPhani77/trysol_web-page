package com.trysol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class TrysolWebApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(TrysolWebApplication.class, args);
	}

}
