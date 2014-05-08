package com.example.morrisandroidfinal;

import android.content.Context;


public class QuestionDatabase {
	
	public QuestionDatabase (Context context) {
		
	}
	
	
	private void addTrivia() {
		//Adding questions to the database
		//
		QuizQuestion q1 = new QuizQuestion("Who is SMU’s only Heisman Trophy winner?", "Eric Dickerson", "Don Meredith", 
				"George W. Bush", "Doak Walker");
		QuizQuestion q2 = new QuizQuestion("In what year was the Mustang Band founded?", "1915", "1941",
				"1923", "1917");
		QuizQuestion q3 = new QuizQuestion("Which of the following has never been a home football stadium for SMU?",
				"Cotton Bowl", "Ownby Stadium", "Texas Stadium", "AT&T Stadium");
		QuizQuestion q4 = new QuizQuestion("How many national championships does SMU football claim?",
				"0", "6", "1", "3");
		QuizQuestion q5 = new QuizQuestion("What is the name of the annual SMU/TCU football game?",
				"The Red River Showdown", "The Lone Star Showdown", "The Iron Bowl", "The Battle for the Iron Skillet");
		QuizQuestion q6 = new QuizQuestion("What is the name of SMU's mascot?", "Bevo", "Reveille", "Shasta", "Peruna");
		QuizQuestion q7 = new QuizQuestion("What are SMU's official colors?", "Red and Blue", "Red, White and Blue",
				"Purple and White", "Harvard Crimson and Yale Blue");
		QuizQuestion q8 = new QuizQuestion("SMU is the only university to receive the -", "award for Most Water on Campus (presented by Ozarka)",
				"Congressional Medal of Honor", "Nobel Peace Prize", "Death Penalty");
		QuizQuestion q9 = new QuizQuestion("The gathering of students and alumni before an SMU football game is called -",
				"Tailgating", "Pregaming", "a miracle", "Boulevarding");
		QuizQuestion q10 = new QuizQuestion("In the last four years, the Mustang Band has been to all of the following places except -",
				"Honolulu", "London", "New York City", "Albuquerque");
	}

}
