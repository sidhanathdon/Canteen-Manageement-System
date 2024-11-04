package com.example.campusbite.Adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.campusbite.Activity.ShowDetailActivity;
import com.example.campusbite.R;

import com.example.campusbite.Domain.foodDomain;

import java.util.ArrayList;

public class RecomendedAdapter extends RecyclerView.Adapter<RecomendedAdapter.ViewHolder> {
    ArrayList<foodDomain> recomendedDomains;

    public RecomendedAdapter(ArrayList<foodDomain> recomendedDomains) {
        this.recomendedDomains =recomendedDomains;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_recomended,parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position){
        holder.title.setText(recomendedDomains.get(position).getTitle());
        holder.fee.setText("₹"+String.valueOf(recomendedDomains.get(position).getFee()));


        int drawableResourceId=holder.itemView.getContext().getResources()
                .getIdentifier(recomendedDomains.get(position).getPic(),"drawable",
                        holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);


        holder.reco_holder.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
            intent.putExtra("object",recomendedDomains.get(position));
            holder.itemView.getContext().startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {return recomendedDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,fee;
        ImageView pic;
        ImageView addBtn;
        ConstraintLayout reco_holder;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            reco_holder = itemView.findViewById(R.id.reco_holder);
            title = itemView.findViewById(R.id.title);
            pic = itemView.findViewById(R.id.vh_pic);
            fee = itemView.findViewById(R.id.fee);
            addBtn = itemView.findViewById(R.id.addBtn);

        }
    }
}
