package com.appstreet.listingdemo.utilities;

import android.content.Context;
import android.net.ConnectivityManager;

public class AppUtils {

    public static boolean isNetworkConnected(Context ctx) {
        ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
}
