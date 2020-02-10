package com.gfavre.bucketlist;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

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
	
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}

		};
	}

}
