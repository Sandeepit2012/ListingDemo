package com.appstreet.listingdemo.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;

import com.appstreet.listingdemo.R;
import com.appstreet.listingdemo.fragment.RecyclerViewFragment;
import com.appstreet.listingdemo.listener.DataListener;
import com.appstreet.listingdemo.presenter.DataPresenter;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_ANIMAL_ITEM = "animal_image_url";
    public static final String EXTRA_ANIMAL_IMAGE_TRANSITION_NAME = "animal_image_transition_name";
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
                // filter recycler view when query submitted
//                getList(query);
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.content, RecyclerViewFragment.newInstance(query))
                        .commit();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                return false;
            }
        });

    }

    int mOffset = 0;

    private void getList(String query) {
        new DataPresenter(new DataListener() {
            @Override
            public void onSuccess(String response) {
                Log.d("data", response);
                try {
                    JSONObject obj = new JSONObject(response);
                    mOffset = obj.getInt("nextOffset");
                    getSupportFragmentManager()
                            .beginTransaction()
                            .add(R.id.content, RecyclerViewFragment.newInstance(response))
                            .commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail() {
                Log.d("data", "failed");
            }
        }, query, mOffset).getData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
