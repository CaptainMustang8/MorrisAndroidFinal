package com.example.morrisandroidfinal;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	/* MainActivity of the app
	 * Now declaring views to be inflated, etc.
	 */
	Random r = new Random();
	int quizLength = 10;
	ArrayList<Integer> questionIDs = new ArrayList<Integer>(); 
	ArrayList<String> optionText = new ArrayList<String>();
	ArrayList<QuizQuestion> mustangQuiz = new ArrayList<QuizQuestion>();
	QuizQuestion question;
	RelativeLayout background;
	TextView mustangTitle;
	ImageView mustang;
	TextView questionText;
	RadioButton r1, r2, r3, r4;
	Button nextQ;
	int playerScore = 0;
	int qqNum = 0;
	int numQuestionsAsked = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// Inflating all the views in this method
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        QuestionDatabase qDB = new QuestionDatabase(this);
        background = (RelativeLayout)findViewById(R.id.trivia_page);
        mustangTitle = (TextView)findViewById(R.id.title);
        mustang = (ImageView)findViewById(R.id.pony_background);
        mustangQuiz = qDB.getQuizQuestions();
	    questionText = (TextView)findViewById(R.id.question);
	    r1 = (RadioButton)findViewById(R.id.option1);
	    r2 = (RadioButton)findViewById(R.id.option2);
	    r3 = (RadioButton)findViewById(R.id.option3);
	    r4 = (RadioButton)findViewById(R.id.option4);
	    nextQ = (Button)findViewById(R.id.next_question);
	    setQuestionView();
        // setting new click listener for button 
        nextQ.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				RadioGroup options = (RadioGroup)findViewById(R.id.options);
				RadioButton playerAns = (RadioButton)findViewById(options.getCheckedRadioButtonId());
				//checks for the correct answer
				if(question.getAnswer() == playerAns.getText()) {
					playerScore++;
				}
				//checks to see that quiz is complete
				if(numQuestionsAsked < 10){
					question = mustangQuiz.get(qqNum);
					optionText.clear();
					setQuestionView();
				}
				//change activities to the score page
				else {
					Intent scoreInt = new Intent(MainActivity.this, ScoreActivity.class);
					Bundle data = new Bundle();
					data.putInt("score", playerScore);
					scoreInt.putExtras(data);
					startActivity(scoreInt);
					finish();
				}
			}
		});
    }
    
    private void setQuestionView() {
    	/* This method randomizes the quiz questions and options.
    	 * It sets the value of qqNum to a random number between 1 and 10,
    	 * then checks to see if qqNum matches a value within questionIDs.
    	 * If it does, the method recurs.  If not, the method adds the value 
    	 * of qqNum to questionIDs, grabs the QuizQuestion value from mustangQuiz,
    	 * and sets the question view accordingly.
    	 */
        qqNum=r.nextInt(quizLength);
        if(!questionIDs.contains(qqNum)) {
        	questionIDs.add(qqNum);
        	question = mustangQuiz.get(qqNum);
        	questionText.setText(question.getQuestion());
        
    	// This is a good way to shuffle answers so the player 
    	// doesn't memorize them.
        	optionText.add(question.getFirstOpt());
	        optionText.add(question.getSecondOpt());
	        optionText.add(question.getThirdOpt());
	        optionText.add(question.getAnswer());
	       
	        Collections.shuffle(optionText);
	    	
	        r1.setText(optionText.get(0));
	    	r2.setText(optionText.get(1));
	    	r3.setText(optionText.get(2));
	    	r4.setText(optionText.get(3));
	    	numQuestionsAsked++;
        }
        else {
        	//recursion
        	setQuestionView();
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
