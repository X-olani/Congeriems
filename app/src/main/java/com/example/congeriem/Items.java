package com.example.congeriem;

public class Items {

    private String ID;
    private String Items;
    private int Price;
    private String CategoryID;
    private String  Date;
    private String Url;

    public Items() {
    }

    public Items(String id, String items, int price, String categoryID, String date, String url) {

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

    public String getID() {
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

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setItems(String items) {
        Items = items;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public void setCategoryID(String categoryID) {
        CategoryID = categoryID;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
