package com.exam.entity.exam;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "quiz")
public class Quiz 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int quizId;
	private String quizTitle;
	private String quizDescription;
	private String maxMarks;
	private String noOfQuestions;
	private boolean quizActive = false;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Category category;

	
	//quiz has many questions
	@OneToMany(mappedBy = "quiz",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Question> questions = new HashSet<>();

	public Quiz() {
		super();
	}

	public Quiz(String quizTitle, String quizDescription, String maxMarks, String noOfQuestions, boolean quizActive,
			Category category, Set<Question> questions) {
		super();
		this.quizTitle = quizTitle;
		this.quizDescription = quizDescription;
		this.maxMarks = maxMarks;
		this.noOfQuestions = noOfQuestions;
		this.quizActive = quizActive;
		this.category = category;
		this.questions = questions;
	}

	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}

	public String getQuizTitle() {
		return quizTitle;
	}

	public void setQuizTitle(String quizTitle) {
		this.quizTitle = quizTitle;
	}

	public String getQuizDescription() {
		return quizDescription;
	}

	public void setQuizDescription(String quizDescription) {
		this.quizDescription = quizDescription;
	}

	public String getMaxMarks() {
		return maxMarks;
	}

	public void setMaxMarks(String maxMarks) {
		this.maxMarks = maxMarks;
	}

	public String getNoOfQuestions() {
		return noOfQuestions;
	}

	public void setNoOfQuestions(String noOfQuestions) {
		this.noOfQuestions = noOfQuestions;
	}

	public boolean isQuizActive() {
		return quizActive;
	}

	public void setQuizActive(boolean quizActive) {
		this.quizActive = quizActive;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	@Override
	public String toString() {
		return "Quiz [quizId=" + quizId + ", quizTitle=" + quizTitle + ", quizDescription=" + quizDescription
				+ ", maxMarks=" + maxMarks + ", noOfQuestions=" + noOfQuestions + ", quizActive=" + quizActive
				+ ", category=" + category + ", questions=" + questions + "]";
	}

	

	
}
