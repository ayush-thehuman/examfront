package com.exam.Repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.entity.exam.Question;
import com.exam.entity.exam.Quiz;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

	
	Set<Question> findByQuiz(Quiz quiz);

}
