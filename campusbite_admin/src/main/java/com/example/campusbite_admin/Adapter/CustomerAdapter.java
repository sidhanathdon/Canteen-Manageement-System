package com.example.campusbite_admin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.campusbite_admin.Domains.Customers;
import com.example.campusbite_admin.R;

import java.util.ArrayList;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> {

    Context context;
    ArrayList<Customers> customers;
    public CustomerAdapter(Context context, ArrayList<Customers> customers) {
        this.context = context;
        this.customers = customers;
    }

    @NonNull
    @Override
    public CustomerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_customer_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerAdapter.ViewHolder holder, int position) {

        holder.cl_name.setText(customers.get(position).getFullName());
        holder.cl_email.setText(customers.get(position).getEmail());
        holder.cl_pNo.setText(customers.get(position).getMobile());
        holder.cl_id.setText(customers.get(position).getId());



    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView cl_name,cl_email,cl_pNo,cl_id;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cl_name= itemView.findViewById(R.id.cl_name);
            cl_email= itemView.findViewById(R.id.cl_email);
            cl_pNo= itemView.findViewById(R.id.cl_pNo);
            cl_id=itemView.findViewById(R.id.cl_id);




        }
    }
}
