package com.gfavre.bucketlistapp.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gfavre.bucketlistapp.bucketlist.BucketListRepository;
import com.gfavre.bucketlistapp.category.CategoryRepository;
import com.gfavre.bucketlistapp.exceptions.ResourceNotFoundException;

/**
 * Item REST controller
 * 
 * @author guillaume
 *
 */
@CrossOrigin
@RestController
public class ItemRestController {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private BucketListRepository bucketListRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	/**
	 * Read all items of a bucketlist
	 * 
	 * @param bucketListId
	 * @return
	 */
	@GetMapping("/bucketlist/{bucketListId}/items")
	public List<Item> getItemsByBucketListId(@PathVariable Integer bucketListId) {
		
		return bucketListRepository.findById(bucketListId).map(bucketlist -> {
			return itemRepository.findByBucketListId(bucketListId);
		}).orElseThrow(() -> new ResourceNotFoundException("Bucketlist not found for id "+bucketListId));
	}
	
	/**
	 * Read all items of a category in a a bucketlist
	 * 
	 * @param bucketListId
	 * @return
	 */
	@GetMapping("/bucketlist/{bucketListId}/itemsByCategory")
	List<Item> getItemsByBucketListIdAndCategory(@PathVariable Integer bucketListId, @RequestParam(value = "categoryId") Integer categoryId) {
		return bucketListRepository.findById(bucketListId).map(bucketlist -> {
			return itemRepository.findByBucketListIdAndCategoryId(bucketListId, categoryId);
		}).orElseThrow(() -> new ResourceNotFoundException("Bucketlist not found for id "+bucketListId));
	}
	
	/**
	 * A new item in a bucketlist
	 * 
	 * @param bucketListId
	 * @param newItem
	 * @return
	 */
	@PostMapping("/bucketlist/{bucketListId}/items")
	Item addItem(@PathVariable Integer bucketListId, @RequestBody Item newItem) {
		
		Integer idCategory = newItem.getCategory().getId();
		if(!categoryRepository.existsById(idCategory)) {
			throw new ResourceNotFoundException("Category not found for id "+idCategory);
		}
		
		return bucketListRepository.findById(bucketListId).map(bucketlist -> {
			newItem.setBucketList(bucketlist);
			return itemRepository.save(newItem);
		}).orElseThrow(() -> new ResourceNotFoundException("Bucketlist not found for id "+bucketListId));
		
	}
	
	/**
	 * Modify an item of a bucketlist
	 * 
	 * @param bucketListId
	 * @param itemId
	 * @param updatedItem
	 * @return
	 */
	@PutMapping("/bucketlist/{bucketListId}/items/{itemId}")
	public Item updateItem(@PathVariable Integer bucketListId, @PathVariable Integer itemId, @RequestBody Item updatedItem) {
		
		if(!bucketListRepository.existsById(bucketListId)) {
			throw new ResourceNotFoundException("Bucketlist not found for id "+bucketListId);
		}
		
		Integer idCategory = updatedItem.getCategory().getId();
		if(!categoryRepository.existsById(idCategory)) {
			throw new ResourceNotFoundException("Category not found for id "+idCategory);
		}
		
		return itemRepository.findById(itemId).map(item -> {
			item.setLabel(updatedItem.getLabel());
			item.setCategory(updatedItem.getCategory());
			item.setStatus(updatedItem.getStatus());
			return itemRepository.save(item);
		}).orElseThrow(() -> new ResourceNotFoundException("Item not found for id "+itemId));
	}
	
	/**
	 * Delete an item of a bucketlist
	 * 
	 * @param bucketListId
	 * @param itemId
	 * @return
	 */
	@DeleteMapping("/bucketlist/{bucketListId}/items/{itemId}")
	public ResponseEntity<?> deleteItem(@PathVariable Integer bucketListId, @PathVariable Integer itemId) {
		
		if(!bucketListRepository.existsById(bucketListId)) {
			throw new ResourceNotFoundException("Bucketlist not found for id "+bucketListId);
		}
		
		return itemRepository.findById(itemId).map(item -> {
			itemRepository.deleteById(itemId);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Item not found for id "+itemId));
	}

}
