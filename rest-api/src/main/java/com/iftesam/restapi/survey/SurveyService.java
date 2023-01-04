package com.iftesam.restapi.survey;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class SurveyService {
	private static List<Survey> surveys=new ArrayList<>();
	
static {
	Question question1 = new Question("Question1",
	        "Most Popular Cloud Platform Today", Arrays.asList(
	                "AWS", "Azure", "Google Cloud", "Oracle Cloud"), "AWS");
	Question question2 = new Question("Question2",
	        "Fastest Growing Cloud Platform", Arrays.asList(
	                "AWS", "Azure", "Google Cloud", "Oracle Cloud"), "Google Cloud");
	Question question3 = new Question("Question3",
	        "Most Popular DevOps Tool", Arrays.asList(
	                "Kubernetes", "Docker", "Terraform", "Azure DevOps"), "Kubernetes");

	List<Question> questions = new ArrayList<>(Arrays.asList(question1,
	        question2, question3));

	Survey survey = new Survey("Survey1", "My Favorite Survey",
	        "Description of the Survey", questions);

	surveys.add(survey);

	}

public List<Survey> retrieveAllSurveys() {
	// TODO Auto-generated method stub
	return surveys;
}

public Survey retrieveSurveyById(String surveyId) {
	
	Predicate<? super Survey> predicate=
			survey->survey.getId().equalsIgnoreCase(surveyId);
	
			Optional<Survey> optionalSurvey=
					surveys.stream().filter(predicate).findFirst();
			
			if(optionalSurvey.isEmpty()) return null;
			
			return optionalSurvey.get();
}

public List<Question> retrieveAllSurveyQuestions(String surveyId) {
	// TODO Auto-generated method stub
	Predicate<? super Survey> predicate=
			survey->survey.getId().equalsIgnoreCase(surveyId);
	
			Optional<Survey> optionalSurvey=
					surveys.stream().filter(predicate).findFirst();
			if(optionalSurvey.isEmpty()) return null;
			
			Survey survey= optionalSurvey.get();
			
			return survey.getQuestions();
			
}

public Question retrieveSpecificSurveyQuestion(String surveyId,String questionId) {
	// TODO Auto-generated method stub
	List<Question> questions=retrieveAllSurveyQuestions(surveyId);
	if(questions==null) return null;
	
					
			Optional<Question> optionalQuestion=
			questions.stream().filter(Question->Question.getId().equalsIgnoreCase(questionId)).findFirst();
			
			if(optionalQuestion.isEmpty()) return null;
			return optionalQuestion.get();
					
}

public String addNewSurveyQuestion(String surveyId, Question question) {
	// TODO Auto-generated method stub
	List<Question> questions=retrieveAllSurveyQuestions(surveyId);
	
	question.setId(generateRandomId());
	questions.add(question);
	return question.getId();
}

private String generateRandomId() {
	SecureRandom secureRandom=new SecureRandom();
	String randomId=new BigInteger(32,secureRandom).toString();
	return randomId;
}

public String deleteSurveyQuestion(String surveyId,String questionId) {
	// TODO Auto-generated method stub
	List<Question> surveyQuestions=retrieveAllSurveyQuestions(surveyId);
	if(surveyQuestions==null) return null;
	
					
			Predicate<? super Question> predicate = Question->Question.getId().equalsIgnoreCase(questionId);
			
			
			boolean removed=surveyQuestions.removeIf(predicate);
			
			if(!removed) return null;
			return questionId;
					
}

public void updateSurveyQuestion(String surveyId, String questionId, Question question) {
	// TODO Auto-generated method stub
	List<Question> questions=retrieveAllSurveyQuestions(surveyId);
	
	Predicate<? super Question> predicate = Question->Question.getId().equalsIgnoreCase(questionId);
	boolean removed=questions.removeIf(predicate);
	questions.add(question);
	
	}


}