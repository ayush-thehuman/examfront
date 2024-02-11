package com.exam.service;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;

import com.exam.entity.exam.Category;
import com.exam.entity.exam.Quiz;

public interface QuizService {

	// addQuiz
	public Quiz addQuiz(Quiz quiz);

	// updateQuiz
	public Quiz updateQuiz(Quiz quiz);

	// getAllQuiz
	public Set<Quiz> getAllQuiz();

	// getQuizById
	public Quiz getQuizById(int quizId);

	// deleteQuizById
	public void deleteQuizById(int quizId);

	//get Quiz by category
	public List<Quiz> getQuizzesOfCategory(Category category);
	
	//getAllActiveQuiz
	public List<Quiz> getActiveQuizzes();
	
	//getActiveQuizByCategory
	public List<Quiz> getActiveQuizByCategory(Category c);
}
