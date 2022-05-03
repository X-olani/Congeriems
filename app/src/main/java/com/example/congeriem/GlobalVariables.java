package com.example.congeriem;

import android.app.Application;

public class GlobalVariables  extends Application {
    private  ListCategory listCat= new ListCategory();
    private  ListOfItems listItem= new ListOfItems();

    public ListCategory getListCat() {
        return listCat;
    }


    public void setListCat(ListCategory listCat) {
        this.listCat = listCat;

    } public ListOfItems getListItem() {
        return listItem;
    }

    public void setListItem(ListOfItems listItem) {
        this.listItem = listItem;
    }
}
