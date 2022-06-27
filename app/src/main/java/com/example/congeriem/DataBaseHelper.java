package com.example.congeriem;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    // Category variables
    public  static  final String CATEGORIES="TBL_CATEGORIES";
    public  static  final String CATEGORY="CATEGORY";
    public  static  final String GOAL="GOAL";
    public  static  final String ID="ID";

    // items  variables
    public  static  final String ITEMS="TBL_ITEM";
    public  static  final String ITEM="ITEM";
    public  static  final String PRICE="PRICE";
    public  static  final String CATEGORY_ID="CATEGORY_ID";
    public  static  final String DATE="DATE";
    public  static  final String URL="URL";



    public DataBaseHelper(@Nullable Context context) {
        super(context,"collection.db",null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable="CREATE TABLE "+ CATEGORIES + " ("+ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+ CATEGORY +" TEXT,"+ GOAL +" TEXT)";

        String newcreateTable="CREATE TABLE "+ ITEMS + " ("+ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+ ITEM +" TEXT,"+ PRICE +" INTEGER,"+ CATEGORY_ID +" TEXT,"+ DATE +" TEXT,"+ URL +" URL)";
        db.execSQL(createTable);
        db.execSQL(newcreateTable);
    }


    // updates the tables when you a new colume
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
    public  boolean addOneCategory (Categories categories) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CATEGORY, categories.getCategory());
        cv.put(GOAL, categories.getGoal());

        long insert = db.insert(CATEGORIES, null, cv);

        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }
    public  boolean addOneItem (Items items) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ITEM, items.getItems());
        cv.put(PRICE, items.getPrice());
        cv.put(CATEGORY_ID, items.getCategoryID());
        cv.put(DATE,items.getDate());
        cv.put(URL,items.getUrl());

        long insert = db.insert(ITEMS, null, cv);

        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

// get all the data from database and store it into a list
public List<Categories> getAllCategories(){

        List<Categories> returnList = new ArrayList<>();


        String query ="SELECT * FROM "+ CATEGORIES;
        SQLiteDatabase db =this.getReadableDatabase();

        Cursor cursor= db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do{
                int categoryID = cursor.getInt(0);
                String category= cursor.getString(1);
                int goal = cursor.getInt(2);

//Categories newCategory = new Categories( categoryID,category,goal);
//returnList.add(newCategory);

            } while (cursor.moveToNext());
        }
        else{

        }
cursor.close();
        db.close();
        return returnList;

}
// get all the  data from the database
    public List<Items> getAllItems(){

        List<Items> returnList = new ArrayList<>();


        String query ="SELECT * FROM "+ ITEMS;
        SQLiteDatabase db =this.getReadableDatabase();

        Cursor cursor= db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do{
                String ItemID = cursor.getString(0);
                String item= cursor.getString(1);
                int price = cursor.getInt(2);
                String categoryID= cursor.getString(3);
                String date = cursor.getString(4);
                String url= cursor.getString(5);

                Items newItem = new Items(ItemID,item,price,categoryID,date,url);
                returnList.add(newItem);

            } while (cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return returnList;

    }
// delete one item
public boolean deleteItem( Items item){

        SQLiteDatabase db = this.getWritableDatabase();
        String query ="DELETE FROM "+ITEMS+ " WHERE "+ID+" = "+ item.getID();

    Cursor cursor = db.rawQuery(query, null);

    if(cursor.moveToFirst()){
        return true;
    }else {return false;}


}
}

