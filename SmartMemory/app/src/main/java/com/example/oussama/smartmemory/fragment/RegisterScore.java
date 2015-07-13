package com.example.oussama.smartmemory.fragment;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.oussama.smartmemory.MainActivity;
import com.example.oussama.smartmemory.model.Player;
import com.example.oussama.smartmemory.R;

import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterScore extends DialogFragment {

    LayoutInflater inflater;
    EditText username;
    View v;
    private Player player;
    SharedPreferences pref;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        inflater = getActivity().getLayoutInflater();
        v = inflater.inflate(R.layout.fragment_register_score, null);
        calculScore();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v).setPositiveButton(getActivity().getResources().getString(R.string.register), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                username = (EditText) v.findViewById(R.id.username);
                player.setUsername(username.getText().toString());
                try {
                    savePlayer();
                    Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.scoreRegistered), Toast.LENGTH_SHORT).show();
                    pref = getActivity().getPreferences(0);
                    int position = pref.getInt("position", 0);
                    switch (position) {
                        case 0:
                            ((MainActivity) getActivity()).startFragment(PlayGame34.class, null);
                            break;
                        case 1:
                            ((MainActivity) getActivity()).startFragment(PlayGame.class, null);
                            break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.scoreNonRegistered), Toast.LENGTH_SHORT).show();
                }
            }
        }).setNegativeButton(getActivity().getResources().getString(R.string.skip), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((MainActivity)getActivity()).startFragment(PlayGame.class, null);
            }
        });

        return builder.create();
    }

    public void calculScore(){
        int counter = getArguments().getInt("mc_counter", 0);
        long time = getArguments().getLong("time", 0);
        //TODO Calcul du score
        int score = (int) (counter*time);

        player = new Player("", (int) time, counter, score);

        TextView tvScore = (TextView)v.findViewById(R.id.textViewScore);
        tvScore.setText(String.valueOf(score));
    }

    public void savePlayer() throws IOException {
        ((MainActivity)getActivity()).getDb().addOne(player);
    }
}
