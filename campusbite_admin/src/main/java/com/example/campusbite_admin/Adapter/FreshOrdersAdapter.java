package com.example.campusbite_admin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.campusbite_admin.Domains.FreshItems;
import com.example.campusbite_admin.R;

import java.util.ArrayList;

public class FreshOrdersAdapter extends RecyclerView.Adapter<FreshOrdersAdapter.ViewHolder> {
    Context context;
    ArrayList<FreshItems> items;


    public FreshOrdersAdapter(Context context, ArrayList<FreshItems> items) {
        this.context = context;
        this.items = items;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fresh_orders_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FreshOrdersAdapter.ViewHolder holder, int position) {

        holder.fl_title.setText(items.get(position).getOh_title());
        holder.fl_ech_itm_fee.setText("₹"+ items.get(position).getOh_itemFee());

        holder.fl_totalFee.setText("₹"+items.get(position).getOh_totalFee());
        holder.fl_date.setText(items.get(position).getOh_date());
        holder.fl_time.setText(items.get(position).getOh_time());

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(items.get(position).getOh_pic(),"drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.fl_pic);

        holder.fl_no_of_items.setText(items.get(position).getOg_itemNo());
        holder.fl2_no_of_items.setText(items.get(position).getOg_itemNo());
        holder.fl_customerName.setText(items.get(position).getCustomerName());

    }

    @Override
    public int getItemCount() {

        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView fl_pic;
        TextView fl_title,fl_ech_itm_fee,fl_no_of_items,fl2_no_of_items,fl_totalFee,fl_date,fl_time,fl_customerName;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            fl_pic = itemView.findViewById(R.id.fl_pic);
            fl_title = itemView.findViewById(R.id.fl_title);
            fl_ech_itm_fee = itemView.findViewById(R.id.fl_ech_itm_fee);
            fl_no_of_items = itemView.findViewById(R.id.fl_no_of_items);
            fl2_no_of_items = itemView.findViewById(R.id.fl2_no_of_items);
            fl_totalFee = itemView.findViewById(R.id.fl_totalFee);
            fl_date = itemView.findViewById(R.id.fl_date);
            fl_time = itemView.findViewById(R.id.fl_time);
            fl_customerName = itemView.findViewById(R.id.fl_customerName);


        }
    }
}
