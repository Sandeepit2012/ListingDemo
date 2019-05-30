package com.appstreet.listingdemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.appstreet.listingdemo.fragment.ItemDetailFragment;
import com.appstreet.listingdemo.model.Value;

import java.util.List;

public class ItemViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<Value> mItems;

    public ItemViewPagerAdapter(FragmentManager fm, List<Value> items) {
        super(fm);
        this.mItems = items;
    }

    @Override
    public Fragment getItem(int position) {
        Value item = mItems.get(position);
        return ItemDetailFragment.newInstance(item);
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

}