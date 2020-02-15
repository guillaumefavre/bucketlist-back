package com.gfavre.bucketlistapp.item;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

//@Document(collection = "Item")
@Getter
@Setter
public class Item {
	
	@Id
	private ObjectId id;
	
	private String label;
	private String category;
	
	public Item() {
		
	}
	
	public Item(String category, String label) {
		this.category = category;
		this.label = label;
	}
	
	@Override
	  public String toString() {
	    return String.format(
	        "Item[id=%s, label='%s', category='%s']",
	        id, label, category);
	  }


}
