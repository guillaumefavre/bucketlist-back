package com.gfavre.bucketlistapp.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gfavre.bucketlistapp.bucketlist.BucketListRepository;
import com.gfavre.bucketlistapp.exceptions.ResourceNotFoundException;

@RestController
public class ItemRestController {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private BucketListRepository bucketListRepository;
	
	@GetMapping("/")
	String test() {
		return "Greetings from Spring Boot!!!!";
	}
	
	@GetMapping("/bucketlist/{bucketListId}/items")
	List<Item> getItemsByBucketListId(@PathVariable Integer bucketListId) {
		
		return bucketListRepository.findById(bucketListId).map(bucketlist -> {
			return itemRepository.findByBucketListId(bucketListId);
		}).orElseThrow(() -> new ResourceNotFoundException("Bucketlist not found for id "+bucketListId));
	}
	
	@PostMapping("/bucketlist/{bucketListId}/items")
	Item addItem(@PathVariable Integer bucketListId, @RequestBody Item newItem) {
		
		return bucketListRepository.findById(bucketListId).map(bucketlist -> {
			newItem.setBucketList(bucketlist);
			return itemRepository.save(newItem);
		}).orElseThrow(() -> new ResourceNotFoundException("Bucketlist not found for id "+bucketListId));
		
	}
	
	@GetMapping("/searchByCategory")
	List<Item> itemsByCategory(@RequestParam(value = "category") String category) {
		return itemRepository.findByCategory(category);
	}
	

}
