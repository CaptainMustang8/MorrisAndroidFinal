package com.example.morrisandroidfinal;

public class QuizQuestion {
	/*	Creating private attributes for QuizQuestion class.
		Each question has 4 possible options for the user to select;
		one is the correct answer, the other three are, of course, not.
	 */
	private int qID;
	private String question;
	private String opt1;
	private String opt2;
	private String opt3;
	private String answer;
	
	//default constructor
	public QuizQuestion() {
		qID = 0;
		setQuestion("");
		setFirstOpt("");
		setSecondOpt("");
		setThirdOpt("");
		setAnswer("");
	}
	
	//main constructor
	public QuizQuestion(String newQuestion, String num1, String num2, String num3, String correct) {
		setQuestion(newQuestion);
		setFirstOpt(num1);
		setSecondOpt(num2);
		setThirdOpt(num3);
		setAnswer(correct);
	}
	
	
	//now a series of Accessor methods for the different attributes
	
	public int getID() {
		return qID;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public String getFirstOpt() {
		return opt1;
	}
	
	public String getSecondOpt() {
		return opt2;
	}
	
	public String getThirdOpt() {
		return opt3;
	}
	
	public String getAnswer() {
		return answer;
	}
	
	//a series of Mutator methods next
	
	public void setID(int id) {
		qID = id;
	}
	
	public void setQuestion(String newQuestion) {
		question = newQuestion;
	}
	
	public void setFirstOpt(String firstOpt) {
		opt1 = firstOpt;
	}
	
	public void setSecondOpt(String secondOpt) {
		opt2 = secondOpt;
	}
	
	public void setThirdOpt(String thirdOpt) {
		opt3 = thirdOpt;
	}
	
	public void setAnswer(String ans) {
		answer = ans;
	}

}
