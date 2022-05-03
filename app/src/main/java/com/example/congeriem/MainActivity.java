package com.example.congeriem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    CardView createCategory, viewCategory ,showItem;
    public ArrayList newArray ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewCategory=(CardView) findViewById(R.id.cardView);
        createCategory=(CardView) findViewById(R.id.cardView2);
        showItem=(CardView) findViewById(R.id.cardView3);
        viewCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,ShowCategories.class);
                startActivity(i);
            }
        });
        createCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, addCategory.class);
                startActivity(i);
            }
        });
        showItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ShowItems.class);
                startActivity(i);
            }
        });

    }
}