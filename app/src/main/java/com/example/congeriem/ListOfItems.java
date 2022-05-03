package com.example.congeriem;

import java.util.ArrayList;
import java.util.List;

public class ListOfItems {
    List<Items> myItemList;


    public  ListOfItems(){

        this.myItemList= new ArrayList<>();




    }
    public List<Items> getMyItemList() {
        return myItemList;
    }




    public void setMycategoriesList(List<Items> myItemList) {
        this.myItemList = myItemList;
    }

}
