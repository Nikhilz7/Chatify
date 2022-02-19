package com.example.chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.chat.Model.Users;
import com.example.chat.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;



import java.util.Objects;


public class signup extends AppCompatActivity {
   ActivitySignupBinding binding;
    private FirebaseAuth auth;
    FirebaseDatabase database;
    ProgressDialog dialog;
//    DatabaseReference myRef;
    @IgnoreExtraProperties
    public class User {

        public String username;
        public String email;
        public String pass;

        public User() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public User(String username, String email,String pass) {
            this.username = username;
            this.email = email;
            this.pass = pass;
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        
        getSupportActionBar().hide();
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        dialog = new ProgressDialog(signup.this);
        dialog.setTitle("Creating Account");
        dialog.setMessage("Experience the new chat ");

//        myRef = database.getReference("message");
        binding.btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                auth.createUserWithEmailAndPassword(binding.etEmail.getText().toString() , binding.etPass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        myRef.setValue("Hello, World!");
                        dialog.dismiss();
                            if (task.isSuccessful()) {
                                Users user = new Users(binding.etUname.getText().toString(), binding.etEmail.getText().toString(), binding.etPass.getText().toString());
                                String id = task.getResult().getUser().getUid();
                                database.getReference().child("Users").child(id).setValue(user);

                                Toast.makeText(signup.this, "User created successfully", Toast.LENGTH_SHORT).show();
                                 Intent intent = new Intent(signup.this, login.class);
                                 startActivity(intent);

                            } else {
                                Toast.makeText(signup.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                            }

                    }
                });
            }
        });
        binding.log.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
               Intent intent = new Intent(signup.this, login.class);
                startActivity(intent);
           }
        });
    }
}