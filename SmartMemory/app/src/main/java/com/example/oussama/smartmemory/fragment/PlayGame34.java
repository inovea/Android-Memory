package com.example.oussama.smartmemory.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.oussama.smartmemory.MainActivity;
import com.example.oussama.smartmemory.R;

public class PlayGame34 extends Fragment {

    private int[] id_mc = new int[12];
    private Integer[][] img_mc = new Integer[12][2];
    private Button[] myMcs = new Button[12];
    private int mc_counter = 0;
    private int firstid = 0;
    private int secondid = 0;
    private Boolean mc_isfirst = false;

    private int correctcounter = 0;
    private TextView tvScore;

    //TIMER
    private TextView textTimer;
    private long startTime = 0L;
    private Handler myHandler;
    long timeInMillies = 0L;
    long timeSwap = 0L;
    long finalTime = 0L;


    public PlayGame34() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_play_game34, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity) getActivity()).getSupportActionBar().hide();
        initGame();
        launchTimer();
    }

    public void launchTimer() {
        textTimer = (TextView) getView().findViewById(R.id.mc_time);
        startTime = SystemClock.uptimeMillis();
        myHandler = new Handler();
        myHandler.postDelayed(updateTimerMethod, 0);
    }

    private Runnable updateTimerMethod = new Runnable() {
        @Override
        public void run() {
            timeInMillies = SystemClock.uptimeMillis() - startTime;
            finalTime = timeSwap + timeInMillies;

            int seconds = (int) (finalTime / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;
//            int milliseconds = (int) (finalTime % 1000);

            textTimer.setText("" + minutes + ":"
                            + String.format("%02d", seconds)
//                    + ":" + String.format("%03d", milliseconds)
            );

            myHandler.postDelayed(this, 0);
        }
    };


    private void initGame() {

            mc_counter = 0;
            firstid = 0;
            secondid = 0;
            mc_isfirst = false;
            correctcounter = 0;

            tvScore = (TextView) getView().findViewById(R.id.mc_feedback);

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
            img_mc[6][0] = R.drawable.back1;
            img_mc[6][1] = R.drawable.ic_img1;
            img_mc[7][0] = R.drawable.back2;
            img_mc[7][1] = R.drawable.ic_img2;
            img_mc[8][0] = R.drawable.back3;
            img_mc[8][1] = R.drawable.ic_img3;
            img_mc[9][0] = R.drawable.back4;
            img_mc[9][1] = R.drawable.ic_img4;
            img_mc[10][0] = R.drawable.back5;
            img_mc[10][1] = R.drawable.ic_img5;
            img_mc[11][0] = R.drawable.back6;
            img_mc[11][1] = R.drawable.ic_img6;

            for (int i = 0; i < 12; i++) {
                myMcs[i] = (Button) getView().findViewById(id_mc[i]);
                myMcs[i].setBackgroundResource(img_mc[i][0]);
                myMcs[i].setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        int i = 0;
                        for (int n = 0; n < 12; n++) {
                            if (id_mc[n] == view.getId())
                                i = n;
                        }

                        doClickAction(view, i);

                    }
                });
            }


    }

    private void doClickAction(View v, int i) {

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
            for (int j = 0; j < 16; j++) {
                if(i != j){
                    myMcs[j].setEnabled(true);
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
            waiting(200);
            myMcs[firstid].setVisibility(View.INVISIBLE);
            myMcs[secondid].setVisibility(View.INVISIBLE);
            correctcounter++;

        } else {
            // incorrect
            waiting(400);
        }

        // re-enable and turn cards back
        for (Button b : myMcs) {
            if (b.getVisibility() != View.INVISIBLE) {
                b.setEnabled(true);
                b.setBackgroundResource(R.drawable.memory_back);
                for (int i = 0; i < 12; i++) {
                    myMcs[i].setBackgroundResource(img_mc[i][0]);
                }
            }
        }

        tvScore.setText(String.format("%d/%d", correctcounter, mc_counter));

        if (correctcounter > 5) {
            timeSwap += timeInMillies;
            myHandler.removeCallbacks(updateTimerMethod);

            RegisterScore dialog = new RegisterScore();
            Bundle bundle = new Bundle();
            bundle.putInt("mc_counter", mc_counter);
            bundle.putLong("time", finalTime);
            dialog.setArguments(bundle);
            dialog.show(getFragmentManager(), "Register Score");

        }

    }

    public static void waiting(int n) {
        long t0, t1;
        t0 = System.currentTimeMillis();
        do {
            t1 = System.currentTimeMillis();
        } while ((t1 - t0) < (n));
    }


}
