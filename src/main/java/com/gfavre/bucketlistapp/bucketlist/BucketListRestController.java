package com.gfavre.bucketlistapp.bucketlist;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BucketListRestController {
	
	@Autowired
	private BucketListRepository bucketListRepository;

	@PostMapping("/bucketlist")
	BucketList newEmployee(@RequestBody BucketList newBucketList) {
	    return bucketListRepository.save(newBucketList);
	}
	
	@GetMapping("/bucketlist/{id}")
	Optional<BucketList> one(@PathVariable String id) {
		return bucketListRepository.findById(id);
	}
}
