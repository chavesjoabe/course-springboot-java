package com.example.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.course.entities.Category;
import com.example.course.entities.Order;
import com.example.course.entities.OrderItem;
import com.example.course.entities.Payment;
import com.example.course.entities.Product;
import com.example.course.entities.User;
import com.example.course.entities.enums.OrderStatus;
import com.example.course.repositories.CategoryRepository;
import com.example.course.repositories.OrderItemRepository;
import com.example.course.repositories.OrderRepository;
import com.example.course.repositories.ProductRepository;
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
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	public void run(String... args) throws Exception {
		
		User user1 = new User(0, "Jose das Couves", "jose@gmail.com", "555 0123" , "1234");
		User user2 = new User(0, "Maria das Neves", "maria@gmail.com", "555 555" , "1234");
		User user3 = new User(0, "Vitor Green", "vitor@gmail.com", "3444 555" , "1234");
		User user4 = new User(0, "Ana Brown", "ana@gmail.com", "4444 555" , "1234");
	
		userRepository.saveAll(Arrays.asList(user1, user2, user3, user4)); //adciona os valores a uma lista
		
		Order order1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"),OrderStatus.WAITING_PAYMENT, user1);
		Order order2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"),OrderStatus.PAID, user2);
		Order order3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"),OrderStatus.CANCELED, user4);
		
		orderRepository.saveAll(Arrays.asList(order1, order2, order3));

		Category category1 = new Category(null, "Electronics");
		Category category2 = new Category(null, "Books");
		Category category3 = new Category(null, "Computers");
		
		categoryRepository.saveAll(Arrays.asList(category1, category2, category3));
		
		Product product1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product product2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product product3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product product4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product product5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
		
		productRepository.saveAll(Arrays.asList(product1,product2,product3,product4,product5));
		
		product1.getCategories().add(category2);
		product2.getCategories().add(category1);
		product2.getCategories().add(category3);
		product3.getCategories().add(category3);
		product4.getCategories().add(category3);
		product5.getCategories().add(category2);
		
		productRepository.saveAll(Arrays.asList(product1,product2,product3,product4,product5));//adiciona os produtos com as categorias
		

		OrderItem orderItem1 = new OrderItem(order1, product1, 2, product1.getPrice());
		OrderItem orderItem2 = new OrderItem(order1, product3, 1, product3.getPrice());
		OrderItem orderItem3 = new OrderItem(order2, product3, 2, product3.getPrice());
		OrderItem orderItem4 = new OrderItem(order3, product5, 2, product5.getPrice());
		
		orderItemRepository.saveAll(Arrays.asList(orderItem1,orderItem2,orderItem3,orderItem4));
		
		Payment payment1 = new Payment(null, Instant.parse("2019-07-21T05:42:10Z"), order2);
		order2.setPayment(payment1);
		
		orderRepository.save(order2);
		
	}
	
	
}
