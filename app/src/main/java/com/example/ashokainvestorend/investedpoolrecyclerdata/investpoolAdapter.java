package com.example.ashokainvestorend.investedpoolrecyclerdata;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ashokainvestorend.R;
import com.example.ashokainvestorend.invpoolexpand;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class investpoolAdapter extends RecyclerView.Adapter<investpoolAdapter.MyViewHolder>{

    Context mContext;
    public static final String EXTRA_URL="imageurl";
    public static final String EXTRA_NAME="user";
    public static final String EXTRA_LOCATION="views";
    public static final String EXTRA_PROFIT="views";
    //added extra datas for manipulating later(report is not initialised currently)
    public static final String EXTRA_REPORT="imageurl";
    public  static final String EXTRA_AREA="imageHeight";
    private ArrayList<invespoolitems> mpoolitemslist;

    public investpoolAdapter(Context mContext, ArrayList<invespoolitems> mpoolitemslist) {
        this.mContext = mContext;
        this.mpoolitemslist = mpoolitemslist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;

        //change the views as you want by just changing the layout single applist
        v= LayoutInflater.from(mContext).inflate(R.layout.applist_item,parent,false);
        MyViewHolder vHolder=new MyViewHolder(v);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {


        holder.mpoolname.setText(mpoolitemslist.get(position).getPoolname());
        holder.mprofit.setText(mpoolitemslist.get(position).getProfit());
        holder.marea.setText(mpoolitemslist.get(position).getArea());
        holder.mlocation.setText(mpoolitemslist.get(position).getLocation());
        //holder set imageview for now there is default set(USE PICASSO METHOD TO LOAD IMAGES)
        //Picasso.with(this).load().into();


        //Click listener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                invespoolitems m=mpoolitemslist.get(position);
                String pname=m.getPoolname();
                String plocation=m.getLocation();
                String preport=m.getReport();
                String parea=m.getArea();
                String pprofit=m.getProfit();
                String pimage=m.getmImageurl();
                Toast.makeText(mContext, "entered inpoolexpand"+pname, Toast.LENGTH_SHORT).show();
                Intent i=new Intent(view.getContext(), invpoolexpand.class);
                i.putExtra(EXTRA_URL,pimage);
                i.putExtra(EXTRA_NAME,pname);
                i.putExtra(EXTRA_AREA,parea);
                i.putExtra(EXTRA_LOCATION,plocation);
                i.putExtra(EXTRA_REPORT,preport);
                i.putExtra(EXTRA_PROFIT,pprofit);
                view.getContext().startActivity(i);
            }
        });
        //Click listener ends

    }

    @Override
    public int getItemCount() {
        return mpoolitemslist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        public CircleImageView mimageView;
        public TextView mpoolname;
        public TextView mlocation;
        public TextView marea;
        public TextView mprofit;
        //public TextView mreport;
        //public TextView mextraattribute;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mimageView=itemView.findViewById(R.id.poolimage);
            mpoolname=itemView.findViewById(R.id.pname);
            mlocation=itemView.findViewById(R.id.pooladdresstxt);
            marea=itemView.findViewById(R.id.poolarea);
            mprofit=itemView.findViewById(R.id.poolprofit);

        }
    }

}
