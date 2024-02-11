package com.exam.controller;

import java.util.List;
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
import com.exam.entity.exam.Quiz;
import com.exam.service.QuizService;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {

	@Autowired
	private QuizService quizService;
	
	//addQuiz
	@PostMapping("/")
	public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz)
	{
		Quiz addQuiz = quizService.addQuiz(quiz);
		return new ResponseEntity<Quiz>(addQuiz, HttpStatus.CREATED);
	}
	
	//updateQuiz
	@PutMapping("/")
	public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz)
	{
		Quiz updateQuiz = quizService.updateQuiz(quiz);
		return new ResponseEntity<Quiz>(updateQuiz, HttpStatus.OK);
	}
	
	//getAllQuiz
	@GetMapping("/")
	public ResponseEntity<Set<Quiz>> getAllQuiz()
	{
		Set<Quiz> allQuiz = quizService.getAllQuiz();
		return new ResponseEntity<Set<Quiz>>(allQuiz, HttpStatus.OK);
	}

	//getQuizById
	@GetMapping("/{quizid}")
	public ResponseEntity<Quiz> getQuizById(@PathVariable int quizid)
	{
		Quiz quizById = quizService.getQuizById(quizid);
		return new ResponseEntity<Quiz>(quizById, HttpStatus.OK);
	}
	
	//deleteQuizById
	@DeleteMapping("/{quizid}")
	public void deleteQuizById(@PathVariable int quizid)
	{
		this.quizService.deleteQuizById(quizid);
	}
	
	//getQuizzesOfCategories
	@GetMapping("/category/{cid}")
	public ResponseEntity<List<Quiz>> getQuizzesOfCategories(@PathVariable int cid)
	{
		Category category = new Category();
		category.setCid(cid);
		List<Quiz> quizzesOfCategory = this.quizService.getQuizzesOfCategory(category);
		return new ResponseEntity<List<Quiz>>(quizzesOfCategory,HttpStatus.OK);
	}
	
	//getActiveQuiz
	@GetMapping("/active")
	public ResponseEntity<List<Quiz>> getAllActiveQuiz()
	{
		List<Quiz> activeQuizzes = this.quizService.getActiveQuizzes();
		return new ResponseEntity<List<Quiz>>(activeQuizzes, HttpStatus.OK);
	}
	
	//getActiveQuizOfCategory
	@GetMapping("/category/active/{cid}")
	public ResponseEntity<List<Quiz>> getActiveQuizOfCategory(@PathVariable int cid)
	{
		Category category = new Category();
		category.setCid(cid);
		List<Quiz> activeQuizByCategory = this.quizService.getActiveQuizByCategory(category);
		return new ResponseEntity<List<Quiz>>(activeQuizByCategory, HttpStatus.OK);
	}
	
}
