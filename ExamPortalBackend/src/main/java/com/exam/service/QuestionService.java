package com.exam.service;

import java.util.Set;

import com.exam.entity.exam.Question;
import com.exam.entity.exam.Quiz;

public interface QuestionService {

	// addQuestions
	public Question addQuestions(Question question);

	// updateQuestion
	public Question updateQuestion(Question question);

	// getAllQuestion
	public Set<Question> getAllQustion();

	// getQuestionById
	public Question getQuestionById(int quesId);
	
	//getQuestionsOfQuiz
	public Set<Question> getQuestionsOfQuiz(Quiz quiz);

	// deleteQuesionById
	public void deleteQuesionById(int quesId);
	
	public Question get(int quesId);
}
