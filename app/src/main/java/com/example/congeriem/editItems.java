package com.example.congeriem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class editItems extends AppCompatActivity {
    EditText edtItem,edtPrice;
    Button btnDelete, btnDone;
    TextView edtDate;
    Spinner edtCategoryID;

    //class to connect to the sql database
    DataBaseHelper dataBaseHelper = new DataBaseHelper(editItems.this);
    int itemID;
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

        if(newData!=null){

            // setting the values
            itemID=  newData.getInt("itemID");
            edtItem.setText(newData.getString("item"));
            edtPrice.setText(Integer.toString(newData.getInt("price")));
            edtDate.setText(newData.getString("date"));

            // adding the category into the spinner using a list
            List<Categories> j= new ArrayList<>();
                  for( Categories i : dataBaseHelper.getAllCategories()){
                      j.add(new Categories(i.getID(),i.getCategory(),i.getGoal()));

                  }
            ArrayAdapter<Categories> spinnerAdapter = new ArrayAdapter<Categories>(this, android.R.layout.simple_spinner_item,j);
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            edtCategoryID.setAdapter(spinnerAdapter);

btnDelete.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        // find the right object to delete by finding the id
        for (Items t : dataBaseHelper.getAllItems()) {

            if (t.getID() == itemID) {
                Items items = new Items(t.getID(), t.getItems(), t.getPrice(), t.getCategoryID(), t.getDate(), t.getUrl());
                dataBaseHelper.deleteItem(items);
                Intent i = new Intent(editItems.this, ShowCategories.class);
                startActivity(i);
            }

        }
    }
});


        }
    }
}