package com.example.artemqa.bestlcd.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.artemqa.bestlcd.R;
import com.example.artemqa.bestlcd.ui.activity.RVItemActivity;
import com.example.artemqa.bestlcd.model.Monitor;
import com.example.artemqa.bestlcd.util.Helper;
import com.squareup.picasso.Picasso;

import io.realm.RealmResults;

/**
 * Created by artemqa on 14.01.2018.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ItemViewHolder> {
   private RealmResults<Monitor> items;
    public MyRecyclerViewAdapter (RealmResults<Monitor> items){
        this.items = items;
    }
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Context context = holder.imageView.getContext();
        holder.nameMonitor.setText(items.get(position).getName());
        holder.scoreAlg.setText(String.valueOf(items.get(position).getScoreAlgorithm()));
        holder.costMonitor.setText(String.valueOf(items.get(position).getCost()));
        Picasso.with(context).load(items.get(position).getUrlPicture()).resize(100,100 ).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView nameMonitor,costMonitor,scoreAlg;
        ImageView imageView;
        Context context;
        public ItemViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            imageView = itemView.findViewById(R.id.image_view_item_rv);
            nameMonitor = itemView.findViewById(R.id.tv_name_item_rv);
            scoreAlg = itemView.findViewById(R.id.tv_alg_score_item_rv);
            costMonitor = itemView.findViewById(R.id.tv_cost_item_rv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, RVItemActivity.class);
                    Bundle data = new Bundle();
                    data.putString(Helper.INTENT_RV_URL,nameMonitor.getText().toString());
                    intent.putExtras(data);
                    context.startActivity(intent);
                }
            });
        }
    }
}
