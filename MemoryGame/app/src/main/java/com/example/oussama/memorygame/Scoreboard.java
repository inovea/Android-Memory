package com.example.oussama.memorygame;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class Scoreboard extends Activity {
	
	public static boolean DEBUG = false;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (DEBUG) {
			Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show(); 
		}

	}
	
	@Override
	public void onNewIntent(Intent intent)
	{
	  super.onNewIntent(intent);
	  setIntent(intent);
	}
	
	
	
	@Override protected void onStart() { 
		super.onStart();

		setContentView(R.layout.scoreboard);
		
		int score = getIntent().getIntExtra("com.gertrietveld.memorygame.SCORE",99);

		TextView t = (TextView) findViewById(R.id.scoreboard_text);
		t.setText( String.format( getString(R.string.scoreboard_text), score ) );
		
		
		Button startButton = (Button) findViewById(R.id.play_again);
		startButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startGame();
			}
		});
		
	}
	
	private void startGame() {
		SharedPreferences settings = getSharedPreferences("memoryPrefs", 0);
		SharedPreferences.Editor prefeditor = settings.edit();
		prefeditor.putBoolean("new_game", true);
		prefeditor.commit();
		Intent launchGame = new Intent(this, PlayGame.class);
		startActivity(launchGame);
	}

}
