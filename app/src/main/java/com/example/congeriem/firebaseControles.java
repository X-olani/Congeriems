package com.example.congeriem;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class firebaseControles {


    private DatabaseReference databaseReference;

    public  firebaseControles() {

        databaseReference= FirebaseDatabase.getInstance().getReference();
    }

    //adding category to firebase

    public Task<Void> updateCategory(String key, HashMap<String ,Object> hashMap){

      return   databaseReference.child(key).updateChildren(hashMap);
    }

    public Task<Void> removeCategory(String key){

        return   databaseReference.child(key).removeValue();
    }

    public Query get( ){



            return databaseReference;


    }
}
