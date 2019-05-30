package com.appstreet.listingdemo.fragment;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.appstreet.listingdemo.R;
import com.appstreet.listingdemo.model.Value;
import com.appstreet.listingdemo.utilities.Constants;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class ItemDetailFragment extends Fragment {

    private static final String EXTRA_TRANSITION_NAME = "transition_name";

    public ItemDetailFragment() {
    }

    public static ItemDetailFragment newInstance(Value item) {
        ItemDetailFragment detailFragment = new ItemDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.SELECTED_ITEMS, item);
        bundle.putString(EXTRA_TRANSITION_NAME, item.name);
        detailFragment.setArguments(bundle);
        return detailFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postponeEnterTransition();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Value item = getArguments().getParcelable(Constants.SELECTED_ITEMS);
        String transitionName = getArguments().getString(EXTRA_TRANSITION_NAME);

        ImageView imageView = view.findViewById(R.id.detail_image_view);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageView.setTransitionName(transitionName);
        }

        Picasso.with(getContext())
                .load(item.contentUrl)
                .noFade()
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        startPostponedEnterTransition();
                    }

                    @Override
                    public void onError() {
                        startPostponedEnterTransition();
                    }
                });
    }
}