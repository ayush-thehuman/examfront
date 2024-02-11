package com.exam.entity.exam;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "category")
public class Category 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cid;
	private String title;
	private String description;
	
	//category has many quiz
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	@JsonIgnore
	private Set<Quiz> quizes = new LinkedHashSet<>();

	public Category() {
		super();
	}

	public Category(String title, String description, Set<Quiz> quizes) {
		super();
		this.title = title;
		this.description = description;
		this.quizes = quizes;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Quiz> getQuizes() {
		return quizes;
	}

	public void setQuizes(Set<Quiz> quizes) {
		this.quizes = quizes;
	}

	@Override
	public String toString() {
		return "Category [cid=" + cid + ", title=" + title + ", description=" + description + ", quizes=" + quizes
				+ "]";
	}
	
	
	
}
