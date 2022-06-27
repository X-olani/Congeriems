package com.example.congeriem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;

public class ShowItems extends AppCompatActivity {
    GlobalVariables globalVariables=(GlobalVariables) this.getApplication();
    List<Items> itemsList;

    Button btnBack;
    RecyclerView rvDisplay;
    ItemAdapter adapter;

    DataBaseHelper dataBaseHelper = new DataBaseHelper( ShowItems.this);
    DatabaseReference db;

    private  RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_items);
        btnBack =(Button)findViewById(R.id.btnBack) ;


        // displaying item on list
        rvDisplay=findViewById(R.id.ListView2);
        itemsList= globalVariables.getItemList();

        rvDisplay.setHasFixedSize(true);


        layoutManager = new LinearLayoutManager(this);
        rvDisplay.setLayoutManager(layoutManager);

     dataBaseHelper = new DataBaseHelper( ShowItems.this);
        List<Items> allItems = dataBaseHelper.getAllItems();


        adapter=new ItemAdapter(allItems,this);
        rvDisplay.setAdapter(adapter);
        Bundle newData = getIntent().getExtras();

        if(newData!= null){

            String getCategory= newData.getString("category");


            int id= newData.getInt("id");
           theFilter(getCategory);

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
    public void theFilter (String filterBy){

        List<Items> filterList = new ArrayList<Items>();



                for (Items sub:itemsList){
                    Log.i("T","HERE "+sub.getCategoryID());

           if(sub.getCategoryID().toLowerCase().contains(filterBy)){

                filterList.add(sub);
            }

                }
                adapter=new ItemAdapter(filterList,ShowItems.this);
                rvDisplay.setAdapter(adapter);

            }



}