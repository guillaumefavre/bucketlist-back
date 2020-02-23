package com.gfavre.bucketlistapp.category;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gfavre.bucketlistapp.exceptions.ResourceNotFoundException;

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
	
	@PutMapping("/category/{id}")
	public Category updateCategory(@PathVariable Integer id, @RequestBody Category updatedCategory) {
		return categoryRepository.findById(id).map(category -> {
			category.setLabel(updatedCategory.getLabel());
			return categoryRepository.save(category);
		}).orElseThrow(() -> new ResourceNotFoundException("Category not found for id "+id));
	}
	
	@DeleteMapping("/category/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable Integer id) {
		return categoryRepository.findById(id).map(category -> {
			categoryRepository.delete(category);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Category not found for id "+id));
	}

}
