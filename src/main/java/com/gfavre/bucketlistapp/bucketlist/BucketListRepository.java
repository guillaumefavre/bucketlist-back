package com.gfavre.bucketlistapp.bucketlist;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface BucketListRepository extends MongoRepository<BucketList, String> {
	
	@Query(value = "{'itemsList.category' : {$eq : ?1} }", fields="{'itemsList.label' : 1}")
	public Optional<BucketList> findByCategory(String id, String category);

}
