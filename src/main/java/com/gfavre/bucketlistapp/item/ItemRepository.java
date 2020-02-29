package com.gfavre.bucketlistapp.item;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
	
	public List<Item> findByBucketListId(Integer bucketListId);
	
	public List<Item> findByBucketListIdAndCategoryId(Integer bucketListId, Integer idCategory);
	
}
