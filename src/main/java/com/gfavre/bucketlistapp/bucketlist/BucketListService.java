package com.gfavre.bucketlistapp.bucketlist;

import java.util.List;

public interface BucketListService {
	
	public BucketList createBucketList(BucketList newBucketList);
	
	public List<BucketList> testSearchByCategory(String id, String category);

}
