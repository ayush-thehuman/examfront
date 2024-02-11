package com.exam.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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

import com.exam.entity.exam.Question;
import com.exam.entity.exam.Quiz;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private QuizService quizService;
	
	//addQuestion
	@PostMapping("/")
	public ResponseEntity<Question> addQuestion(@RequestBody Question question)
	{
		Question addQuestion = questionService.addQuestions(question);
		return new ResponseEntity<Question>(addQuestion, HttpStatus.CREATED);
	}
	
	//updateQuestion
	@PutMapping("/")
	public ResponseEntity<Question> updateQuestion(@RequestBody Question question)
	{
		Question updateQuestion = questionService.updateQuestion(question);
		return new ResponseEntity<Question>(updateQuestion, HttpStatus.OK);
	}
	
	//getAllQuestion
	//findQuestionsOfQuiz
		@GetMapping("/quiz/{quizId}")
		public ResponseEntity<?> findQuestionsOfQuiz(@PathVariable int quizId) {
			Quiz quizById = quizService.getQuizById(quizId);
			Set<Question> questions = quizById.getQuestions();
			List<Question> list = new ArrayList<>(questions);
			if (list.size() > Integer.parseInt(quizById.getNoOfQuestions())) {
				list = list.subList(0, Integer.parseInt(quizById.getNoOfQuestions() + 1));
			}
			list.forEach(e->{
				e.setQuesAnswer("");
			});
			Collections.shuffle(list);
			return new ResponseEntity<>(list, HttpStatus.OK);			
		}
		
		@GetMapping("/quiz/all/{quizId}")
		public ResponseEntity<?> getAllQuestionAdmin(@PathVariable int quizId)
		{
			Quiz quiz = new Quiz();
			quiz.setQuizId(quizId);
		
			Set<Question> questionsOfQuiz = questionService.getQuestionsOfQuiz(quiz);
			return new ResponseEntity<>(questionsOfQuiz, HttpStatus.OK);
		}
		
	//getQuestionById
	@GetMapping("/{quesId}")
	public ResponseEntity<Question> getQuestionById(@PathVariable int quesId)
	{
		Question questionById = questionService.getQuestionById(quesId);
		return new ResponseEntity<Question>(questionById, HttpStatus.OK);
	}
	
	//deleteQuestionById
	@DeleteMapping("/{quesId}")
	public void deleteQuestionById(@PathVariable int quesId)
	{
		this.questionService.deleteQuesionById(quesId);
	}
	
	
	//evaluate quiz
	@PostMapping("/eval-quiz")
	public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions)
	{
		System.out.println(questions);
		int marksGot = 0;
		int correctAnswers = 0;
		int attempted = 0;
		
		for (Question q : questions) {
			Question question = this.questionService.get(q.getQuesId());
			if(question.getQuesAnswer().equals(q.getGivenAnswer()))
			{
				//correct
				correctAnswers++;
				
				double marksSingle = Double.parseDouble(questions.get(0).getQuiz().getMaxMarks())/Double.valueOf(questions.size());
				marksGot += marksSingle;
			}
			if(q.getGivenAnswer() != null)
			{
				attempted++;
			}
		}
		
		Map<String, Object> map = Map.of("marksGot",marksGot,"correctAnswers",correctAnswers,"attempted",attempted);
		//return new ResponseEntity<>(map, HttpStatus.OK);
		return ResponseEntity.ok(map);
	}
}
