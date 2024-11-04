package com.example.campusbite.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.campusbite.Domain.Item;
import com.example.campusbite.R;

import java.util.ArrayList;
import java.util.List;

public class HistAdapter extends RecyclerView.Adapter<HistAdapter.ViewHolder> {
    Context context;
    ArrayList<Item> items;

    public HistAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public HistAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_histcart,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistAdapter.ViewHolder holder, int position) {

        holder.hist_title.setText(items.get(position).getOh_title());
        holder.hist_ech_itm_fee.setText("₹"+ items.get(position).getOh_itemFee());

        holder.hist_totalFee.setText("₹"+items.get(position).getOh_totalFee());
        holder.hist_date.setText(items.get(position).getOh_date());
        holder.hist_time.setText(items.get(position).getOh_time());

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(items.get(position).getOh_pic(),"drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.hist_pic);

        holder.hist_no_of_items.setText(items.get(position).getOg_itemNo());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView hist_pic;
        TextView hist_title,hist_ech_itm_fee,hist_no_of_items,hist_totalFee,hist_date,hist_time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            hist_pic=itemView.findViewById(R.id.hist_pic);
            hist_title=itemView.findViewById(R.id.hist_title);
            hist_ech_itm_fee=itemView.findViewById(R.id.hist_ech_itm_fee);
            hist_no_of_items=itemView.findViewById(R.id.hist_no_of_items);
            hist_totalFee=itemView.findViewById(R.id.hist_totalFee);
            hist_date=itemView.findViewById(R.id.hist_date);
            hist_time=itemView.findViewById(R.id.hist_time);

        }
    }
}
