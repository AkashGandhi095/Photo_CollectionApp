package com.example.photocollectionapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.photocollectionapp.Adapter.ImageAdapter;
import com.example.photocollectionapp.Modal.NatureImageData;
import com.example.photocollectionapp.R;
import com.example.photocollectionapp.Utils.ApiSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NatureFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NatureFragment extends Fragment {

    private static final String CLIENT_ID = "1fjtKb7zSd4M_qzb9tf13-u51UvrBC2jNzBGwDEdA6I";
    private final String NATURE_URL = "https://api.unsplash.com/collections/1580860/photos/?client_id=" + CLIENT_ID;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NatureFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NatureFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NatureFragment newInstance(String param1, String param2) {
        NatureFragment fragment = new NatureFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }

    private RecyclerView recyclerView;
    private List<ImageData> imageDataList;
    private ImageAdapter imageAdapter;
    private ImageData imageData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nature, container, false);
        initViews(view);
        getNatureImageData();
        return view;
    }

    private void initViews(View view) {
        imageDataList = new ArrayList<>();


        recyclerView = view.findViewById(R.id.natureRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(requireActivity() , 2));
        imageAdapter = new ImageAdapter(requireActivity() , imageDataList);
        recyclerView.setAdapter(imageAdapter);
    }

    private void getNatureImageData() {


        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, NATURE_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {
                    for (int i=0;i<response.length();i++)
                    {
                        JSONObject jsonObject = response.getJSONObject(i);

                        JSONObject urlObject = jsonObject.getJSONObject("urls");
                        String imgUrls = urlObject.getString("small");
                        String imgDesc = jsonObject.getString("alt_description");

                        imageDataList.add(new ImageData(imgDesc , imgUrls));
                        Log.d("natureData", "onResponse: " +imgUrls + " , desc : " +imgDesc);


                    }

                    imageAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("VolleyResponse", "onErrorResponse: " +error.toString());
            }
        });
        ApiSingleton.getSingleton(requireActivity()).addRequestQueue(request);
    }

}