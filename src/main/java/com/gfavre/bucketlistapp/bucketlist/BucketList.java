package com.gfavre.bucketlistapp.bucketlist;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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

}
