package com.example.congeriem;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class addItem extends AppCompatActivity {
    GlobalVariables globalVariables=(GlobalVariables) this.getApplication();
    List<Items> itemsList;
    List <Categories> categoriesList;
    Spinner spinner;
    DatabaseReference db;
    DataBaseHelper dataBaseHelper = new DataBaseHelper(addItem.this);

    TextView edtItem,edtPrice, txtDate, txtMessage;
    Button btnCancel,btnDone;
    String dataSaved, selectedCategory;
    int categoryID;
    int limitItem;
    private DatePickerDialog.OnDateSetListener onDateSetListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        // setting the objects
        edtItem= (TextView) findViewById(R.id.edtItem);
        edtPrice= (TextView) findViewById(R.id.edtPrice);
        txtDate= (TextView) findViewById(R.id.txtDate);
        btnDone=(Button) findViewById(R.id.btnDone);
        btnCancel =(Button) findViewById(R.id.btnCancel);
        txtMessage=(TextView )findViewById(R.id.txtLabel4);
        itemsList= globalVariables.getItemList();
        categoriesList=dataBaseHelper.getAllCategories();
        spinner=findViewById(R.id.spinner);
        db= FirebaseDatabase.getInstance().getReference();

// dropdown of category
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,globalVariables.getDropDownList());
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);



        //getting a the date view

        txtDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                Calendar cal= Calendar.getInstance();
                int year =cal.get(Calendar.YEAR);
                int month =cal.get(Calendar.MONTH);
                int day =cal.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog dialog= new DatePickerDialog(
                        addItem.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,onDateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable( new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });
        onDateSetListener= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                // returning the data selected
                dataSaved= Integer.toString (year)+"/"+Integer.toString(month)+"/"+Integer.toString( day);
                txtDate.setText(dataSaved);
                // Toast.makeText(addItem.this,(year)+"/"+Integer.toString(month)+"/"+Integer.toString( day),Toast.LENGTH_LONG).show();
            }
        };

// passing data to the show item view

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //assigning the variables

                String item= edtItem.getText().toString();
                int price =Integer.parseInt( edtPrice.getText().toString());
                String date= txtDate.getText().toString();
                selectedCategory=spinner.getSelectedItem().toString();

                Intent i= new Intent( addItem.this,ShowItems.class);
                i.putExtra("category",selectedCategory);



/// if goal not reached add item


                    addItem();




if (checkIfGoalIsComplete()){
    startActivity(i);

};


            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getApplicationContext(),ShowCategories.class);


                startActivity(i);
            }
        });


    }
    //check if the goal has been reached if so the user can not add to list
    public Boolean checkIfGoalIsComplete(){
        boolean saveItem=true;
        limitItem=0;
        int count=0;




        // get the goal form th category class
        for(Categories i: globalVariables.getCategoryList()){

            if(i.getCategory().contentEquals(selectedCategory)){
            limitItem=i.getGoal();

            };
        }

      // checking if the goal haven reached
        for(Items i:globalVariables.getItemList()){


            if(i.getCategoryID().contentEquals(selectedCategory)){
                count++;

            };
        }
        if (limitItem == count){
            saveItem =false;
            txtMessage.setText("Goal has been Reached");

        }
        return saveItem;


    };

    private void addItem() {
        String item= edtItem.getText().toString();
        int price =Integer.parseInt( edtPrice.getText().toString());
        String date= txtDate.getText().toString();
        selectedCategory=spinner.getSelectedItem().toString();


        String id = db.push().getKey();
        Items c = new Items(id,item,price,selectedCategory,date,"https://assets.vogue.com/photos/61602c7c30a1330360069511/master/w_1280%2Cc_limit/slide_2.jpg");
        db.child("items").child(id).setValue(c).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                    Toast.makeText(addItem.this, "Created", Toast.LENGTH_LONG).show();

                }
            }


        });


    }


}