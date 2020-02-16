package com.gfavre.bucketlistapp.bucketlist;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.gfavre.bucketlistapp.item.Item;

@Service
public class BucketListServiceImpl implements BucketListService {
	
	@Autowired
	private BucketListRepository bucketListRepository;
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public BucketList createBucketList(BucketList newBucketList) {
		
		if(CollectionUtils.isNotEmpty(newBucketList.getItemsList())) {
			for(Item item : newBucketList.getItemsList()) {
				item.setId(new ObjectId());
			}
		}
		
		return bucketListRepository.save(newBucketList);
	}
	
	public List<BucketList> testSearchByCategory(String id, String category) {
		
		
		MatchOperation matchStage = Aggregation.match(new Criteria("itemsList.category").is("Voyage"));
		ProjectionOperation projectStage = Aggregation.project();

		Aggregation aggregation = Aggregation.newAggregation(matchStage, projectStage);
		
		AggregationResults<BucketList> output = mongoTemplate.aggregate(aggregation, "foobar", BucketList.class);
		
		return output.getMappedResults();
	}

}
