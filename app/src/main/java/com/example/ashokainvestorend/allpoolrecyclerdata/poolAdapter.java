package com.example.ashokainvestorend.allpoolrecyclerdata;

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
import com.example.ashokainvestorend.apoolexpand_activity;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class poolAdapter extends RecyclerView.Adapter<poolAdapter.MyViewHolder>{

    Context mContext;
    public static final String EXTRA_URL="imageurl";
    public static final String EXTRA_NAME="user";
    public static final String EXTRA_LOCATION="views";
    public static final String EXTRA_PROFIT="views";
    //added extra datas for manipulating later(report is not initialised currently)
    public static final String EXTRA_REPORT="imageurl";
    public  static final String EXTRA_AREA="imageHeight";
    private ArrayList<poolitems> mpoolitemslist;
  // private onitemclicklistener mListener;
//click listener for items on recycler
    public interface onitemclicklistener{
        void onItemClick(int position);
    }
   /* public void setOnItemClickListener(onitemclicklistener listener){
        mListener=listener;
    }*/

    public poolAdapter(Context mContext, ArrayList<poolitems> mpoolitemslist) {
        this.mContext = mContext;
        this.mpoolitemslist = mpoolitemslist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(mContext).inflate(R.layout.applist_item,parent,false);
        MyViewHolder vHolder=new MyViewHolder(v);
        return vHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {


        holder.mpoolname.setText(mpoolitemslist.get(position).getPoolname());
        holder.mprofit.setText(mpoolitemslist.get(position).getPrevprofit());
        holder.mtotinvestments.setText(mpoolitemslist.get(position).getTotalinvests());
        holder.mlocation.setText(mpoolitemslist.get(position).getLocation());
         //holder set imageview for now there is default set(USE PICASSO METHOD TO LOAD IMAGES)
        //Picasso.with(this).load().into();



        //CLICK LISTENER FOR EVERY ITEM ON RECYCLER VIEW OF ALLPOOLS
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                poolitems m=mpoolitemslist.get(position);
                String pname=m.getPoolname();
                String plocation=m.getLocation();
                String preport=m.getReport();
                String ptotinvest=m.getTotalinvests();
                String pprofit=m.getPrevprofit();
                //String pimage=m.getmImageurl();
                Toast.makeText(mContext, "entered apoolexpand"+pname, Toast.LENGTH_SHORT).show();
                Intent i=new Intent(view.getContext(), apoolexpand_activity.class);
              /*  i.putExtra(EXTRA_URL,pimage);
                i.putExtra(EXTRA_NAME,pname);
                i.putExtra(EXTRA_AREA,parea);
                i.putExtra(EXTRA_LOCATION,plocation);
                i.putExtra(EXTRA_REPORT,preport);
                i.putExtra(EXTRA_PROFIT,pprofit);*/
                view.getContext().startActivity(i);




            }
        });

        //CLICK LISTENER ENDS
    }

    @Override
    public int getItemCount()

    {
        return mpoolitemslist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private Context context;
        public CircleImageView mimageView;
        public TextView mpoolname;
        public TextView mlocation;
        public TextView mtotinvestments;
        public TextView mprofit;
        //public TextView mextraattribute;
        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            context = itemView.getContext();
            mimageView=itemView.findViewById(R.id.poolimage);
            mpoolname=itemView.findViewById(R.id.pname);
            mlocation=itemView.findViewById(R.id.pooladdresstxt);
            mtotinvestments=itemView.findViewById(R.id.poolarea);
            mprofit=itemView.findViewById(R.id.poolprofit);

           /* itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(mListener!=null)
                    {
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION)
                        {

                            mListener.onItemClick(position);

                        }
                    }
                }
            });*/


        }
    }

}
