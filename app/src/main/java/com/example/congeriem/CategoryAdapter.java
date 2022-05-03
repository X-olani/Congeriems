package com.example.congeriem;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CategoryAdapter extends BaseAdapter {
    Activity mActivity;
    ListCategory myList;

    public CategoryAdapter(Activity mActivity, ListCategory myList) {
        this.mActivity = mActivity;
        this.myList = myList;
    }

    @Override
    public int getCount() {
        return myList.getMycategoriesList().size();
    }

    @Override
    public Categories getItem(int i) {
        return myList.getMycategoriesList().get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // convertView which is recyclable view
        View currentItemView ;

        LayoutInflater inflater= (LayoutInflater)mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        currentItemView= inflater.inflate(R.layout.custom_layout,parent,false);

        // get the position of the view from the ArrayAdapter
        Categories category = this.getItem(position);


        // then according to the position of the view assign the desired TextView 1 for the same
        TextView displayCat = currentItemView.findViewById(R.id.txtDisplay);
        displayCat.setText(category.getCategory());



        // then return the recyclable view
        return currentItemView;
    }


}
