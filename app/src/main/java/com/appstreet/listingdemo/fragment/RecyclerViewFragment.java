package com.appstreet.listingdemo.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.appstreet.listingdemo.R;
import com.appstreet.listingdemo.activities.MainActivity;
import com.appstreet.listingdemo.adapter.GridAdapter;
import com.appstreet.listingdemo.listener.DataListener;
import com.appstreet.listingdemo.listener.ItemClickListener;
import com.appstreet.listingdemo.model.DataModel;
import com.appstreet.listingdemo.model.Value;
import com.appstreet.listingdemo.presenter.DataPresenter;
import com.google.gson.Gson;

import java.util.ArrayList;


@SuppressLint("ValidFragment")
public class RecyclerViewFragment extends Fragment implements ItemClickListener {

    public static final String TAG = RecyclerViewFragment.class.getSimpleName();
    public String mSearch;
    DataModel model;
    private boolean isLoading=false;
    ArrayList<Value> mValue=new ArrayList<>();

    @SuppressLint("ValidFragment")
    public RecyclerViewFragment(String response) {
        this.mSearch =response;
    }

    public static RecyclerViewFragment newInstance(String response) {
        return new RecyclerViewFragment(response);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_recycler_view, container, false);;
        init(view);
        return view;
    }

    /*@Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }*/
    private RecyclerView mRecyclerView;
    private GridAdapter mAdapter;
    private ProgressBar mProgress;
    private void init(View view) {
        mProgress = view.findViewById(R.id.pb_main_progress);
        mRecyclerView = view.findViewById(R.id.rv_items);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), MainActivity.SPAN);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        getList(mSearch);
        initScrollListener();

        /*Gson gson = new Gson();
        model = gson.fromJson(mSearch, DataModel.class);
        mRecyclerView = view.findViewById(R.id.rv_items);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), MainActivity.SPAN);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mAdapter = new GridAdapter(model.value, RecyclerViewFragment.this);
        mRecyclerView.setAdapter(mAdapter);*/
    }
    int offset;
    private void getList(String query) {
        mProgress.setVisibility(View.VISIBLE);
        new DataPresenter(new DataListener() {
            @Override
            public void onSuccess(String response) {
                mProgress.setVisibility(View.GONE);

                Log.d("data", response);
                try {
//                    JSONObject obj = new JSONObject(response);
//                    mOffset = obj.getInt("nextOffset");
                    Gson gson = new Gson();
                    model = gson.fromJson(response, DataModel.class);
                    mValue.addAll(model.value);
                    offset = model.nextOffset;
                    if(model.currentOffset==0){
                        mAdapter = new GridAdapter(mValue, RecyclerViewFragment.this);
                        mRecyclerView.setAdapter(mAdapter);
                    }else {
                        mValue.addAll(model.value);
                    }
                } catch (Exception e) {
                    mProgress.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.error), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail() {
                Log.d("data", "failed");
            }
        }, query, offset).getData();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_colunm_two) {
            MainActivity.SPAN = 2;
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), MainActivity.SPAN));
            return true;
        }
        if (id == R.id.action_colunm_three) {
            MainActivity.SPAN = 3;
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), MainActivity.SPAN));
            return true;
        }
        if (id == R.id.action_colunm_four) {
            MainActivity.SPAN = 4;
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), MainActivity.SPAN));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initScrollListener() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), MainActivity.SPAN);
                int childAdapterPosition = recyclerView.getChildAdapterPosition(recyclerView.getChildAt(recyclerView.getChildCount() - 1));
                if (!isLoading) {
                    if ( childAdapterPosition == mValue.size() - 1) {
                        loadMore();
                        isLoading = true;
                    }
                }
            }
        });
    }

    private void loadMore() {
        //model.value.add(null);
//        mAdapter.notifyItemInserted(model.value.size() - 1);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getList(mSearch);
                mAdapter.notifyItemInserted(mValue.size() - 1);
                mAdapter.notifyDataSetChanged();
                isLoading = false;
            }
        }, 2000);
    }

    @Override
    public void onItemClick(int pos, Value gridItem, ImageView shareImageView) {
        Fragment animalViewPagerFragment = AnimalViewPagerFragment.newInstance(pos, mValue);
        getFragmentManager()
                .beginTransaction()
                .addSharedElement(shareImageView, ViewCompat.getTransitionName(shareImageView))
                .addToBackStack(TAG)
                .add(R.id.content, animalViewPagerFragment)
                .commit();
    }
}