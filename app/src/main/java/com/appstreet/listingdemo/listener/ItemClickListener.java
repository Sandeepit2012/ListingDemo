package com.appstreet.listingdemo.listener;

import android.widget.ImageView;

import com.appstreet.listingdemo.model.Hit;
import com.appstreet.listingdemo.model.Value;

public interface ItemClickListener {
    void onItemClick(int pos, Value gridItem, ImageView shareImageView);
}