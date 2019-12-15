package com.example.ashokainvestorend;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.ashokainvestorend.allpoolrecyclerdata.Getallpoolsformat;
import com.example.ashokainvestorend.allpoolrecyclerdata.Pool;
import com.example.ashokainvestorend.allpoolrecyclerdata.poolAdapter;
import com.example.ashokainvestorend.allpoolrecyclerdata.poolitems;

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
public class all_pools extends Fragment{
    private RecyclerView mRecyclerView;
    private poolAdapter recycleradapter;
    private RequestQueue mRequestQueue;
    View v;
    private ArrayList<poolitems> listdata;
    private ArrayList<poolitems> tempList;
    public all_pools() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_all_pools, container, false);
        mRecyclerView=view.findViewById(R.id.recyclerviewallpool);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        tempList = new ArrayList<poolitems>();
        listdata = new ArrayList<poolitems>();

        AmbilData();

        recycleradapter = new poolAdapter(getActivity(),listdata);
        mRecyclerView.setAdapter(recycleradapter);
        recycleradapter.notifyDataSetChanged();
        return view;
    }

    private void AmbilData() {
        SharedPreferences sharedPreferences=this.getActivity().getSharedPreferences("Secrets",MODE_PRIVATE);
        String currenttoken=sharedPreferences.getString("token","");

        //backend retrofit

        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl("https://ashokabackend.herokuapp.com/")//change it afterwards when everthing is hosted
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();
        ApiInterface apiInterface=retrofit.create(ApiInterface.class);
        Call<Getallpoolsformat> call=apiInterface.getallpools(currenttoken);
        call.enqueue(new Callback<Getallpoolsformat>() {
            @Override
            public void onResponse(Call<Getallpoolsformat> call, Response<Getallpoolsformat> response) {
                if(response.isSuccessful())
                {
                    List<Pool> allpool=response.body().getPools();
                    for(int i=0;i<allpool.size();i++)
                    {
                        String pname=allpool.get(i).getName();
                        String poolid=allpool.get(i).getId();
                        String poolengineerid=allpool.get(i).getEngineerId();
                        String plocation=allpool.get(i).getLocation();
                        String ptotinvests= String.valueOf(allpool.get(i).getTotalInvestment());
                        String profits= String.valueOf(allpool.get(i).getPrevProfits());
                        listdata.add(new poolitems(poolid,poolengineerid,pname,ptotinvests,plocation,"",profits));
                        recycleradapter.notifyDataSetChanged();
                    }
                    recycleradapter = new poolAdapter(getActivity(),listdata);
                    mRecyclerView.setAdapter(recycleradapter);
                }
                else
                {
                    Toast.makeText(getContext(), "Error:"+response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Getallpoolsformat> call, Throwable t) {
                Toast.makeText(getContext(), "Error:"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //mpoollist=new ArrayList<>();

    }

}
