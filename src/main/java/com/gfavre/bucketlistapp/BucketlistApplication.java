package com.gfavre.bucketlistapp;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.gfavre.bucketlistapp.bucketlist.BucketList;
import com.gfavre.bucketlistapp.item.Item;
import com.gfavre.bucketlistapp.item.ItemRepository;

@SpringBootApplication
public class BucketlistApplication {
	
	@Autowired
	private ItemRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(BucketlistApplication.class, args);
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
