package com.example.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.course.entities.Category;
import com.example.course.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
	
	@Autowired
	private CategoryService userService;
	
	@GetMapping
	public ResponseEntity<List<Category>> findAll(){
		
		List<Category> list = userService.findAll();
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}") //essa notação diz que a requisiçao vai aceitar um ID dentro da URL
	public ResponseEntity<Category> findById(@PathVariable Long id) //PathVariable me diz que o ID virá de uma variavel da URL
	{
		
		Category obj = userService.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}
}
