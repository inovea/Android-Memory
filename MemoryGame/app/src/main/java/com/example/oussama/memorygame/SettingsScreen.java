package com.example.oussama.memorygame;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class SettingsScreen extends Activity {

	private CheckBox ch_sound_correct, ch_sound_incorrect;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);

		SharedPreferences settings = getSharedPreferences("memoryPrefs", 0);

		ch_sound_correct = (CheckBox) findViewById(R.id.ch_sound_correct);
		ch_sound_incorrect = (CheckBox) findViewById(R.id.ch_sound_incorrect);
		ch_sound_correct.setChecked(settings.getBoolean(
				"play_sound_when_correct", true));
		ch_sound_incorrect.setChecked(settings.getBoolean(
				"play_sound_when_incorrect", true));

		Button saveButtonMenu = (Button) findViewById(R.id.save_prefs);
		saveButtonMenu.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent launchMenu = new Intent(getBaseContext(),
						MenuScreen.class);
				setPreferencesAndGoTo(launchMenu);
			}

		});

		Button saveButtonGame = (Button) findViewById(R.id.save_game);
		saveButtonGame.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent launchGame = new Intent(getBaseContext(), PlayGame.class);
				setPreferencesAndGoTo(launchGame);
			}
		});

	}

	private void setPreferencesAndGoTo(Intent theIntent) {
		SharedPreferences settings = getSharedPreferences("memoryPrefs", 0);
		SharedPreferences.Editor prefeditor = settings.edit();
		prefeditor.putBoolean("play_sound_when_correct",
				ch_sound_correct.isChecked());
		prefeditor.putBoolean("play_sound_when_incorrect",
				ch_sound_incorrect.isChecked());
		prefeditor.putBoolean("new_game", false);
		prefeditor.commit();
		startActivity(theIntent);
	}

	@Override
	protected void onStart() {
		super.onStart();
		Button saveButtonGame = (Button) findViewById(R.id.save_game);
		Button saveButtonMenu = (Button) findViewById(R.id.save_prefs);
		SharedPreferences settings = getSharedPreferences("memoryPrefs", 0);
		//Toast.makeText(this, settings.getString("previous_screen","---"), Toast.LENGTH_SHORT).show();
		if (settings.getString("previous_screen","") == "MenuScreen") {
			saveButtonGame.setVisibility(View.GONE);
			saveButtonMenu.setVisibility(View.VISIBLE);
		}
		
		if (settings.getString("previous_screen","") == "PlayGame") {
			saveButtonMenu.setVisibility(View.GONE);
			saveButtonGame.setVisibility(View.VISIBLE);
		}
	}


}
