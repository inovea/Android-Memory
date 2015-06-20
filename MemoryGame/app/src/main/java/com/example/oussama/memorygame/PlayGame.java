package com.example.oussama.memorygame;

import java.util.Arrays;
import java.util.Collections;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PlayGame extends Activity {

	private int[] id_mc = new int[16];
	private Integer[][] img_mc = new Integer[16][2];
	private Button[] myMcs = new Button[16];
	private int mc_counter = 0;
	private int firstid = 0;
	private int secondid = 0;
	private Boolean mc_isfirst = false;

	private int correctcounter = 0;
	private TextView tFeedback;
	private MediaPlayer mp;
	private Boolean b_snd_inc, b_snd_cor, b_new_game;

	public static boolean DEBUG = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
		initGame();

	}

	private void initGame() {

		SharedPreferences settings = getSharedPreferences("memoryPrefs", 0);
		b_snd_cor = settings.getBoolean("play_sound_when_correct", true);
		b_snd_inc = settings.getBoolean("play_sound_when_incorrect", true);
		b_new_game = settings.getBoolean("new_game", true);

		if (b_new_game) {
			setContentView(R.layout.game);

			mc_counter = 0;
			firstid = 0;
			secondid = 0;
			mc_isfirst = false;
			correctcounter = 0;

			tFeedback = (TextView) findViewById(R.id.mc_feedback);

			// setup button listeners
			Button startButton = (Button) findViewById(R.id.game_menu);
			startButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View view) {
					startMenu();
				}
			});

			Button settingsButton = (Button) findViewById(R.id.game_settings);
			settingsButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View view) {
					startPrefs();
				}
			});

			// fill arrays with resources
			id_mc[0] = R.id.mc0;
			id_mc[1] = R.id.mc1;
			id_mc[2] = R.id.mc2;
			id_mc[3] = R.id.mc3;
			id_mc[4] = R.id.mc4;
			id_mc[5] = R.id.mc5;
			id_mc[6] = R.id.mc6;
			id_mc[7] = R.id.mc7;
			id_mc[8] = R.id.mc8;
			id_mc[9] = R.id.mc9;
			id_mc[10] = R.id.mc10;
			id_mc[11] = R.id.mc11;
			id_mc[12] = R.id.mc12;
			id_mc[13] = R.id.mc13;
			id_mc[14] = R.id.mc14;
			id_mc[15] = R.id.mc15;

			img_mc[0][0] = R.drawable.back1;
			img_mc[0][1] = R.drawable.ic_img1;
			img_mc[1][0] = R.drawable.back2;
			img_mc[1][1] = R.drawable.ic_img2;
			img_mc[2][0] = R.drawable.back3;
			img_mc[2][1] = R.drawable.ic_img3;
			img_mc[3][0] = R.drawable.back4;
			img_mc[3][1] = R.drawable.ic_img4;
			img_mc[4][0] = R.drawable.back5;
			img_mc[4][1] = R.drawable.ic_img5;
			img_mc[5][0] = R.drawable.back6;
			img_mc[5][1] = R.drawable.ic_img6;
			img_mc[6][0] = R.drawable.back7;
			img_mc[6][1] = R.drawable.ic_img7;
			img_mc[7][0] = R.drawable.back8;
			img_mc[7][1] = R.drawable.ic_img8;
			img_mc[8][0] = R.drawable.back1;
			img_mc[8][1] = R.drawable.ic_img1;
			img_mc[9][0] = R.drawable.back2;
			img_mc[9][1] = R.drawable.ic_img2;
			img_mc[10][0] = R.drawable.back3;
			img_mc[10][1] = R.drawable.ic_img3;
			img_mc[11][0] = R.drawable.back4;
			img_mc[11][1] = R.drawable.ic_img4;
			img_mc[12][0] = R.drawable.back5;
			img_mc[12][1] = R.drawable.ic_img5;
			img_mc[13][0] = R.drawable.back6;
			img_mc[13][1] = R.drawable.ic_img6;
			img_mc[14][0] = R.drawable.back7;
			img_mc[14][1] = R.drawable.ic_img7;
			img_mc[15][0] = R.drawable.back8;
			img_mc[15][1] = R.drawable.ic_img8;

			if (DEBUG == false) {
				Collections.shuffle(Arrays.asList(img_mc));
			}

			for (int i = 0; i < 16; i++) {
				myMcs[i] = (Button) findViewById(id_mc[i]);
				myMcs[i].setBackgroundResource(img_mc[i][0]);
				myMcs[i].setOnClickListener(new View.OnClickListener() {
					public void onClick(View view) {
						int i = 0;
						for (int n = 0; n < 16; n++) {
							if (id_mc[n] == view.getId())
								i = n;
						}

						doClickAction(view, i);

					}
				});
			}
		}
	}

	private void doClickAction(View v, int i)

	{

		v.setBackgroundResource(img_mc[i][1]);
		mc_isfirst = !mc_isfirst;

		// disable all buttons
		for (Button b : myMcs) {
			b.setEnabled(false);
		}

		if (mc_isfirst) {
			// turning the first card

			firstid = i;
			// re enable all except this one
			for (Button b : myMcs) {
				if (b.getId() != firstid) {
					b.setEnabled(true);
				}
			}

		} else {
			// turning the second card
			secondid = i;

			doPlayMove();
		}

	}

	private void doPlayMove() {

		mc_counter++;

		if (img_mc[firstid][1] - img_mc[secondid][1] == 0) {
			// correct
			if (b_snd_cor) {
				playSound(R.raw.correct);
			}
			waiting(200);
			myMcs[firstid].setVisibility(View.INVISIBLE);
			myMcs[secondid].setVisibility(View.INVISIBLE);
			correctcounter++;

		} else {
			// incorrect
			if (b_snd_inc) {
				playSound(R.raw.incorrect);
			}
			waiting(400);
		}

		// re-enable and turn cards back
		for (Button b : myMcs) {
			if (b.getVisibility() != View.INVISIBLE) {
				b.setEnabled(true);
				b.setBackgroundResource(R.drawable.memory_back);
				for (int i = 0; i < 16; i++) {
					myMcs[i].setBackgroundResource(img_mc[i][0]);
				}
			}
		}

		tFeedback.setText(String.format("%d/%d", correctcounter, mc_counter));

		if (correctcounter > 7) {

			Intent iSc = new Intent(getApplicationContext(), Scoreboard.class);
			iSc.putExtra("com.gertrietveld.memorygame.SCORE", mc_counter);
			iSc.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
			startActivity(iSc);

		}

	}

	public void playSound(int sound) {
		mp = MediaPlayer.create(this, sound);
		mp.setVolume((float) .5, (float) .5);
		mp.start();
		mp.setOnCompletionListener(new OnCompletionListener() {
			public void onCompletion(MediaPlayer mp) {
				mp.release();
			}

		});
	}

	public static void waiting(int n) {
		long t0, t1;
		t0 = System.currentTimeMillis();
		do {
			t1 = System.currentTimeMillis();
		} while ((t1 - t0) < (n));
	}

	private void startMenu() {
		Intent launchMenu = new Intent(this, MenuScreen.class);
		startActivity(launchMenu);
	}

	private void startPrefs() {
		Intent launchPrefs = new Intent(this, SettingsScreen.class);
		startActivity(launchPrefs);
	}

	@Override
	protected void onPause() {
		super.onPause();
		SharedPreferences settings = getSharedPreferences("memoryPrefs", 0);
		SharedPreferences.Editor prefeditor = settings.edit();
		prefeditor.putString("previous_screen", "PlayGame");
		prefeditor.commit();
	}


	@Override
	protected void onRestart() {
		super.onRestart();
		initGame();
		if (DEBUG) {
			Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	protected void onResume() {
		super.onResume();
		if (DEBUG)
			Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
	}


}