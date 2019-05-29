package com.appstreet.listingdemo.adapter;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.appstreet.listingdemo.R;
import com.appstreet.listingdemo.listener.ItemClickListener;
import com.appstreet.listingdemo.model.Value;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ImageViewHolder> {

    private ItemClickListener mItemClickListener;
    private ArrayList<Value> mItems;

    public GridAdapter(ArrayList<Value> items, ItemClickListener itemClickListener) {
        this.mItems = items;
        this.mItemClickListener = itemClickListener;

    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_square, parent, false));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public void onBindViewHolder(final ImageViewHolder holder, int position) {
        final Value item = mItems.get(position);

        Picasso.with(holder.itemView.getContext())
                .load(item.contentUrl)
                .placeholder(R.drawable.progress)
                .into(holder.imageView);

        ViewCompat.setTransitionName(holder.imageView, item.name);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClick(holder.getAdapterPosition(), item, holder.imageView);
            }
        });
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;

        public ImageViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_image);
        }
    }


}
