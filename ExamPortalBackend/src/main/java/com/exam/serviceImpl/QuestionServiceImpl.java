package com.exam.serviceImpl;

import java.util.HashSet;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.Repository.QuestionRepository;
import com.exam.entity.exam.Question;
import com.exam.entity.exam.Quiz;
import com.exam.service.QuestionService;
@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository quesRepo;
	
	@Override
	public Question addQuestions(Question question) {
		
		return this.quesRepo.save(question);
	}

	@Override
	public Question updateQuestion(Question question) {
		
		return this.quesRepo.save(question);
	}

	@Override
	public Set<Question> getAllQustion() {
		
		return new HashSet<>(this.quesRepo.findAll());
	}

	@Override
	public Question getQuestionById(int quesId) {
		
		return this.quesRepo.findById(quesId).get();
	}

	@Override
	public void deleteQuesionById(int quesId) {
		
		Question question = new Question();
		question.setQuesId(quesId);
		this.quesRepo.delete(question);

	}

	@Override
	public Set<Question> getQuestionsOfQuiz(Quiz quiz) {
		
		return this.quesRepo.findByQuiz(quiz);
	}

	@Override
	public Question get(int quesId) {
		
		return this.quesRepo.getOne(quesId);
	}



}
