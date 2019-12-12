package com.example.ashokainvestorend.particulartransactionmodels;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ashokainvestorend.R;

import java.util.ArrayList;


public class transactionAdapter extends RecyclerView.Adapter<transactionAdapter.MyViewHolder>{

    Context mContext;
    private ArrayList<transactionitem> mtransactionitemlist;


    public transactionAdapter(Context mContext, ArrayList<transactionitem> mtransactionitemlist) {
        this.mContext = mContext;
        this.mtransactionitemlist = mtransactionitemlist;
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


        holder.mtdate.setText(mtransactionitemlist.get(position).getTimedate());
        holder.mtamount.setText(mtransactionitemlist.get(position).getAmount());
    }

    @Override
    public int getItemCount()

    {
        return mtransactionitemlist.size();
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
