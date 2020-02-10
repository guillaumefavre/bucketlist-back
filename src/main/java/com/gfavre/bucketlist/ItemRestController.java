package com.gfavre.bucketlist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemRestController {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@GetMapping("/")
	String test() {
		return "Greetings from Spring Boot!!!!";
	}
	
	@GetMapping("/items")
	List<Item> allItems() {
		return itemRepository.findAll();
	}
	
	
	@GetMapping("/searchByLabel")
	Item itemByLabel(@RequestParam(value = "label") String label) {
		return itemRepository.findByLabel(label);
	}
	
	@GetMapping("/searchByCategory")
	List<Item> itemsByCategory(@RequestParam(value = "category") String category) {
		return itemRepository.findByCategory(category);
	}

}
