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
import com.example.campusbite.Helper.ManagementCart;
import com.example.campusbite.Interface.ChangeNumberItemsListner;
import com.example.campusbite.R;
import com.example.campusbite.Domain.foodDomain;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    ArrayList<foodDomain> listFoodSelected ;
    private ManagementCart managementCart;
    ChangeNumberItemsListner changeNumberItemsListner;

    public CartAdapter(ArrayList<foodDomain> listFoodSelected , Context context, ChangeNumberItemsListner changeNumberItemsListner) {
        this.listFoodSelected =listFoodSelected;
        managementCart = new ManagementCart(context);
        this.changeNumberItemsListner =changeNumberItemsListner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_card,parent, false);

        return new ViewHolder(inflate2);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(listFoodSelected.get(position).getTitle());
        holder.feeEachItem.setText("₹"+listFoodSelected.get(position).getFee());
        holder.totalEachItem.setText("₹"+((listFoodSelected.get(position).getNumberInCart() * listFoodSelected.get(position).getFee())));
        holder.num.setText(String.valueOf(listFoodSelected.get(position).getNumberInCart()));


        int drawableResourceId = holder.itemView.getContext().getResources()
                .getIdentifier(listFoodSelected.get(position).getPic(),"drawable",
                        holder.itemView.getContext().getPackageName());



        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

        holder.plusbtn.setOnClickListener(v -> managementCart.plusNumberFoood(listFoodSelected, position, () -> {
            notifyDataSetChanged();
            changeNumberItemsListner.changed();
        }));

        holder.minusbtn.setOnClickListener(v -> managementCart.minusNumberFood(listFoodSelected, position, () -> {
            notifyDataSetChanged();
            changeNumberItemsListner.changed();
        }));
    }

    @Override
    public int getItemCount() {return listFoodSelected.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,feeEachItem;
        ImageView pic,plusbtn,minusbtn;
        TextView totalEachItem,num;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.vh_title);
            pic = itemView.findViewById(R.id.vh_pic);
            feeEachItem = itemView.findViewById(R.id.feeEachItem);
            totalEachItem = itemView.findViewById(R.id.totalEachItem);
            plusbtn = itemView.findViewById(R.id.plusCartBtn);
            minusbtn = itemView.findViewById(R.id.minusCardBtn);
            num = itemView.findViewById(R.id.numberItemTxt);


        }
    }
}
