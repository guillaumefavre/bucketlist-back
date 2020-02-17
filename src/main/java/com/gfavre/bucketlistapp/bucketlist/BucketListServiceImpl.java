package com.gfavre.bucketlistapp.bucketlist;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationExpression;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.data.mongodb.core.aggregation.ComparisonOperators;
import org.springframework.data.mongodb.core.aggregation.Fields;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
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
	
	public List<BucketList> searchByCategory(String id, String category) {
		
		AggregationExpression filtre = ArrayOperators.Filter.filter("itemsList")
	             .as("item")
	             .by(ComparisonOperators.Eq.valueOf("item.category").equalToValue(category));
		
		ProjectionOperation projection1 = Aggregation.project("title").and(filtre).as("itemsList").andExclude("_id");
//		projection1 = projection1.and("itemsList").nested("label");
		
//		ProjectionOperation projection2 = Aggregation.project().and("itemsList").nested(Fields.fields("itemsList.label"));

		Aggregation aggregation = Aggregation.newAggregation(projection1);
		
		// TODO utiliser unwind

		
		AggregationResults<BucketList> output = mongoTemplate.aggregate(aggregation, BucketList.class, BucketList.class);
		
		return output.getMappedResults();
	}

}
