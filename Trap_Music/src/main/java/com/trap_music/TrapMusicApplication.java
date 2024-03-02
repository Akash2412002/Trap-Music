package com.trap_music;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication (exclude = {SecurityAutoConfiguration.class})
public class TrapMusicApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrapMusicApplication.class, args);
	}
}
