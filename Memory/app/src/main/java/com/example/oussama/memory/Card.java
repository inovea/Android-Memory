package com.example.oussama.memory;

import android.widget.Button;

/**
 * Created by Oussama on 11/06/2015.
 */
public class Card {
    public int x;
    public int y;
    public Button button;

    public Card(Button button, int x,int y) {
        this.x = x;
        this.y=y;
        this.button=button;
    }

}
