package com.appstreet.listingdemo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Value implements Parcelable {

public String webSearchUrl;
public String name;
public String thumbnailUrl;
public String datePublished;
public Boolean isFamilyFriendly;
public String contentUrl;
public String hostPageUrl;
public String contentSize;
public String encodingFormat;
public String hostPageDisplayUrl;
public Integer width;
public Integer height;
public String imageInsightsToken;
public String imageId;
public String accentColor;

    protected Value(Parcel in) {
        webSearchUrl = in.readString();
        name = in.readString();
        thumbnailUrl = in.readString();
        datePublished = in.readString();
        byte tmpIsFamilyFriendly = in.readByte();
        isFamilyFriendly = tmpIsFamilyFriendly == 0 ? null : tmpIsFamilyFriendly == 1;
        contentUrl = in.readString();
        hostPageUrl = in.readString();
        contentSize = in.readString();
        encodingFormat = in.readString();
        hostPageDisplayUrl = in.readString();
        if (in.readByte() == 0) {
            width = null;
        } else {
            width = in.readInt();
        }
        if (in.readByte() == 0) {
            height = null;
        } else {
            height = in.readInt();
        }
        imageInsightsToken = in.readString();
        imageId = in.readString();
        accentColor = in.readString();
    }

    public static final Creator<Value> CREATOR = new Creator<Value>() {
        @Override
        public Value createFromParcel(Parcel in) {
            return new Value(in);
        }

        @Override
        public Value[] newArray(int size) {
            return new Value[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(webSearchUrl);
        dest.writeString(name);
        dest.writeString(thumbnailUrl);
        dest.writeString(datePublished);
        dest.writeByte((byte) (isFamilyFriendly == null ? 0 : isFamilyFriendly ? 1 : 2));
        dest.writeString(contentUrl);
        dest.writeString(hostPageUrl);
        dest.writeString(contentSize);
        dest.writeString(encodingFormat);
        dest.writeString(hostPageDisplayUrl);
        if (width == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(width);
        }
        if (height == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(height);
        }
        dest.writeString(imageInsightsToken);
        dest.writeString(imageId);
        dest.writeString(accentColor);
    }
}