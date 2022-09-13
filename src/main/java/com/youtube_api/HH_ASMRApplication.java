package com.youtube_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages={"com/youtube_api/Controller"})
@SpringBootApplication
public class HH_ASMRApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(HH_ASMRApplication.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
