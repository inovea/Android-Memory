package com.example.oussama.smartmemory.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.oussama.smartmemory.MainActivity;
import com.example.oussama.smartmemory.R;

public class SettingGame extends Fragment {

    RadioGroup rg;
    RadioButton rb33, rb44, rb45;
    int position;
    SharedPreferences pref;

    public SettingGame() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_setting_game, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pref  = getActivity().getPreferences(0);
        position = pref.getInt("position", 0);

        rg = (RadioGroup) getView().findViewById(R.id.radioGroup1);

        switch (position){
            case 0 : rg.check(R.id.radio43);
                break;
            case 1 : rg.check(R.id.radio44);
                break;
        }



        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                SharedPreferences.Editor edt = pref.edit();

                // Method 1 For Getting Index of RadioButton
                position = rg.indexOfChild(getView().findViewById(checkedId));


                switch (position) {
                    case 0:
                        edt.putInt("position", position);
                        break;
                    case 1:
                        edt.putInt("position", position);
                        break;
                    default:
                        edt.putInt("position", 0);
                        break;
                }

                edt.commit();
                ((MainActivity)getActivity()).onBackPressed();
            }
        });
    }
}
