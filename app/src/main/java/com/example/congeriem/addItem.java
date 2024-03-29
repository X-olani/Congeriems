package com.example.congeriem;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class addItem extends AppCompatActivity {
    GlobalVariables globalVariables=(GlobalVariables) this.getApplication();
    List<Items> itemsList;
    List <Categories> categoriesList;
    Spinner spinner;
    DatabaseReference db;
    StorageReference dbStorage;
    DataBaseHelper dataBaseHelper = new DataBaseHelper(addItem.this);

    TextView edtItem,edtPrice, txtDate, txtMessage;
    Button btnCancel,btnDone,btnTakePicture;
    String dataSaved, selectedCategory;


    Uri img_uri;

    ImageView img;
 ;
    int limitItem;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
// variables to store the constants of the camara returns

    ActivityResultLauncher< String> activityResultLauncher;
  int  CAMERA_PERMISSION_CODE=1;
  int CAMARA=2;



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
        btnTakePicture=findViewById(R.id.btnTakePicture);
        txtMessage=(TextView )findViewById(R.id.txtLabel4);
        itemsList= globalVariables.getItemList();
        categoriesList=dataBaseHelper.getAllCategories();
        spinner=findViewById(R.id.spinner);
        img =findViewById(R.id.imgCap);
        db= FirebaseDatabase.getInstance().getReference();
        dbStorage= FirebaseStorage.getInstance().getReference("uploads/"+String.valueOf(System.currentTimeMillis())+".jpg");

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

                ImageURL( img_uri);

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

        // getting thee image back from the camera
        activityResultLauncher= registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                if(result!=null){
img_uri=result;
                    img.setImageURI(result);
                }
            }

        });

        // button to lunch the camera
btnTakePicture.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        //opening the intent for the camera

/*        ContentValues values = new ContentValues();

Intent camaraIntent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
if(camaraIntent.resolveActivity(getPackageManager())!=null){

    activityResultLauncher.launch(camaraIntent);
}
else{
    Toast.makeText(addItem.this,"Wont able to get camera working",Toast.LENGTH_LONG).show();
}

*/


        Intent intent= new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
     activityResultLauncher.launch("image/*");
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

    private void addItem(String geturl) {
        String item= edtItem.getText().toString();
        int price =Integer.parseInt( edtPrice.getText().toString());
        String date= txtDate.getText().toString();
        selectedCategory=spinner.getSelectedItem().toString();

        // upload image
      /*  StorageReference fileReff= dbStorage.child(String.valueOf(System.currentTimeMillis()));
        fileReff.putFile()*/


        String id = db.push().getKey();
        Log.i("URL","SHOW URL "+ geturl.toString());
        Items c = new Items(id,item,price,selectedCategory,date,geturl.toString());

        db.child("items").child(id).setValue(c).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                    Toast.makeText(addItem.this, "Created", Toast.LENGTH_LONG).show();

                }
            }


        });


    }
    public void ImageURL(Uri bb){


        dbStorage.putFile(bb).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                dbStorage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        addItem(uri.toString());
                    }
                });




                Toast.makeText(addItem.this, "Image was Uploaded",Toast.LENGTH_LONG).show();
            }

        }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

            }
        })
.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(addItem.this, "Image was not Uploaded",Toast.LENGTH_LONG).show();

            }
        });

    }


}