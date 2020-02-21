package com.gfavre.bucketlistapp.item;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gfavre.bucketlistapp.bucketlist.BucketList;

import lombok.Getter;
import lombok.Setter;

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
	private String category;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "bucketList_id", nullable = false)
	private BucketList bucketList;
	
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
