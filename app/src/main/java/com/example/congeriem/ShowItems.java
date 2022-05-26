package com.example.congeriem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class ShowItems extends AppCompatActivity {
    GlobalVariables globalVariables=(GlobalVariables) this.getApplication();
    List<Items> itemsList;

    Button btnBack;
    RecyclerView rvDisplay;
    ItemAdapter adapter;

    //ListOfItems listTheItem;
    private RecyclerView.LayoutManager layoutManager;

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


        adapter=new ItemAdapter(itemsList,this);
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

        for (Items sub: itemsList){
            if(sub.getCategoryID().toLowerCase().contains(filterBy)){

                filterList.add(sub);
            }

        }
        adapter=new ItemAdapter(filterList,this);
        rvDisplay.setAdapter(adapter);
    };
}