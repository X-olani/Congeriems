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

public class ItemAdapter extends BaseAdapter {

    Activity mActivity;
    ListOfItems myList;

    public ItemAdapter(Activity mActivity, ListOfItems myList) {
        this.mActivity = mActivity;
        this.myList = myList;
    }

    @Override
    public int getCount() {
        return myList.getMyItemList().size();
    }

    @Override
    public Items getItem(int i) {
        return myList.getMyItemList().get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        View currentItemView ;

        LayoutInflater inflater= (LayoutInflater)mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        currentItemView= inflater.inflate(R.layout.item_custom_layout,parent,false);

        // get the position of the view from the ArrayAdapter
        Items item = this.getItem(position);


        // then according to the position of the view assign the desired TextView 1 for the same
        TextView displayItem = currentItemView.findViewById(R.id.txtDisplay);
        TextView displayCateory=currentItemView.findViewById(R.id.txtCategory);
        TextView displayDate= currentItemView.findViewById(R.id.txtDate);
        displayCateory.setText("Category:"+item.getCategoryID());
        displayDate.setText("Date:"+item.getDate());
        displayItem.setText(item.getItems());



        // then return the recyclable view
        return currentItemView;


    }
}
