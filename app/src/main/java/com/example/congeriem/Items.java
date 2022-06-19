package com.example.congeriem;

public class Items {

    private int ID;
    private String Items;
    private int Price;
    private String CategoryID;
    private String  Date;
    private String Url;

    public Items(int id,String items, int price,String categoryID, String date, String url) {

        Items = items;
        Price= price;
        CategoryID = categoryID;
        Date=date;
        ID=id;
        Url=url;

    }

    public String getUrl() {
        return Url;
    }

    public int getID() {
        return ID;
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

    @Override
    public String toString() {
        return  Items ;

    }
}
