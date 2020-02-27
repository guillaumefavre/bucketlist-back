package com.gfavre.bucketlistapp.bucketlist;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gfavre.bucketlistapp.item.Item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "bucketlist")
public class BucketList {
	
	@Id
	@GeneratedValue(generator = "bucketlist_generator")
    @SequenceGenerator(
            name = "bucketlist_generator",
            sequenceName = "bucketlist_sequence",
            initialValue = 1,
            allocationSize=1
    )
	private Integer id;
	
	private String title;
	
	@OneToMany(mappedBy = "bucketList")
	private List<Item> items = new ArrayList<Item>();

}
