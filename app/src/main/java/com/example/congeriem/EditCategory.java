package com.example.congeriem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;

public class EditCategory extends AppCompatActivity {
    GlobalVariables globalVariables=(GlobalVariables) this.getApplication();
    List<Categories> categoriesList;
    EditText edtCategory,edtGola;
    Button btnCancel, btnDone, btnDelete;
    int editposition=-1;
    String categoryID;
    DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_category);
        Bundle newData= getIntent().getExtras();
        edtCategory= (EditText) findViewById(R.id.edtCategory);
        edtGola=(EditText) findViewById(R.id.edtGoal);
        btnCancel= (Button) findViewById(R.id.btnCancel);
        btnDone = (Button) findViewById(R.id.btnDone);
        btnDelete=(Button) findViewById(R.id.btnDelete) ;
        categoriesList= globalVariables.getCategoryList();
        db= FirebaseDatabase.getInstance().getReference();
        if(newData!=null){

            // setting the values
            edtCategory.setText(newData.getString("category"));
            edtGola.setText(Integer.toString( newData.getInt("goal")));
            categoryID= newData.getString("id");




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

                String categoryName=edtCategory.getText().toString();
                int goal=Integer.parseInt(edtGola.getText().toString());

                // updating the category
 HashMap hashMap= new HashMap();
 hashMap.put("category",categoryName);
         hashMap.put("goal",goal);
                db.child("categories").child(categoryID).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(EditCategory.this, "Updated", Toast.LENGTH_LONG).show();
                        }
                    }
                });
               Intent i = new Intent(getApplicationContext(),ShowCategories.class);



                startActivity(i);
            }
        });
btnDelete.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        db.child("categories").child(categoryID).setValue(null).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                    Toast.makeText(EditCategory.this, "Deleted", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
});

    }

}