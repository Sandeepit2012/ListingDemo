package com.appstreet.listingdemo;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.iainconnor.objectcache.CacheManager;
import com.iainconnor.objectcache.DiskCache;

import java.io.File;

public class DemoApplication extends Application {
    private String TAG = DemoApplication.class.getSimpleName();
    private RequestQueue mRequestQueue;
    private static DemoApplication mInstance;
    private CacheManager cacheManager;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized DemoApplication getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public CacheManager getCacheManager() {
        if (cacheManager == null) {
            try {
                File cacheFile = new File(getFilesDir() + File.separator + getPackageName());
                DiskCache diskCache = new DiskCache(cacheFile, BuildConfig.VERSION_CODE, 1024 * 1024 * 10);
                cacheManager = CacheManager.getInstance(diskCache);
            } catch (Exception e) {
            e.printStackTrace();
            }
        }
        return this.cacheManager;
    }
}