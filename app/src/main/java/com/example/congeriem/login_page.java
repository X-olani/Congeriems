package com.example.congeriem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class login_page extends AppCompatActivity {
    Button btn;
    TextView txtemail,txtpassword;

    //Fake Data
    String dummyEmail="admin@gmail.com";
    String dumpyPassword="12345";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        btn = (Button) findViewById(R.id.loginbtn);
        txtemail=(TextView)findViewById(R.id.email);
        txtpassword=(TextView)findViewById(R.id.password);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=txtemail.getText().toString();
                String password=txtpassword.getText().toString();
                openLogedIn(email,password);

            }
        });

    }
    // function to open main activity
    private void openLogedIn(String email, String password) {
        Intent intent = new Intent(login_page.this, MainActivity.class);

        if(isTextFulled(email,password)==true){
            startActivity(intent);
        }




    }

    // function checks if the fields are empty
    public Boolean isTextFulled(String email, String password ){
        Boolean login=false;


        if (email.trim()=="" && password.trim()==""){

            Toast.makeText(login_page.this,"Input Fields Empty",Toast.LENGTH_LONG).show();

        }else if(dummyEmail.equals(email) && dumpyPassword.equals(password)){
            login=true;
        }else {
            Toast.makeText(login_page.this,email+" "+password,Toast.LENGTH_LONG).show();


        }
        return login;
    }
}