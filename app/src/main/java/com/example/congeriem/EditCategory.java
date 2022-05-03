package com.example.congeriem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditCategory extends AppCompatActivity {
    EditText edtCategory,edtGola;
    Button btnCancel, btnDone;
    int editposition=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_category);
        Bundle newData= getIntent().getExtras();
        edtCategory= (EditText) findViewById(R.id.edtCategory);
        edtGola=(EditText) findViewById(R.id.edtGoal);
        btnCancel= (Button) findViewById(R.id.btnCancel);
        btnDone = (Button) findViewById(R.id.btnDone);
        if(newData!=null){

            // setting the values
            edtCategory.setText(newData.getString("category"));
            edtGola.setText(Integer.toString( newData.getInt("goal")));
            editposition=newData.getInt("edit");
        }

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),ShowCategories.class);
                startActivity(i);
            }
        });
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),ShowCategories.class);
                String categoryName=edtCategory.getText().toString();
                int goal=Integer.parseInt(edtGola.getText().toString());


                i.putExtra("edit",editposition);
                i.putExtra("Category",categoryName);
                i.putExtra("GOAL",goal);

                startActivity(i);
            }
        });
    }
}