package com.example.morrisandroidfinal;

import java.util.ArrayList;
import java.util.Collections;

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
	//HashSet<Integer> completedQuestions = new HashSet<Integer>();
	//Random r = new Random();
	//int maxQQCount = 10;
	ArrayList<String> optionText = new ArrayList<String>();
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
        background = (RelativeLayout)findViewById(R.id.trivia_page);
        mustangTitle = (TextView)findViewById(R.id.title);
        mustang = (ImageView)findViewById(R.id.pony_background);
        
        mustangQuiz = qDB.getQuizQuestions();
        
        /*while(completedQuestions.size() < maxQQCount) {
        	qqNum = r.nextInt(maxQQCount);
        	while(completedQuestions.contains(qqNum)) {
        		qqNum = r.nextInt(maxQQCount);
        	}
        }*/
        
        question = mustangQuiz.get(qqNum);
        questionText = (TextView)findViewById(R.id.question);
        
        r1 = (RadioButton)findViewById(R.id.option1);
        r2 = (RadioButton)findViewById(R.id.option2);
        r3 = (RadioButton)findViewById(R.id.option3);
        r4 = (RadioButton)findViewById(R.id.option4);
        nextQ = (Button)findViewById(R.id.next_question);

        setQuestionView();
        
        nextQ.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				RadioGroup options = (RadioGroup)findViewById(R.id.options);
				RadioButton playerAns = (RadioButton)findViewById(options.getCheckedRadioButtonId());
				if(question.getAnswer() == playerAns.getText()) {
					playerScore++;
				}
				if(qqNum < 10){
					question = mustangQuiz.get(qqNum);
					optionText.clear();
					setQuestionView();
				}
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
    	questionText.setText(question.getQuestion());
        
    	optionText.add(question.getFirstOpt());
        optionText.add(question.getSecondOpt());
        optionText.add(question.getThirdOpt());
        optionText.add(question.getAnswer());
       
        Collections.shuffle(optionText);
    	
        r1.setText(optionText.get(0));
    	r2.setText(optionText.get(1));
    	r3.setText(optionText.get(2));
    	r4.setText(optionText.get(3));
    	qqNum++;
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
