package com.example.congeriem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class ShowCategories extends AppCompatActivity {
    CardView createCategory;
    Button nextWindow;
    ListView lvDisplay;
    CategoryAdapter adapter;
    ListCategory listTheCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_categories);

        lvDisplay=findViewById(R.id.ListView);
        listTheCategory= ((GlobalVariables)this.getApplication()).getListCat();
        adapter=new CategoryAdapter(ShowCategories.this,listTheCategory);
        lvDisplay.setAdapter(adapter);
        Bundle newData = getIntent().getExtras();

        if(newData!= null){


            String getCategory= newData.getString("Category");
            int getGoal= newData.getInt("GOAL");
            int editPosition= newData.getInt("edit");
            Categories c= new Categories(getCategory,getGoal);

            //if editposition is not -1 update list  and remove old item
            if(editPosition>-1){
                listTheCategory.getMycategoriesList().remove(editPosition);
            }
            listTheCategory.getMycategoriesList().add(c);
            adapter.notifyDataSetChanged();
        }
        nextWindow= (Button) findViewById(R.id.btnNext);

        nextWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent op = new Intent(ShowCategories.this, MainActivity.class);
                startActivity(op);
            }
        });

        // holding in the category will open edit view
        lvDisplay.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {

                Intent i= new Intent(getApplicationContext(),EditCategory.class);
                Categories c=  listTheCategory.getMycategoriesList().get(position);

                i.putExtra("category",c.getCategory());
                i.putExtra("goal",c.getGoal());
                i.putExtra("edit",position);
                startActivity(i);
                return false;
            }


        });
        lvDisplay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent i= new Intent(getApplicationContext(),addItem.class);
                Categories c=  listTheCategory.getMycategoriesList().get(position);
                i.putExtra("category",c.getCategory());
                i.putExtra("goal",c.getGoal());

                startActivity(i);
            }
        });







    }
}