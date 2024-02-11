package com.exam.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.entity.exam.Category;
import com.exam.entity.exam.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {
	
	public List<Quiz> findByCategory(Category category);
	
	public List<Quiz> findByQuizActive(boolean b);
	
	public List<Quiz> findByCategoryAndQuizActive(Category c, boolean b);
}
