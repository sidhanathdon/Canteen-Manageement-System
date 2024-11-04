package com.example.campusbite.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.campusbite.R;

public class HistViewHolder extends RecyclerView.ViewHolder {
    ImageView hist_pic;
    TextView hist_title,hist_ech_itm_fee,hist_no_of_items,hist_totalFee,hist_date,hist_time;
    public HistViewHolder(@NonNull View itemView) {
        super(itemView);
        hist_pic=itemView.findViewById(R.id.hist_pic2);
        hist_title=itemView.findViewById(R.id.hist_title2);
        hist_ech_itm_fee=itemView.findViewById(R.id.hist_ech_itm_fee2);
        hist_no_of_items=itemView.findViewById(R.id.hist_no_of_items2);
        hist_totalFee=itemView.findViewById(R.id.hist_totalFee2);
        hist_date=itemView.findViewById(R.id.hist_date2);
        hist_time=itemView.findViewById(R.id.hist_time2);
    }
}
