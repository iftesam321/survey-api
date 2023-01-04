package com.iftesam.restapi.survey;

import java.util.List;

public class Survey {
	private String id;
	private String description;
	private List<Question> questions;
	private String title;
	public Survey(String id, String description,String title, List<Question> questions ) {
		super();
		this.id = id;
		this.description = description;
		this.questions = questions;
		this.title = title;
	}
	public Survey() {
	
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public String getDescription() {
		return description;
	}
	public List<Question> getQuestions() {
		return questions;
	}
	public String getTitle() {
		return title;
	}
	@Override
	public String toString() {
		return "Survey [id=" + id + ", description=" + description + ", questions=" + questions + ", title=" + title
				+ "]";
	}
	
	
}
