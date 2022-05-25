package com.example.congeriem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class ShowCategories extends AppCompatActivity {
    GlobalVariables globalVariables=(GlobalVariables) this.getApplication();
    List<Categories> arrayCategory;
    CategoryAdapter adapter;
    CardView createCategory;
    Button nextWindow;
    RecyclerView rvDisplay;



    private  RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_categories);

        // createing a connection to the recycle view and displaying data
        arrayCategory = globalVariables.getCategoryList();

        rvDisplay = findViewById(R.id.ListView);

        rvDisplay.setHasFixedSize(true);


        layoutManager = new LinearLayoutManager(this);
        rvDisplay.setLayoutManager(layoutManager);

        adapter = new CategoryAdapter(arrayCategory, this);
        rvDisplay.setAdapter(adapter);


        nextWindow = (Button) findViewById(R.id.btnNext);

        nextWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent op = new Intent(ShowCategories.this, MainActivity.class);
                startActivity(op);
            }
        });




    }



}