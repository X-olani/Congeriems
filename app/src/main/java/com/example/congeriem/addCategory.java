package com.example.congeriem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class addCategory extends AppCompatActivity {
    GlobalVariables globalVariables=(GlobalVariables) this.getApplication();
List<Categories>categoriesList;
    Button btnAddCategory ;
    TextView txtCategory,txtGoal ;
     DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        btnAddCategory = (Button) findViewById(R.id.btnAdd);
        txtCategory =(TextView) findViewById(R.id.txtCategory);
        txtGoal=(TextView) findViewById(R.id.txtGoal);
        categoriesList=globalVariables.getCategoryList();
        db= FirebaseDatabase.getInstance().getReference();

// button add to category class
        btnAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //passing variable to show category view
                String name=txtCategory.getText().toString();
                int goal= Integer.parseInt( txtGoal.getText().toString());

               // categoriesList.add(c);
                Intent op = new Intent(addCategory.this,ShowCategories.class);

                if (isTextFulled(name,goal)== true){

                    addCategory();


                   // startActivity(op);
                }

            };
        });


    }

    private void addCategory() {
        String name = txtCategory.getText().toString();
        int goal = Integer.parseInt(txtGoal.getText().toString());

        String id = db.push().getKey();
        Categories c = new Categories(id, name, goal);
        db.child("categories").child(id).setValue(c).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                    Toast.makeText(addCategory.this, "Created", Toast.LENGTH_LONG).show();
                }
            }


        });
    }


    public Boolean isTextFulled(String category, int goal ){
        Boolean status=false;
String g = Integer.toString(goal);

        if (category.trim().equals("") & g.equals("0")){

            Toast.makeText(addCategory.this,"Input Fields Empty",Toast.LENGTH_LONG).show();

        }else {
            status = true;
        }
        return status;
    }
    // generate ID


}