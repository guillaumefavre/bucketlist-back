package com.gfavre.bucketlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BucketlistApplication implements CommandLineRunner {
	
	@Autowired
	private ItemRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(BucketlistApplication.class, args);
	}
	
	@Override
	  public void run(String... args) throws Exception {

	    repository.deleteAll();

	    // save a couple of items
	    repository.save(new Item("Sport", "Courir un marathon"));
	    repository.save(new Item("Voyage", "Voyager en Australie"));
	    repository.save(new Item("Développement personnel", "Parler anglais couramment"));
	    repository.save(new Item("Voyage", "Aller à New-York"));

	    // fetch all customers
	    System.out.println("Items found with findAll():");
	    System.out.println("-------------------------------");
	    for (Item item : repository.findAll()) {
	      System.out.println(item);
	    }
	    System.out.println();

	    // fetch an individual item
	    System.out.println("Item found with findByLabel('Courir un marathon'):");
	    System.out.println("--------------------------------");
	    System.out.println(repository.findByLabel("Courir un marathon"));
	    
	    System.out.println("Items found with findByCategory('Voyage'):");
	    System.out.println("--------------------------------");
	    for (Item item : repository.findByCategory("Voyage")) {
	      System.out.println(item);
	    }

	  }

}
