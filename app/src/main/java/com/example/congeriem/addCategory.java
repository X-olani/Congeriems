package com.example.congeriem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class addCategory extends AppCompatActivity {
    public ArrayList<Categories> arrayCategor =new ArrayList<Categories>();
    Button btnAddCategory ;
    TextView txtCategory,txtGoal ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
//just a commit
        btnAddCategory = (Button) findViewById(R.id.btnAdd);
        txtCategory =(TextView) findViewById(R.id.txtCategory);
        txtGoal=(TextView) findViewById(R.id.txtGoal);
// button add to category class
        btnAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //passing variable to show category view
                String name=txtCategory.getText().toString();
                int goal= Integer.parseInt( txtGoal.getText().toString());

                Intent op = new Intent(addCategory.this,ShowCategories.class);

                //setting edit value to -1 because item is not being edited
                op.putExtra("edit",-1);
                op.putExtra("Category",name);
                op.putExtra("GOAL",goal);
                if (isTextFulled(name,goal)== true){

                    startActivity(op);
                }

            };
        });


    }
    public Boolean isTextFulled(String category, int goal ){
        Boolean login=false;
String g = Integer.toString(goal);

        if (category.trim().equals("") & g.equals("0")){

            Toast.makeText(addCategory.this,"Input Fields Empty",Toast.LENGTH_LONG).show();

        }else {
            login = true;
        }
        return login;
    }
}