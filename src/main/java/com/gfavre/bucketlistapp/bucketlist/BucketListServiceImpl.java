package com.gfavre.bucketlistapp.bucketlist;

import org.apache.commons.collections4.CollectionUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gfavre.bucketlistapp.item.Item;

@Service
public class BucketListServiceImpl implements BucketListService {
	
	@Autowired
	private BucketListRepository bucketListRepository;

	@Override
	public BucketList createBucketList(BucketList newBucketList) {
		
		if(CollectionUtils.isNotEmpty(newBucketList.getItemsList())) {
			for(Item item : newBucketList.getItemsList()) {
				item.setId(new ObjectId());
			}
		}
		
		return bucketListRepository.save(newBucketList);
	}

}
