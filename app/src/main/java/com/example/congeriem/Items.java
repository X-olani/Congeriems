package com.example.congeriem;

public class Items {

    private String Items;
    private int Price;
    private String CategoryID;
    private String  Date;

    public Items( String items, int price,String categoryID, String date) {

        Items = items;
        Price= price;
        CategoryID = categoryID;
        Date=date;

    }



    public String getItems() {
        return Items;
    }

    public int getPrice() {
        return Price;
    }

    public String getDate() {
        return Date;
    }

    public String getCategoryID() {
        return CategoryID;
    }
}
