package com.appstreet.listingdemo.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.appstreet.listingdemo.R;
import com.appstreet.listingdemo.fragment.RecyclerViewFragment;

public class RecyclerViewToFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_to_fragment);

        /*getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.content, RecyclerViewFragment.newInstance(response))
                .commit();*/
    }
}
