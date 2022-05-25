package com.example.congeriem;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
  List<Categories> categoriesList;
  Context context;

    public CategoryAdapter(List<Categories> categoriesList, Context context) {
        this.categoriesList = categoriesList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout,parent,false);
MyViewHolder holder= new MyViewHolder(view);
        return holder;
    }

    // binding the data to the view
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position ) {


holder.txtDisplay.setText(categoriesList.get(position).getCategory());

holder.parentLayout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent i = new Intent(context,ShowItems.class);
        i.putExtra("category",categoriesList.get(position).getCategory());

        context.startActivity(i);
    }
});

// holding the card will allow you to edit the category

 holder.parentLayout.setOnLongClickListener(new View.OnLongClickListener() {
     @Override
     public boolean onLongClick(View view) {
         Intent i= new Intent(context,EditCategory.class);


         i.putExtra("category",categoriesList.get(position).getCategory());
         i.putExtra("goal",categoriesList.get(position).getGoal());
         i.putExtra("id",categoriesList.get(position).getID());

         context.startActivity(i);
         return false;
     }
 });
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }


    public  class MyViewHolder extends  RecyclerView.ViewHolder{
        // input object assignment area

        TextView txtDisplay;
ConstraintLayout parentLayout;
        public  MyViewHolder(@NonNull View itemView){
            super(itemView);
            txtDisplay= itemView.findViewById(R.id.txtDisplay);
parentLayout= itemView.findViewById(R.id.linearLayoutCategory);
        }



    }
}
