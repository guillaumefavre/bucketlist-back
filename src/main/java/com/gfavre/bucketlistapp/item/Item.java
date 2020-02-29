package com.gfavre.bucketlistapp.item;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gfavre.bucketlistapp.bucketlist.BucketList;
import com.gfavre.bucketlistapp.category.Category;

import lombok.Getter;
import lombok.Setter;

/**
 * Item object
 * 
 * @author guillaume
 *
 */
@Getter
@Setter
@Entity
@Table(name = "item")
public class Item {
	
	@Id
	@GeneratedValue(generator = "item_generator")
    @SequenceGenerator(
            name = "item_generator",
            sequenceName = "item_sequence",
            initialValue = 1,
            allocationSize=1
    )
	private Integer id;
	
	private String label;
	private ItemStatus status;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "bucketList_id", nullable = false)
	@JsonIgnore
	private BucketList bucketList;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;
	
	public Item() {
		
	}
	
	public Item(String label, Category category) {
		this.label = label;
		this.category = category;
	}
	
	@Override
	  public String toString() {
	    return String.format(
	        "Item[id=%s, label='%s']",
	        id, label);
	  }


}
