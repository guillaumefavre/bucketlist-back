package com.gfavre.bucketlistapp.bucketlist;

import java.util.List;

public interface BucketListService {
	
	public BucketList createBucketList(BucketList newBucketList);
	
	public List<BucketList> searchByCategory(String id, String category);

}
