package com.appstreet.listingdemo.fragment;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appstreet.listingdemo.R;
import com.appstreet.listingdemo.adapter.AnimalPagerAdapter;
import com.appstreet.listingdemo.model.Value;

import java.util.ArrayList;

public class AnimalViewPagerFragment extends Fragment {

    private static final String EXTRA_INITIAL_ITEM_POS = "initial_item_pos";
    private static final String EXTRA_ANIMAL_ITEMS = "animal_items";

    public AnimalViewPagerFragment() {
        // Required empty public constructor
    }

    public static AnimalViewPagerFragment newInstance(int currentItem, ArrayList<Value> animalItems) {
        AnimalViewPagerFragment animalViewPagerFragment = new AnimalViewPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_INITIAL_ITEM_POS, currentItem);
        bundle.putParcelableArrayList(EXTRA_ANIMAL_ITEMS, animalItems);
        animalViewPagerFragment.setArguments(bundle);
        return animalViewPagerFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postponeEnterTransition();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move));
        }
        setSharedElementReturnTransition(null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_animal_view_pager, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int currentItem = getArguments().getInt(EXTRA_INITIAL_ITEM_POS);
        ArrayList<Value> animalItems = getArguments().getParcelableArrayList(EXTRA_ANIMAL_ITEMS);

        AnimalPagerAdapter animalPagerAdapter = new AnimalPagerAdapter(getChildFragmentManager(), animalItems);

        ViewPager viewPager = view.findViewById(R.id.animal_view_pager);
        viewPager.setAdapter(animalPagerAdapter);
        viewPager.setCurrentItem(currentItem);
    }
}