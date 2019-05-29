package com.appstreet.listingdemo.presenter;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.appstreet.listingdemo.DemoApplication;
import com.appstreet.listingdemo.listener.DataListener;
import com.appstreet.listingdemo.listener.OnDataCall;

import java.util.HashMap;
import java.util.Map;

public class DataPresenter implements OnDataCall {

    private DataListener mDataListener;
    String mQuery;
    int mOffset;
    public DataPresenter(DataListener dataListener, String query, int offset) {
        this.mOffset = offset;
        this.mQuery=query;
        this.mDataListener = dataListener;
    }
    @Override
    public void getData() {
        String url = "https://api.cognitive.microsoft.com/bing/v7.0/images/search?q="+mQuery+"&count=10&offset="+mOffset+"&mkt=en-us&safeSearch=Moderate";//"https://pixabay.com/api/?key=12610660-a4703616120260c7e98a9e04c&q=yellow+flowers&image_type=photo";
        try {
            StringRequest jsonObjReq = new StringRequest(Request.Method.GET,
                    url, new Response.Listener<String>() {
                @Override
                public void onResponse(final String response) {
                    try {
                        if (mDataListener != null) {
                            mDataListener.onSuccess(response);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (mDataListener != null)
                            mDataListener.onFail();
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    if (mDataListener != null)
                        mDataListener.onFail();
                }
            }) {

                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    return params;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<>();
                    headers.put("Ocp-Apim-Subscription-Key", "c04c9720ca444e369b4320cfea3b97ac");
                    return headers;
                }
            };
            DemoApplication.getInstance().addToRequestQueue(jsonObjReq);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
