package com.example.oussama.smartmemory;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;


public class PlayGame extends Fragment {

    private int[] id_mc = new int[16];
    private Integer[][] img_mc = new Integer[16][2];
    private Button[] myMcs = new Button[16];
    private int mc_counter = 0;
    private int firstid = 0;
    private int secondid = 0;
    private Boolean mc_isfirst = false;

    private int correctcounter = 0;
    private TextView tFeedback;
    private Boolean b_snd_inc, b_snd_cor, b_new_game;

    public static boolean DEBUG = false;

    public PlayGame() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_play_game, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initGame();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void initGame() {
        SharedPreferences settings = getActivity().getSharedPreferences("memoryPrefs", 0);
        b_new_game = settings.getBoolean("new_game", true);
//        b_snd_cor = settings.getBoolean("play_sound_when_correct", false);
//        b_snd_inc = settings.getBoolean("play_sound_when_incorrect", false);

        if (b_new_game) {
            //setContentView(R.layout.game);

            mc_counter = 0;
            firstid = 0;
            secondid = 0;
            mc_isfirst = false;
            correctcounter = 0;

            tFeedback = (TextView) getView().findViewById(R.id.mc_feedback);

            // setup button listeners
            Button startButton = (Button) getView().findViewById(R.id.game_menu);
            startButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
//                    startMenu();
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
                myMcs[i] = (Button) getView().findViewById(id_mc[i]);
                myMcs[i].setBackgroundResource(img_mc[i][0]);
                myMcs[i].setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        int i = 0;
                        for (int n = 0; n < 16; n++) {
                            if (id_mc[n] == view.getId())
                                i = n;
                        }

//                        doClickAction(view, i);

                    }
                });
            }
        }
    }
}
