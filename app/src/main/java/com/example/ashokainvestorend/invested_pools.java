package com.example.ashokainvestorend;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ashokainvestorend.investedpoolrecyclerdata.invespoolitems;
import com.example.ashokainvestorend.investedpoolrecyclerdata.investpoolAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class invested_pools extends Fragment {

    private RecyclerView mRecyclerView;
    private investpoolAdapter recycleradapter;
    private RequestQueue mRequestQueue;
    View v;
    private ArrayList<invespoolitems> listdata;
    private ArrayList<invespoolitems> tempList;
    public invested_pools() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_invested_pools, container, false);
        mRecyclerView=view.findViewById(R.id.investedrecycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        tempList = new ArrayList<invespoolitems>();
        listdata = new ArrayList<invespoolitems>();

        AmbilData();

        recycleradapter = new investpoolAdapter(getActivity(),listdata);
        mRecyclerView.setAdapter(recycleradapter);
        recycleradapter.notifyDataSetChanged();
        return view;


    }

    private void AmbilData() {
        String url="https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=kitten&image_type=photo&pretty=true";
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray=response.getJSONArray("hits");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);
                                String poolname = hit.getString("user");
                                String mImageurl= hit.getString("tags");
                                String area = hit.getString("imageHeight");
                                String location = hit.getString("views");
                                String profit=hit.getString("views");
                                String Report=hit.getString("downloads");

                                //remember maintain the same order as in poolitemslist.java
                                tempList.add(new invespoolitems(mImageurl,poolname,area,location,profit,Report));
                                recycleradapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        listdata.clear();
                        listdata.addAll(tempList);
                        recycleradapter.notifyDataSetChanged();}
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
            }
        });

        Volley.newRequestQueue(getActivity()).add(request);



    }

}
