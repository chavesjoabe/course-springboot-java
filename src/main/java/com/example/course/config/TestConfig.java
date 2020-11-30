package com.example.course.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.course.entities.User;
import com.example.course.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		
		User user1 = new User(0, "Jose das Couves", "jose@gmail.com", "555 0123" , "1234");
		User user2 = new User(0, "Maria das Neves", "maria@gmail.com", "555 555" , "1234");
		
		userRepository.saveAll(Arrays.asList(user1, user2));
		
	}
	
	
}
