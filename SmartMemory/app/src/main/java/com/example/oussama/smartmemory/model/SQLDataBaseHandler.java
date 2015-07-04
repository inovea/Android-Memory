package com.example.oussama.smartmemory.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Oussama on 02/07/2015.
 */
public class SQLDataBaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "PlayerDB";
    private static final String TABLE_NAME = "PlayerTable";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_TIME = "time";
    private static final String KEY_COUNTER = "counter";
    private static final String[] COLONNES = { KEY_ID, KEY_NAME, KEY_TIME,
            KEY_COUNTER };

    public SQLDataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.i("SQLite DB : Constructeur ", "Constructeur");

    }

    @Override
    public void onCreate(SQLiteDatabase arg0) {

        String CREATION_TABLE_EXEMPLE = "CREATE TABLE PlayerTable ( "
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "name TEXT, "
                + "time INTEGER, " + "counter INTEGER )";

        arg0.execSQL(CREATION_TABLE_EXEMPLE);
        Log.i("SQLite DB", "Creation");

    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub

        arg0.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(arg0);
        Log.i("SQLite DB", "Upgrade");


    }

    public void deleteOne(Player player) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, // table
                "name = ?", new String[] { player.getUsername() });
        db.close();
//        db.delete(TABLE_NAME, // table
//                "id = ?", new String[] { String.valueOf(player.getId()) });
//        db.close();
        Log.i("SQLite DB : Delete : ", player.toString());

    }

    public Player showOne(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, // a. table
                COLONNES, // b. column names
                " name = ?", // c. selections
                new String[] { name }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        if (cursor != null)
            cursor.moveToFirst();
        Player player = new Player();
        player.setUsername(cursor.getString(1));
        player.setTime(Integer.parseInt(cursor.getString(2)));
        player.setCounter(Integer.parseInt(cursor.getString(3)));
        // log
        Log.i("SQLite DB : Show one  : name=  "+name, player.toString());

        return player;
    }

    public List<Player> showAll() {

        List<Player> players = new LinkedList<>();
        String query = "SELECT  * FROM " + TABLE_NAME + " ORDER BY " + KEY_TIME + " DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Player player= null;
        if (cursor.moveToFirst()) {
            do {
                player = new Player();
                player.setUsername(cursor.getString(1));
                player.setTime(Integer.parseInt(cursor.getString(2)));
                player.setCounter(Integer.parseInt(cursor.getString(3)));
                players.add(player);
            } while (cursor.moveToNext());
        }
        Log.i("SQLite DB : Show All  : ", players.toString());
        return players;
    }

    public void addOne(Player player) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, player.getUsername());
        values.put(KEY_TIME, player.getTime());
        values.put(KEY_COUNTER, player.getCounter());
        // insertion
        db.insert(TABLE_NAME, // table
                null, values);

        db.close();
        Log.i("SQLite DB : Add one  : name=  "+player.getUsername(), player.toString());
    }

    /*public int updateOne(Food food) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, food.getName());
        values.put(KEY_PRICE, food.getPrice());
        values.put(KEY_QUANTITY, food.getQuantity());

        int i = db.update(TABLE_NAME, // table
                values, // column/value
                "id = ?", // selections
                new String[] { String.valueOf(food.getId()) });

        db.close();
        Log.i("SQLite DB : Update one  : id=  "+food.getId(), food.toString());

        return i;
    }*/

}
