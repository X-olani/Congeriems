package com.example.congeriem;

import java.util.ArrayList;
import java.util.List;

public class ListCategory {
    List<Categories> mycategoriesList;


    public  ListCategory(){

        this.mycategoriesList= new ArrayList<>();




    }

    public List<Categories> getMycategoriesList() {
        return mycategoriesList;
    }

    public void setMycategoriesList(List<Categories> mycategoriesList) {
        this.mycategoriesList = mycategoriesList;
    }
}
