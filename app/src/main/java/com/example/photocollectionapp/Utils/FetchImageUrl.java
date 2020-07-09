package com.example.photocollectionapp.Utils;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class FetchImageUrl implements Response.Listener<JSONArray> ,Response.ErrorListener {

    private Context context;

    public FetchImageUrl(Context context)
    {
        this.context = context;
    }

    public void getImageUrlFromApi(String imageUrl)
    {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET , imageUrl , null , this , this);
        ApiSingleton.getSingleton(context).addRequestQueue(request);
    }


    @Override
    public void onResponse(JSONArray response) {
        try {
            for (int i = 0; i < response.length(); i++) {
                JSONObject jsonObject = response.getJSONObject(i);

                JSONObject urlObject = jsonObject.getJSONObject("urls");
                String imgUrls = urlObject.getString("small");
                Log.d("imgUrls", "onResponse: " +imgUrls);
            }
        }
        catch (JSONException e)
        {

        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }


}
