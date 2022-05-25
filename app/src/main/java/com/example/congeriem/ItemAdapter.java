package com.example.congeriem;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {

 List<Items> itemsList;
 Context context;

    public ItemAdapter(List<Items> itemsList, Context context) {
        this.itemsList = itemsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_custom_layout,parent, false);
        MyViewHolder holder= new MyViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.MyViewHolder holder, final int position) {

        holder.displayCateory.setText("Category:"+itemsList.get(position).getCategoryID());
        holder.displayDate.setText("Date:"+itemsList.get(position).getDate());
        holder.displayItem.setText(itemsList.get(position).getItems());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }
    public  class  MyViewHolder extends  RecyclerView.ViewHolder{

        TextView displayItem;
        TextView displayCateory;
        TextView displayDate;
        ConstraintLayout parentLayout;

    public MyViewHolder(@NonNull View itemView){

        super(itemView);
         displayItem = itemView.findViewById(R.id.txtDisplay);
         displayCateory=itemView.findViewById(R.id.txtCategory);
         displayDate= itemView.findViewById(R.id.txtDate);
         parentLayout= itemView.findViewById(R.id.linearLayoutItems);

    }
    }
}
