package com.example.ashokainvestorend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class alltransactionadapter extends RecyclerView.Adapter<alltransactionadapter.MyViewHolder>{

    Context mContext;
    private ArrayList<alltransactionitem> malltransactionitemlist;


    public alltransactionadapter(Context mContext, ArrayList<alltransactionitem> malltransactionitemlist) {
        this.mContext = mContext;
        this.malltransactionitemlist = malltransactionitemlist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(mContext).inflate(R.layout.transaction_item,parent,false);
        MyViewHolder vHolder=new MyViewHolder(v);
        return vHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {


        holder.mtdate.setText(malltransactionitemlist.get(position).getTimedate());
        holder.mtamount.setText(malltransactionitemlist.get(position).getAmount());
    }

    @Override
    public int getItemCount()

    {
        return malltransactionitemlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private Context context;

        public TextView mtdate;
        public TextView mtamount;
        //public TextView mextraattribute;
        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            context = itemView.getContext();
            mtdate=itemView.findViewById(R.id.transactdate);
            mtamount=itemView.findViewById(R.id.transactamt);

        }
    }

}

