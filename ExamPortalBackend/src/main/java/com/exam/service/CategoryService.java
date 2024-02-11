package com.exam.service;

import java.util.Set;

import com.exam.entity.exam.Category;

public interface CategoryService {

	// addcategory
	public Category addCategory(Category category);

	// updateCategory
	public Category updateCategory(Category category);

	// getCategories
	public Set<Category> getCategories();

	// getCategoryById
	public Category getCategoryById(int cid);
	
	// deleteCategoryById
	public void deleteCategoryById(int cid);
}
