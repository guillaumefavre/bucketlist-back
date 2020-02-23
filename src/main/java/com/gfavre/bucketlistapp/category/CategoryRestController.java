package com.gfavre.bucketlistapp.category;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryRestController {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@PostMapping("/category")
    public Category createCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }
	
	@GetMapping("/category/{id}")
	public Optional<Category> getOneCategory(@PathVariable Integer id) {
		return categoryRepository.findById(id);
	}
	
	@GetMapping("/category")
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

}
