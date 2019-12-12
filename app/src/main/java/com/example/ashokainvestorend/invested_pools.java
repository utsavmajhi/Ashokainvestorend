package com.example.ashokainvestorend;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.ashokainvestorend.investedpoolrecyclerdata.Getinvestedpoolformat;
import com.example.ashokainvestorend.investedpoolrecyclerdata.Pool;
import com.example.ashokainvestorend.investedpoolrecyclerdata.invespoolitems;
import com.example.ashokainvestorend.investedpoolrecyclerdata.investpoolAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class invested_pools extends Fragment {

    private RecyclerView mRecyclerView;
    int previnv=0;
    int totalinvestedbyhim=0;
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

        SharedPreferences sharedPreferences=this.getActivity().getSharedPreferences("Secrets",MODE_PRIVATE);
        String currentusername=sharedPreferences.getString("username","");
        String currentemail=sharedPreferences.getString("email","");
        String currentph=sharedPreferences.getString("phone","");
        String currentaadhar=sharedPreferences.getString("aadhar","");
        String currenttoken=sharedPreferences.getString("token","");

        /*
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
        */


        //backend retrofit

        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5000/")//change it afterwards when everthing is hosted
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();
        ApiInterface apiInterface=retrofit.create(ApiInterface.class);
        Call<Getinvestedpoolformat> call=apiInterface.getinvestedpools(currenttoken);
        call.enqueue(new Callback<Getinvestedpoolformat>() {
            @Override
            public void onResponse(Call<Getinvestedpoolformat> call, Response<Getinvestedpoolformat> response) {
                if(response.isSuccessful())
                {
                    List<Pool> invpool=response.body().getPools();
                    for(int i=0;i<invpool.size();i++)
                    {
                        String pname=invpool.get(i).getName();
                        String poolid=invpool.get(i).getId();
                        String poolengineerid=invpool.get(i).getEngineerId();
                        String plocation=invpool.get(i).getLocation();
                        String ptotinvests= String.valueOf(invpool.get(i).getTotalInvestment());
                        totalinvestedbyhim+=invpool.get(i).getTotalInvestment();
                        String profits= String.valueOf(invpool.get(i).getPrevProfits());
                        listdata.add(new invespoolitems(poolid,poolengineerid,pname,ptotinvests,plocation,"",profits));
                        recycleradapter.notifyDataSetChanged();
                    }
                    //for storing all total investments in all pools
                    SharedPreferences sharedPreferences=getActivity().getSharedPreferences("Secrets",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("totalinvestmentstillnow", String.valueOf(totalinvestedbyhim));
                    editor.putString("noofinvestedpools", String.valueOf(invpool.size()));
                    editor.apply();
                    //shared preferences end


                    recycleradapter = new investpoolAdapter(getActivity(),listdata);
                    mRecyclerView.setAdapter(recycleradapter);
                    /*
                    listdata.clear();
                    listdata.addAll(tempList);
                    recycleradapter.notifyDataSetChanged();*/

                }
                else {
                    Toast.makeText(getContext(), "Error:"+response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Getinvestedpoolformat> call, Throwable t) {
                Toast.makeText(getContext(), "Error:"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });




    }


}
