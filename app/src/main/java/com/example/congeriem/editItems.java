package com.example.congeriem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class editItems extends AppCompatActivity {
    GlobalVariables globalVariables=(GlobalVariables) this.getApplication();
    EditText edtItem,edtPrice;
    Button btnDelete, btnDone;
    TextView edtDate;
    Spinner edtCategoryID;
    DatabaseReference db;
    //class to connect to the sql database
    DataBaseHelper dataBaseHelper = new DataBaseHelper(editItems.this);
    String itemID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_items);

        Bundle newData= getIntent().getExtras();
        edtItem= (EditText) findViewById(R.id.edtItem);
        edtPrice=(EditText) findViewById(R.id.edtPrice);
        edtDate=(TextView) findViewById(R.id.txtDate);
        edtCategoryID=(Spinner) findViewById(R.id.spinner);
        btnDelete= (Button) findViewById(R.id.btnDelete);
        btnDone = (Button) findViewById(R.id.btnDone);
        db= FirebaseDatabase.getInstance().getReference();
        if(newData!=null){

            // setting the values
            itemID=  newData.getString("itemID");
            edtItem.setText(newData.getString("item"));
            edtPrice.setText(Integer.toString(newData.getInt("price")));
            edtDate.setText(newData.getString("date"));

            // adding the category into the spinner using a list

            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,globalVariables.getDropDownList());
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            edtCategoryID.setAdapter(spinnerAdapter);

btnDelete.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        // find the right object to delete by finding the id
        db.child("items").child(itemID).setValue(null).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                    Toast.makeText(editItems.this, "Deleted", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
});


        }
    }
}