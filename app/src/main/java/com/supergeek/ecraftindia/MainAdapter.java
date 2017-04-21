package com.supergeek.ecraftindia;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

/**
 * Created by Junejas on 4/21/2017.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {
    ArrayList<ModelClass> data;
    Context context;
    String rs;
    public MainAdapter(Context context,ArrayList<ModelClass> data){
        this.context=context;
        this.data=data;
        rs=context.getResources().getString(R.string.Rs);
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e("abcde","p");
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemlayout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ModelClass modelClass=data.get(position);
        holder.price.setText(rs+" "+String.valueOf(modelClass.price));
        holder.name.setText(modelClass.name);
        Glide.with(context).load(modelClass.getImage())
                .thumbnail(0.1f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name,price;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.image);
            name=(TextView)itemView.findViewById(R.id.name);
            price=(TextView)itemView.findViewById(R.id.price);
        }
    }
}
