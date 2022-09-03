package com.example.congeriem;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

// class the keeps data state after reload from page

public class GlobalVariables  extends Application {
    // global Array so that can be access everywhere
    private static List<Categories> categoryList = new ArrayList<>();
    private static List <Items> itemList= new ArrayList<>();
    private static  List<String>dropDownList= new ArrayList<>();


    public static List<String> getDropDownList() {
        return dropDownList;
    }

    public static void setDropDownList(List<String> dropDownList) {
        GlobalVariables.dropDownList = dropDownList;
    }

    public static List<Categories> getCategoryList() {
        return categoryList;
    }

    public static void setCategoryList(List<Categories> categoryList) {
        GlobalVariables.categoryList = categoryList;
    }

    public static List<Items> getItemList() {
        return itemList;
    }

    public static void setItemList(List<Items> itemList) {
        GlobalVariables.itemList = itemList;
    }


    // getting the data from firebase
    public  static void GetDataFireBaseCategory(){
        DatabaseReference db;
        categoryList= new ArrayList<>();
        dropDownList= new ArrayList<>();
        db= FirebaseDatabase.getInstance().getReference("categories");


        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data:snapshot.getChildren()){
                    Categories c= data.getValue(Categories.class);
                    categoryList.add(c);
                }


                for( Categories i :categoryList){
                    dropDownList.add(i.getCategory());

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    public static void GetDataFireBaseItems(){
        DatabaseReference db;
        itemList= new ArrayList<>();
        db= FirebaseDatabase.getInstance().getReference("items");
        List<Items> itemsList= new ArrayList<>();


        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data:snapshot.getChildren()){
                    Items item= data.getValue(Items.class);
                    itemList.add(item);
                    Log.i("Items","LOOk"+itemsList);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
