package com.example.congeriem;

import android.app.Application;

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
}
