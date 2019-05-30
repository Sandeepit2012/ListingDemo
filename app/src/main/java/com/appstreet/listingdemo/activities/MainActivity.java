package com.appstreet.listingdemo.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.appstreet.listingdemo.R;
import com.appstreet.listingdemo.fragment.RecyclerViewFragment;

public class MainActivity extends AppCompatActivity {

    public static Integer SPAN = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.content, RecyclerViewFragment.newInstance(query))
                        .commit();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // do nothing
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
