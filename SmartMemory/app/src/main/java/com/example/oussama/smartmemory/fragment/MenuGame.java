package com.example.oussama.smartmemory.fragment;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.oussama.smartmemory.MainActivity;
import com.example.oussama.smartmemory.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class MenuGame extends Fragment {

    private Activity activity;

    public MenuGame() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button button = (Button) getView().findViewById(R.id.buttonPlay);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) activity).startFragment(PlayGame.class, null);
            }
        });

        Button stat = (Button) getView().findViewById(R.id.buttonStat);
        stat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).startFragment(PlayerStatistiques.class, null);
            }
        });
        activity = getActivity();
    }


}
