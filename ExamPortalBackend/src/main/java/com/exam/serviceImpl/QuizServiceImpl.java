package com.exam.serviceImpl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.Repository.QuizRepository;
import com.exam.entity.exam.Category;
import com.exam.entity.exam.Quiz;
import com.exam.service.QuizService;

@Service
public class QuizServiceImpl implements QuizService {

	@Autowired
	private QuizRepository quizRepo;
	
	@Override
	public Quiz addQuiz(Quiz quiz) {
		
		return this.quizRepo.save(quiz);
	}

	@Override
	public Quiz updateQuiz(Quiz quiz) {
		
		return this.quizRepo.save(quiz);
	}

	@Override
	public Set<Quiz> getAllQuiz() {

		return new LinkedHashSet<Quiz>(this.quizRepo.findAll());
	}

	@Override
	public Quiz getQuizById(int quizId) {

		return this.quizRepo.findById(quizId).get();
	}

	@Override
	public void deleteQuizById(int quizId) {
		
		this.quizRepo.deleteById(quizId);
	}

	@Override
	public List<Quiz> getQuizzesOfCategory(Category category) {
		
		List<Quiz> findByCategory = this.quizRepo.findByCategory(category);
		
		return findByCategory;
	}

	@Override
	public List<Quiz> getActiveQuizzes() {
		List<Quiz> activeQuiz = this.quizRepo.findByQuizActive(true);
		return activeQuiz;
	}

	@Override
	public List<Quiz> getActiveQuizByCategory(Category c) {
		List<Quiz> categoryQuizActive = this.quizRepo.findByCategoryAndQuizActive(c, true);
		return categoryQuizActive;
	}
	

}
