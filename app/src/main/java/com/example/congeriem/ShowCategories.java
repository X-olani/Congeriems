package com.example.congeriem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ShowCategories extends AppCompatActivity {
    GlobalVariables globalVariables=(GlobalVariables) this.getApplication();
    List<Categories> arrayCategory;
    CategoryAdapter adapter;
    CardView createCategory;
    Button nextWindow;
    RecyclerView rvDisplay;
    String key=null;

     DatabaseReference db;

    private  RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_categories);

        // createing a connection to the recycle view and displaying data
      //  arrayCategory = globalVariables.getCategoryList();

        rvDisplay = findViewById(R.id.ListView);

        rvDisplay.setHasFixedSize(true);


        layoutManager = new LinearLayoutManager(this);
        rvDisplay.setLayoutManager(layoutManager);

        DataBaseHelper dataBaseHelper = new DataBaseHelper( ShowCategories.this);
        List<Categories> allCategories = dataBaseHelper.getAllCategories();
        ShowCategories();

      //  rvDisplay.setAdapter(adapter);


        nextWindow = (Button) findViewById(R.id.btnNext);

        nextWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent op = new Intent(ShowCategories.this, MainActivity.class);
                startActivity(op);
            }
        });




    }

public void ShowCategories(){

    db= FirebaseDatabase.getInstance().getReference("categories");
    List <Categories> CategoryList= new ArrayList<>();
    List <String> dropdownList = new ArrayList<>();

    db.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for (DataSnapshot data:snapshot.getChildren()){
                Categories c= data.getValue(Categories.class);
                CategoryList.add(c);
            }
            globalVariables.setCategoryList(CategoryList);

            for( Categories i :CategoryList){
                dropdownList.add(i.getCategory());

            }
            globalVariables.setDropDownList(dropdownList);
            adapter=new CategoryAdapter(CategoryList,ShowCategories.this);
            rvDisplay.setAdapter(adapter);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });
}

}