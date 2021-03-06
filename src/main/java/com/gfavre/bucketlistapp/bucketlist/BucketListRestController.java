package com.gfavre.bucketlistapp.bucketlist;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Bucketlist REST controller
 * 
 * @author guillaume
 *
 */
@RestController
public class BucketListRestController {
	
	@Autowired
	private BucketListRepository bucketListRepository;
	
	/**
	 * Création d'une nouvelle bucketlist
	 * 
	 * @param bucketList
	 * @return
	 */
	@PostMapping("/bucketlist")
    public BucketList createBucketList(@RequestBody BucketList bucketList) {
        return bucketListRepository.save(bucketList);
    }
	
	/**
	 * Récupération d'une bucketlist
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/bucketlist/{id}")
	public Optional<BucketList> one(@PathVariable Integer id) {
		return bucketListRepository.findById(id);
	}
}
