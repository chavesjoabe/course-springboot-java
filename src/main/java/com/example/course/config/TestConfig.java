package com.example.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.course.entities.Category;
import com.example.course.entities.Order;
import com.example.course.entities.User;
import com.example.course.entities.enums.OrderStatus;
import com.example.course.repositories.CategoryRepository;
import com.example.course.repositories.OrderRepository;
import com.example.course.repositories.UserRepository;

@Configuration
@Profile("test") //essa classe serve para popular o banco de dados
public class TestConfig implements CommandLineRunner{
	
	@Autowired //a anotacao autowired faz a injecao de dependencia
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public void run(String... args) throws Exception {
		
		User user1 = new User(0, "Jose das Couves", "jose@gmail.com", "555 0123" , "1234");
		User user2 = new User(0, "Maria das Neves", "maria@gmail.com", "555 555" , "1234");
		User user3 = new User(0, "Vitor Green", "vitor@gmail.com", "3444 555" , "1234");
		User user4 = new User(0, "Ana Brown", "ana@gmail.com", "4444 555" , "1234");
		
		Order order1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"),OrderStatus.WAITING_PAYMENT, user1);
		Order order2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"),OrderStatus.PAID, user2);
		Order order3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"),OrderStatus.CANCELED, user4);
		
		Category category1 = new Category(null, "Electronics");
		Category category2 = new Category(null, "Books");
		Category category3 = new Category(null, "Computers");
		
		userRepository.saveAll(Arrays.asList(user1, user2, user3, user4)); //adciona os valores a uma lista
		orderRepository.saveAll(Arrays.asList(order1, order2, order3));
		categoryRepository.saveAll(Arrays.asList(category1, category2, category3));
		
	}
	
	
}
