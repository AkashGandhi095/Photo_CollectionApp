package com.example.photocollectionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.photocollectionapp.Modal.NatureImageData;
import com.example.photocollectionapp.Modal.PetImageData;
import com.example.photocollectionapp.Utils.ApiSingleton;
import com.example.photocollectionapp.fragment.NatureFragment;
import com.example.photocollectionapp.fragment.PetFragment;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        adapter.setupFragments(new PetFragment() , "Pets");
        adapter.setupFragments(new NatureFragment() , "Nature");
        viewPager.setAdapter(adapter);

    }
}