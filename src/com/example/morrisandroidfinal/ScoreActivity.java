package com.example.morrisandroidfinal;
import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ScoreActivity extends Activity {
	
	MediaPlayer newPlayer;
	AssetFileDescriptor assets;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score);
		
		/* Create a new media player that will play Peruna
		 * at the end of the Mustang Trivia game
		 */
		
		try {
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
