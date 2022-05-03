package com.example.congeriem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowItems extends AppCompatActivity {
    Button btnBack;
    ListView lvDisplay;
    ItemAdapter adapter;


    ListOfItems listTheItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_items);

        // displaying item on list
        lvDisplay=findViewById(R.id.ListView2);
        listTheItem= ((GlobalVariables)this.getApplication()).getListItem();
        btnBack =(Button)findViewById(R.id.btnBack) ;

        adapter=new ItemAdapter(ShowItems.this,listTheItem);
        lvDisplay.setAdapter(adapter);
        Bundle newData = getIntent().getExtras();

        if(newData!= null){

            String getCategory= newData.getString("category");
            String getItem= newData.getString("item");
            int price= newData.getInt("price");
            String date= newData.getString("date");


            //if editposition is not -1 update list  and remove old item
            Items item= new Items(getItem,price,getCategory,date);
            listTheItem.getMyItemList().add(item);

            adapter.notifyDataSetChanged();
        }

        //button to back to the main page
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getApplicationContext(),ShowCategories.class);
                startActivity(i);
            }
        });
    }
}