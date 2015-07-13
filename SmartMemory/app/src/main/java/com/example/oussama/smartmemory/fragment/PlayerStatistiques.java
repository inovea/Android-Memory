package com.example.oussama.smartmemory.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.oussama.smartmemory.list.ListViewAdapter;
import com.example.oussama.smartmemory.list.ListViewItem;
import com.example.oussama.smartmemory.MainActivity;
import com.example.oussama.smartmemory.model.Player;
import com.example.oussama.smartmemory.R;

import java.util.ArrayList;
import java.util.List;


public class PlayerStatistiques extends ListFragment {

    private List<ListViewItem> mItems;        // ListView items list
    private final int TEMPS_MAX = 60000;
    private final int COUPS_MAX = 30;
    private final int SCORE_MAX = 100;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mItems = new ArrayList<ListViewItem>();
        List<Player> players = ((MainActivity)getActivity()).getDb().showAll();

        for(Player player : players){
            int score = calculScore(player.getCounter(), player.getTime());

            ListViewItem item = new ListViewItem(getResources().getDrawable(R.drawable.ic_memory_back), player.getUsername(), player.getCounter(), timeToString(player.getTime()), score);
            mItems.add(item);
        }
        setListAdapter(new ListViewAdapter(getActivity(), mItems));
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // remove the dividers from the ListView of the ListFragment
        getListView().setDivider(null);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // retrieve theListView item
        ListViewItem item = mItems.get(position);
        final Player player = new Player(item.username, 0, 0, 0);

        // do something
        Toast.makeText(getActivity(), item.username, Toast.LENGTH_SHORT).show();

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                getActivity());

        // set title
        alertDialogBuilder.setTitle(getActivity().getResources().getString(R.string.delete_player_title));

        // set dialog message
        alertDialogBuilder
                .setMessage(getActivity().getResources().getString(R.string.delete_player_text))
                .setCancelable(false)
                .setPositiveButton(getActivity().getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        ((MainActivity)getActivity()).getDb().deleteOne(player);
                        ((MainActivity)getActivity()).onBackPressed();
                    }
                })
                .setNegativeButton(getActivity().getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
    }

    public int calculScore(int counter, int time){
        long p_time = time * 100 / TEMPS_MAX;
        long p_coups = counter * 100 / COUPS_MAX;

        long p_score = (p_time + p_coups) / 2;
        int score = (int) (SCORE_MAX - (SCORE_MAX*p_score/100));

        return score;
    }

    public String timeToString(int finalTime){
        int seconds = (int) (finalTime / 1000);
        int minutes = seconds / 60;
        seconds = seconds % 60;

        String time = "" + minutes + ":" + String.format("%02d", seconds);
        return time;
    }


}
