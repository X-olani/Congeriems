package com.example.congeriem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup_Page extends AppCompatActivity {
    EditText First_name, Last_name, Email, Password;
    Button CREATE_ACCOUNT;
    FirebaseAuth fAuth;
    //ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        First_name = findViewById(R.id.Fist_Name);
        Last_name = findViewById(R.id.Last_Name);
        Email = findViewById(R.id.Signup_Email);
        Password= findViewById(R.id.SignUp_Password);
     CREATE_ACCOUNT= findViewById(R.id.CREATE_ACCOUNT);

        fAuth = FirebaseAuth.getInstance();
        //when app is loading, bar is visible
        //progressBar = findViewById(R.id.progressBar);

        if (fAuth.getCurrentUser() !=  null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        CREATE_ACCOUNT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = First_name.getText().toString().trim();
                String Lname = Last_name.getText().toString().trim();
                String email = Email.getText().toString().trim();
                String pass = Password.getText().toString().trim();

                if (TextUtils.isEmpty(Name)) {
                    First_name.setError("First name is Required");
                    return;
                }
                if (TextUtils.isEmpty(Lname)) {
                    Last_name.setError("Last name is Required");
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    Email.setError("Email is Required");
                }

                if (TextUtils.isEmpty(pass)) {
                    Password.setError("Password is Required");
                    return;
                }
                if (pass.length() < 8){
                Password.setError("Password must be more than 8 characters.");
                return;
                }

           // progressBar.setVisible(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Signup_Page.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            
                        }else{
                            Toast.makeText(Signup_Page.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }

            }};

        }
    }};



