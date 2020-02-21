package com.gfavre.bucketlistapp.bucketlist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BucketListRepository extends JpaRepository<BucketList, Integer> {
	
}
