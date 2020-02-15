package com.gfavre.bucketlistapp.bucketlist;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.gfavre.bucketlistapp.item.Item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "BucketList")
public class BucketList {
	
	@Id
	private String id;
	
	private String title;
	
	private List<Item> itemsList;

}
