package com.gfavre.bucketlistapp.item;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gfavre.bucketlistapp.bucketlist.BucketList;
import com.gfavre.bucketlistapp.bucketlist.BucketListRepository;

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
		return itemRepository.findByBucketListId(bucketListId);
	}
	
	@PostMapping("/bucketlist/{bucketListId}/items")
	Item addItem(@PathVariable Integer bucketListId, @RequestBody Item newItem) {
		Optional<BucketList> bucketList = bucketListRepository.findById(bucketListId);
		
		if(bucketList.isPresent()) {
			newItem.setBucketList(bucketList.get());
			return itemRepository.save(newItem);
		} else {
			// TODO gérer exception
			return null;
		}
		
		
	}
	
	@GetMapping("/searchByCategory")
	List<Item> itemsByCategory(@RequestParam(value = "category") String category) {
		return itemRepository.findByCategory(category);
	}
	

}
