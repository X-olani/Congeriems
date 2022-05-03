package com.example.congeriem;

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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class addItem extends AppCompatActivity {
    TextView edtItem,edtPrice, txtDate, txtMessage;
    Button btnCancel,btnDone;
    String dataSaved, selectedCategory;
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

        Bundle newData= getIntent().getExtras();
        if(newData!=null){

            selectedCategory=newData.getString("category");
            limitItem=newData.getInt("goal");
            checkIfGoalIsComplete(limitItem);
        }
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
                String item= edtItem.getText().toString();
                int price =Integer.parseInt( edtPrice.getText().toString());
                String date= txtDate.getText().toString();
                Intent i= new Intent( addItem.this,ShowItems.class);
                i.putExtra("item",item);
                i.putExtra("price",price);
                i.putExtra("date",date);
                i.putExtra("category",selectedCategory);
                if(checkIfGoalIsComplete(limitItem)){

                    startActivity(i);
                }



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
    public Boolean checkIfGoalIsComplete(int goal){
        boolean saveItem=true;
        ListOfItems listItems= ((GlobalVariables)this.getApplication()).getListItem();
        ArrayList<Items> ArrayItems= (ArrayList<Items>) listItems.getMyItemList();
        int count=0;
        for(Items i:ArrayItems){

            if(i.getCategoryID().contentEquals(selectedCategory)){
                count++;

            };
        }
        Toast.makeText(addItem.this,Integer.toString( goal ),Toast.LENGTH_LONG).show();
        if (goal == count){
            saveItem =false;
            txtMessage.setText("Goal has been Reached");

        }
        return saveItem;


    };
}