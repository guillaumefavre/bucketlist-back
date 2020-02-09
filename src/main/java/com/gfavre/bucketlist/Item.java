package com.gfavre.bucketlist;

import org.springframework.data.annotation.Id;

public class Item {
	
	@Id
	public String id;
	
	public String label;
	public String category;
	
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
