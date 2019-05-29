package com.appstreet.listingdemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.appstreet.listingdemo.fragment.AnimalDetailFragment;
import com.appstreet.listingdemo.model.Hit;
import com.appstreet.listingdemo.model.Value;

import java.util.List;

public class AnimalPagerAdapter extends FragmentStatePagerAdapter {

    private List<Value> animalItems;

    public AnimalPagerAdapter(FragmentManager fm, List<Value> animalItems) {
        super(fm);
        this.animalItems = animalItems;
    }

    @Override
    public Fragment getItem(int position) {
        Value animalItem = animalItems.get(position);
        return AnimalDetailFragment.newInstance(animalItem, animalItem.name);
    }

    @Override
    public int getCount() {
        return animalItems.size();
    }

}