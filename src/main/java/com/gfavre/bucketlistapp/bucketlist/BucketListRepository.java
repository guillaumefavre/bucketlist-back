package com.gfavre.bucketlistapp.bucketlist;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BucketListRepository extends MongoRepository<BucketList, String> {

}
