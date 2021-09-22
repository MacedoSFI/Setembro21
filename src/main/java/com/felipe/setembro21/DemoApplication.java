package com.felipe.setembro21;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

/*
 * opcional caso precise: @EntityScan(basePackages="com.felipe.setembro21") @ComponentScan(basePackages={"com.felipe.setembro21.*"})
opcional caso precise @EnableJpaRepositories(basePackages="com.felipe.setembro21.repository")
 */