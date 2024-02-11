package com.exam.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.entity.exam.Category;
import com.exam.service.CategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	//addCategory
	@PostMapping("/")
	public ResponseEntity<Category> addCategory(@RequestBody Category category)
	{
		Category addCategory = categoryService.addCategory(category);
		return new ResponseEntity<Category>(addCategory, HttpStatus.CREATED);
	}
	
	//getCategory
	@GetMapping("/{cid}")
	public ResponseEntity<Category> getCategory(@PathVariable int cid)
	{
		Category categoryById = categoryService.getCategoryById(cid);
		return new ResponseEntity<Category>(categoryById, HttpStatus.OK);
	}
	
	//getAllCategory
	@GetMapping("/")
	public ResponseEntity<Set<Category>> getAllCategory()
	{
		Set<Category> categories = categoryService.getCategories();
		return new ResponseEntity<Set<Category>>(categories, HttpStatus.OK);
	}
	
	//deleteCategoryById
	@DeleteMapping("/{cid}")
	public void deleteCategoryById(@PathVariable int cid)
	{
		categoryService.deleteCategoryById(cid);
	}
	
	//updateCategory
	@PutMapping("/")
	public ResponseEntity<Category> updateCategory(@RequestBody Category category)
	{
		Category updateCategory = categoryService.updateCategory(category);
		return new ResponseEntity<Category>(updateCategory, HttpStatus.OK);
	}
}
