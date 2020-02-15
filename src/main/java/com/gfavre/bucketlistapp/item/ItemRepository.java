package com.gfavre.bucketlistapp.item;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepository extends MongoRepository<Item, String> {
	
	public Item findByLabel(String label);
	
	public List<Item> findByCategory(String category);
}
