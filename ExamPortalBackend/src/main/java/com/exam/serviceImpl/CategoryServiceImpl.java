package com.exam.serviceImpl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.Repository.CategoryRepository;
import com.exam.entity.exam.Category;
import com.exam.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepo;
	
	@Override
	public Category addCategory(Category category) {
		
		return this.categoryRepo.save(category);
	}

	@Override
	public Category updateCategory(Category category) {
		
		return this.categoryRepo.save(category);
	}

	@Override
	public Set<Category> getCategories() {

		return new LinkedHashSet<>(this.categoryRepo.findAll());
	}

	@Override
	public Category getCategoryById(int cid) {
		
		return this.categoryRepo.findById(cid).get();
	}

	@Override
	public void deleteCategoryById(int cid) {
		
		Category category = new Category();
		category.setCid(cid);
		this.categoryRepo.delete(category);

	}

}
