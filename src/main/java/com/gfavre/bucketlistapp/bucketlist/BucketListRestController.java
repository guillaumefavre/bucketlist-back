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
	
//	@PostMapping("/bucketlist")
//	BucketList newEmployee(@RequestBody BucketList newBucketList) {
//	    return bucketListService.createBucketList(newBucketList);
//	}
	
	@PostMapping("/bucketlist")
    public BucketList createBucketList(@RequestBody BucketList bucketList) {
        return bucketListRepository.save(bucketList);
    }
	
	@GetMapping("/bucketlist/{id}")
	public Optional<BucketList> one(@PathVariable Integer id) {
		return bucketListRepository.findById(id);
	}
	
//	@GetMapping("/bucketlist/{id}/searchByCategory")
//	List<BucketList> one(@PathVariable String id, @RequestParam(value = "category") String category) {
//		return bucketListService.searchByCategory(id, category);
//	}
}
