package com.appstreet.listingdemo.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.appstreet.listingdemo.R;
import com.appstreet.listingdemo.model.Hit;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class AnimalDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_detail);
        supportPostponeEnterTransition();

        Bundle extras = getIntent().getExtras();
        Hit animalItem = extras.getParcelable(MainActivity.EXTRA_ANIMAL_ITEM);

        ImageView imageView = findViewById(R.id.detail_image_view);

        String imageUrl = animalItem.largeImageURL;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            String imageTransitionName = extras.getString(MainActivity.EXTRA_ANIMAL_IMAGE_TRANSITION_NAME);
            imageView.setTransitionName(imageTransitionName);
        }

        Picasso.with(this)
                .load(imageUrl)
                .noFade()
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        supportStartPostponedEnterTransition();
                    }

                    @Override
                    public void onError() {
                        supportStartPostponedEnterTransition();
                    }
                });
    }
}
