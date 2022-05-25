package com.example.congeriem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class addCategory extends AppCompatActivity {
    GlobalVariables globalVariables=(GlobalVariables) this.getApplication();
List<Categories>categoriesList;
    Button btnAddCategory ;
    TextView txtCategory,txtGoal ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        btnAddCategory = (Button) findViewById(R.id.btnAdd);
        txtCategory =(TextView) findViewById(R.id.txtCategory);
        txtGoal=(TextView) findViewById(R.id.txtGoal);
        categoriesList=globalVariables.getCategoryList();

// button add to category class
        btnAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
int createdID=generateID(categoriesList);

                //passing variable to show category view
                String name=txtCategory.getText().toString();
                int goal= Integer.parseInt( txtGoal.getText().toString());
                Categories c= new Categories(createdID,name,goal);
                categoriesList.add(c);
                Intent op = new Intent(addCategory.this,ShowCategories.class);

                if (isTextFulled(name,goal)== true){

                    startActivity(op);
                }

            };
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
    public int generateID (List< Categories > categoriesList) {
        int max = 0;
        int createId = 0;
        //if the array list is empty make the id 1
        if (categoriesList.size() == 0) {


            createId = 0;
        } else {
            for (int x = 0; x < categoriesList.size(); x++) {

                if (categoriesList.get(x).getID() > categoriesList.get(max).getID()) {
                    max = x;
                }


            }
            createId = categoriesList.get(max).getID();
            createId = createId + 1;
        }
        return createId;
    }

}