package com.example.morrisandroidfinal;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class QuestionDatabase extends SQLiteOpenHelper {
	//creating a database containing QuizQuestions
	private SQLiteDatabase qqDB;
	private static final int dbNum = 1;
	private static final String dbName = "MustangTrivia";
	private static final String tableName = "QuizQuestions";
	private static final String qqID = "id";
	private static final String qq = "QuizQuestion";
	private static final String opt1 = "FirstOption";
	private static final String opt2 = "SecondOption";
	private static final String opt3 = "ThirdOption";
	private static final String ans = "Answer";
	
	//default constructor for QuestionDatabase
	public QuestionDatabase (Context context) {
		super(context, dbName, null, dbNum);
	}
	
	@Override
	public void onCreate(SQLiteDatabase myDB){
		/* Create a database with a table called QuizQuestions, containing all QuizQuestions
		 * with possible answers
		 */
		qqDB = myDB;
		String sqlStatement = "CREATE TABLE IF NOT EXISTS " + tableName +
				"(" + qqID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + qq + 
				" TEXT, " + opt1 + " TEXT, " + opt2 + " TEXT, " + opt3 + " TEXT, " 
				+ ans + " TEXT)";
		//execute the previous SQL statement
		qqDB.execSQL(sqlStatement);
		createQuizQuestions();
		
	}
	
	public void onUpgrade(SQLiteDatabase newDB, int oldNum, int newNum) {
		// For upgrading to a more current database of QuizQuestions
		qqDB.execSQL("DROP TABLE IF EXISTS " + tableName);
		onCreate(qqDB);
	}
	
	private void createQuizQuestions() {
		/* Adding questions to the database
		 * This method allows us to create QuizQuestions en masse
		 * by creating new objects, then calling the createQuizQuestions method
		 * to add them to the database.
		 * In this case, the questions are hardcoded.
		 */
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
		
		// Calling createQuizQuestion method for each question now
		createQuizQuestion(q1);
		createQuizQuestion(q2);
		createQuizQuestion(q3);
		createQuizQuestion(q4);
		createQuizQuestion(q5);
		createQuizQuestion(q6);
		createQuizQuestion(q7);
		createQuizQuestion(q8);
		createQuizQuestion(q9);
		createQuizQuestion(q10);
		
		//closing the database
		//qqDB.close();
	}
	
	public void createQuizQuestion(QuizQuestion newQQ) {
		// Method for adding QuizQuestions to table of QuizQuestions
		ContentValues vals = new ContentValues();
		vals.put(qq, newQQ.getQuestion());
		vals.put(opt1, newQQ.getFirstOpt());
		vals.put(opt2, newQQ.getSecondOpt());
		vals.put(opt3, newQQ.getThirdOpt());
		vals.put(ans, newQQ.getAnswer());
		qqDB.insert(tableName, null, vals);	
	}
	
	public ArrayList<QuizQuestion> getQuizQuestions() {
		/* This method gets all quiz questions from the database
		 * and returns an ArrayList of QuizQuestion objects.  This can be 
		 * shuffled so that we have a different order of questions every
		 * time through the quiz.
		 */
		ArrayList<QuizQuestion> quiz = new ArrayList<QuizQuestion>();
		String getQQ = "Select * from " + tableName;
		qqDB = this.getReadableDatabase();
		Cursor c = qqDB.rawQuery(getQQ, null);
		// Goes through a loop and adds QuizQuestions to the ArrayList as 
		// long as there is a QuizQuestion object to add
		if(c.moveToFirst()) {
			do {
				QuizQuestion q = new QuizQuestion();
				q.setID(c.getInt(0));
				q.setQuestion(c.getString(1));
				q.setFirstOpt(c.getString(2));
				q.setSecondOpt(c.getString(3));
				q.setThirdOpt(c.getString(4));
				q.setAnswer(c.getString(5));
				quiz.add(q);
			} while (c.moveToNext());
		}
		return quiz;
	}
}
