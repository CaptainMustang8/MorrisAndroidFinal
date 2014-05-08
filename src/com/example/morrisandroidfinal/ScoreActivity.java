package com.example.morrisandroidfinal;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ScoreActivity extends Activity {
	// declares all views here, to be inflated within the onCreate method
	MediaPlayer newPlayer;
	AssetFileDescriptor assets;
	RelativeLayout background;
	ImageView smu;
	TextView snarkyResponse;
	Button newGame;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score);
		background = (RelativeLayout)findViewById(R.id.scorepage);
		smu = (ImageView)findViewById(R.id.block_letters);
		snarkyResponse = (TextView)findViewById(R.id.score_text);
		newGame = (Button)findViewById(R.id.new_game);
		
		/* Create a new media player that will play Peruna
		 * at the end of the Mustang Trivia game
		 */
		
		try {
			// Opens mp3 file of Peruna and plays it
			assets = getAssets().openFd("peruna.mp3");
			newPlayer = new MediaPlayer();
			newPlayer.setDataSource(assets.getFileDescriptor(), assets.getStartOffset(), assets.getLength());
			newPlayer.setLooping(true);
			newPlayer.prepare();
			newPlayer.start();
		} 
		catch(IOException e) {
			e.printStackTrace();
		}
		
		// This grabs the score from the previous bundle
		Bundle b = getIntent().getExtras();
		int score = b.getInt("score");
		
		// Switch statement for series of snarky responses
		switch(score){
		case 0: snarkyResponse.setText("You scored a 0.  I'm not sure why you got this app.");
				break;
		case 1: snarkyResponse.setText("You scored a 1.  You're probably a Horned Frog.");
				break;
		case 2: snarkyResponse.setText("You scored a 2.  Dude.");
				break;
		case 3: snarkyResponse.setText("You scored a 3.  .300 is a good batting average, but there's no baseball at SMU, is there?");
				break;
		case 4: snarkyResponse.setText("You scored a 4.  I'll give you a break, you must be new here.");
				break;
		case 5: snarkyResponse.setText("You scored a 5.  Not bad, not good.  You've got work to do, freshman...");
				break;
		case 6: snarkyResponse.setText("You scored a 6.  You're improving, I'll give you that.");
				break;
		case 7: snarkyResponse.setText("You scored a 7.  Pretty good, but there's always room for improvement.");
				break;
		case 8: snarkyResponse.setText("You scored a 8.  Solid.  It must be day 3 of band camp.");
				break;
		case 9: snarkyResponse.setText("You scored a 9.  You're almost there!");
				break;
		case 10: snarkyResponse.setText("You scored a 10.  Well done!  You've certainly earned your beanie, freshman.  HUBBA!");
				break;
		}
		
		// this onClickListener will return us to the first question, 
		// and a new quiz will begin
		newGame.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent newInt = new Intent(ScoreActivity.this, MainActivity.class);
				startActivity(newInt);
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.score, menu);
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
	
	public void onPause() {
		super.onPause();
		newPlayer.pause();
	}
	
	public void onResume() {
		super.onResume();
		newPlayer.start();
	}
	
	protected void onStop() {
		super.onStop();
		newPlayer.stop();
		newPlayer = null;
	}
}
