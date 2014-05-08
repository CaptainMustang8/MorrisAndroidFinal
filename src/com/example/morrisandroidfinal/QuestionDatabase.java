package com.example.morrisandroidfinal;
import android.content.ContentValues;
import android.content.Context;
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
		String sqlStatement = "Create table if not exists" + tableName +
				"(" + qqID + "integer primary key autoincrement" + qq + 
				"text," + opt1 + "text," + opt2 + "text," + opt3 + "text," 
				+ ans + "text)";
		//execute the previous SQL statement
		qqDB.execSQL(sqlStatement);
		createQuizQuestions();
		
	}
	
	public void onUpgrade(SQLiteDatabase newDB, int oldNum, int newNum) {
		// For upgrading to a more current database of QuizQuestions
		qqDB.execSQL("Drop table if exists" + tableName);
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
	}
	
	// Method for adding QuizQuestions to table of QuizQuestions
	public void createQuizQuestion(QuizQuestion newQQ) {
		ContentValues vals = new ContentValues();
		vals.put(qq, newQQ.getQuestion());
		vals.put(opt1, newQQ.getFirstOpt());
		vals.put(opt2, newQQ.getSecondOpt());
		vals.put(opt3, newQQ.getThirdOpt());
		vals.put(ans, newQQ.getAnswer());
		qqDB.insert(qq, null, vals);	
	}

}
