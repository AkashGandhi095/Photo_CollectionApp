package com.example.photocollectionapp.Utils;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

public class ApiSingleton {

    private static ApiSingleton singleton;
    private RequestQueue requestQueue;

    private ApiSingleton(Context context) {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized ApiSingleton getSingleton(Context context)
    {
        if(singleton == null)
        {
            singleton = new ApiSingleton(context);
        }
        return singleton;
    }

    public void addRequestQueue(JsonArrayRequest requestString)
    {
         requestQueue.add(requestString);
    }
}
