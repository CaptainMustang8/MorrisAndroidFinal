package com.example.morrisandroidfinal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	/* MainActivity of the app
	 * Now declaring views to be inflated, etc.
	 */
	HashSet<Integer> completedQuestions = new HashSet<Integer>();
	Random r = new Random();
	int maxQQCount = 10;
	ArrayList<String> options = new ArrayList<String>();
	ArrayList<QuizQuestion> mustangQuiz;
	QuizQuestion question;
	RelativeLayout background;
	TextView mustangTitle;
	ImageView mustang;
	TextView questionText;
	RadioButton r1, r2, r3, r4;
	Button nextQ;
	int playerScore = 0;
	int qqNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// Inflating all the views in this method
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        QuestionDatabase qDB = new QuestionDatabase(this);
        mustangQuiz = qDB.getQuizQuestions();
        background = (RelativeLayout)findViewById(R.id.trivia_page);
        mustangTitle = (TextView)findViewById(R.id.title);
        mustang = (ImageView)findViewById(R.id.pony_background);
        
    	
        while(completedQuestions.size() < maxQQCount) {
        	qqNum = r.nextInt(maxQQCount);
        	while(completedQuestions.contains(qqNum)) {
        		qqNum = r.nextInt(maxQQCount);
        	}
        }
        
        question = mustangQuiz.get(qqNum);
        questionText = (TextView)findViewById(R.id.question);
        
        options.add(question.getFirstOpt());
        options.add(question.getSecondOpt());
        options.add(question.getThirdOpt());
        options.add(question.getAnswer());
        
        Collections.shuffle(options);
        
        r1 = (RadioButton)findViewById(R.id.option1);
        r2 = (RadioButton)findViewById(R.id.option2);
        r3 = (RadioButton)findViewById(R.id.option3);
        r4 = (RadioButton)findViewById(R.id.option4);
        nextQ = (Button)findViewById(R.id.next_question);

        setQuestionView();
    }
    
    private void setQuestionView() {
    	questionText.setText(question.getQuestion());
    	r1.setText(options.get(0));
    	r2.setText(options.get(1));
    	r3.setText(options.get(2));
    	r4.setText(options.get(3));
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
