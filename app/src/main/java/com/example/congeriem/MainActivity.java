package com.example.congeriem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    CardView createCategory, viewCategory ,showItem;
    GlobalVariables globalVariables=(GlobalVariables) this.getApplication();
    public ArrayList newArray ;
    DatabaseReference db;
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
                Intent i = new Intent(MainActivity.this, achievements.class);
                startActivity(i);
            }
        });
        getAllItems();

    }
    private void getAllItems(){


        db= FirebaseDatabase.getInstance().getReference("items");
        List<Items> itemsList= new ArrayList<>();


        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data:snapshot.getChildren()){
                    Items item= data.getValue(Items.class);
                    itemsList.add(item);
                    Log.i("Items","LOOk"+itemsList);
                }
                globalVariables.setItemList(itemsList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}