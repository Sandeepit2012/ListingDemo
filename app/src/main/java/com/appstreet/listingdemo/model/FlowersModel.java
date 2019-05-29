package com.appstreet.listingdemo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FlowersModel implements Parcelable {

@SerializedName("totalHits")
@Expose
public Integer totalHits;
@SerializedName("hits")
@Expose
public ArrayList<Hit> hits = new ArrayList<>();
@SerializedName("total")
@Expose
public Integer total;

    protected FlowersModel(Parcel in) {
        if (in.readByte() == 0) {
            totalHits = null;
        } else {
            totalHits = in.readInt();
        }
        hits = in.createTypedArrayList(Hit.CREATOR);
        if (in.readByte() == 0) {
            total = null;
        } else {
            total = in.readInt();
        }
    }

    public static final Creator<FlowersModel> CREATOR = new Creator<FlowersModel>() {
        @Override
        public FlowersModel createFromParcel(Parcel in) {
            return new FlowersModel(in);
        }

        @Override
        public FlowersModel[] newArray(int size) {
            return new FlowersModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (totalHits == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(totalHits);
        }
        dest.writeTypedList(hits);
        if (total == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(total);
        }
    }
}