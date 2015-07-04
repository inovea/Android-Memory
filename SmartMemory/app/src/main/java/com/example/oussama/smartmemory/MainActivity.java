package com.example.oussama.smartmemory;

import android.support.v4.app.*;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.oussama.smartmemory.fragment.MenuGame;
import com.example.oussama.smartmemory.model.SQLDataBaseHandler;


public class MainActivity extends ActionBarActivity {

    SQLDataBaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new SQLDataBaseHandler(this);
        if (savedInstanceState == null) {
            startFragment(MenuGame.class, null);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void startFragment(Class clazz, Bundle bundle){
        try {
            Fragment fragment = (Fragment) clazz.newInstance();
            if(bundle != null){
                fragment.setArguments(bundle);
            }

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.item_list_container, fragment);
            ft.addToBackStack(null);
            ft.commit();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() > 1){
            getSupportFragmentManager().popBackStack();
        } else {
            this.finish();
        }
    }

    public SQLDataBaseHandler getDb() {
        return db;
    }
}
