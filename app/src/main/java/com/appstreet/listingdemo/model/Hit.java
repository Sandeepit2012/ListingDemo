package com.appstreet.listingdemo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hit implements Parcelable {

@SerializedName("largeImageURL")
@Expose
public String largeImageURL;
@SerializedName("webformatHeight")
@Expose
public Integer webformatHeight;
@SerializedName("webformatWidth")
@Expose
public Integer webformatWidth;
@SerializedName("likes")
@Expose
public Integer likes;
@SerializedName("imageWidth")
@Expose
public Integer imageWidth;
@SerializedName("id")
@Expose
public Integer id;
@SerializedName("user_id")
@Expose
public Integer user_id;
@SerializedName("views")
@Expose
public Integer views;
@SerializedName("comments")
@Expose
public Integer comments;
@SerializedName("pageURL")
@Expose
public String pageURL;
@SerializedName("imageHeight")
@Expose
public Integer imageHeight;
@SerializedName("webformatURL")
@Expose
public String webformatURL;
@SerializedName("type")
@Expose
public String type;
@SerializedName("previewHeight")
@Expose
public Integer previewHeight;
@SerializedName("tags")
@Expose
public String tags;
@SerializedName("downloads")
@Expose
public Integer downloads;
@SerializedName("user")
@Expose
public String user;
@SerializedName("favorites")
@Expose
public Integer favorites;
@SerializedName("imageSize")
@Expose
public Integer imageSize;
@SerializedName("previewWidth")
@Expose
public Integer previewWidth;
@SerializedName("userImageURL")
@Expose
public String userImageURL;
@SerializedName("previewURL")
@Expose
public String previewURL;

    protected Hit(Parcel in) {
        largeImageURL = in.readString();
        if (in.readByte() == 0) {
            webformatHeight = null;
        } else {
            webformatHeight = in.readInt();
        }
        if (in.readByte() == 0) {
            webformatWidth = null;
        } else {
            webformatWidth = in.readInt();
        }
        if (in.readByte() == 0) {
            likes = null;
        } else {
            likes = in.readInt();
        }
        if (in.readByte() == 0) {
            imageWidth = null;
        } else {
            imageWidth = in.readInt();
        }
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        if (in.readByte() == 0) {
            user_id = null;
        } else {
            user_id = in.readInt();
        }
        if (in.readByte() == 0) {
            views = null;
        } else {
            views = in.readInt();
        }
        if (in.readByte() == 0) {
            comments = null;
        } else {
            comments = in.readInt();
        }
        pageURL = in.readString();
        if (in.readByte() == 0) {
            imageHeight = null;
        } else {
            imageHeight = in.readInt();
        }
        webformatURL = in.readString();
        type = in.readString();
        if (in.readByte() == 0) {
            previewHeight = null;
        } else {
            previewHeight = in.readInt();
        }
        tags = in.readString();
        if (in.readByte() == 0) {
            downloads = null;
        } else {
            downloads = in.readInt();
        }
        user = in.readString();
        if (in.readByte() == 0) {
            favorites = null;
        } else {
            favorites = in.readInt();
        }
        if (in.readByte() == 0) {
            imageSize = null;
        } else {
            imageSize = in.readInt();
        }
        if (in.readByte() == 0) {
            previewWidth = null;
        } else {
            previewWidth = in.readInt();
        }
        userImageURL = in.readString();
        previewURL = in.readString();
    }

    public static final Creator<Hit> CREATOR = new Creator<Hit>() {
        @Override
        public Hit createFromParcel(Parcel in) {
            return new Hit(in);
        }

        @Override
        public Hit[] newArray(int size) {
            return new Hit[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(largeImageURL);
        if (webformatHeight == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(webformatHeight);
        }
        if (webformatWidth == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(webformatWidth);
        }
        if (likes == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(likes);
        }
        if (imageWidth == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(imageWidth);
        }
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        if (user_id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(user_id);
        }
        if (views == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(views);
        }
        if (comments == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(comments);
        }
        dest.writeString(pageURL);
        if (imageHeight == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(imageHeight);
        }
        dest.writeString(webformatURL);
        dest.writeString(type);
        if (previewHeight == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(previewHeight);
        }
        dest.writeString(tags);
        if (downloads == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(downloads);
        }
        dest.writeString(user);
        if (favorites == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(favorites);
        }
        if (imageSize == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(imageSize);
        }
        if (previewWidth == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(previewWidth);
        }
        dest.writeString(userImageURL);
        dest.writeString(previewURL);
    }
}