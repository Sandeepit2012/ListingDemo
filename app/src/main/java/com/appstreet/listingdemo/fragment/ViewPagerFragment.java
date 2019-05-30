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
import com.appstreet.listingdemo.adapter.ItemViewPagerAdapter;
import com.appstreet.listingdemo.model.Value;
import com.appstreet.listingdemo.utilities.Constants;

import java.util.ArrayList;

public class ViewPagerFragment extends Fragment {

    public ViewPagerFragment() {

    }

    public static ViewPagerFragment newInstance(int currentItem, ArrayList<Value> items) {
        ViewPagerFragment viewPagerFragment = new ViewPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.ITEM_POSITION, currentItem);
        bundle.putParcelableArrayList(Constants.SELECTED_ITEMS, items);
        viewPagerFragment.setArguments(bundle);
        return viewPagerFragment;
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
        return inflater.inflate(R.layout.fragment_view_pager, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int currentItem = getArguments().getInt(Constants.ITEM_POSITION);
        ArrayList<Value> items = getArguments().getParcelableArrayList(Constants.SELECTED_ITEMS);
        ItemViewPagerAdapter pagerAdapter = new ItemViewPagerAdapter(getChildFragmentManager(), items);
        ViewPager viewPager = view.findViewById(R.id.item_view_pager);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(currentItem);
        pagerAdapter.notifyDataSetChanged();
    }
}