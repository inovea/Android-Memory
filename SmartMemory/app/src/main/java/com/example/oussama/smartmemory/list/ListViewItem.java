package com.example.oussama.smartmemory.list;

import android.graphics.drawable.Drawable;

/**
 * Created by Oussama on 04/07/2015.
 */
public class ListViewItem {

    public final Drawable icon;
    public final String username;
    public final int counter;
    public final int time;
    public final int score;

    public ListViewItem(Drawable icon, String username, int counter, int time, int score) {
        this.icon = icon;
        this.username = username;
        this.counter = counter;
        this.time = time;
        this.score = score;
    }
}
