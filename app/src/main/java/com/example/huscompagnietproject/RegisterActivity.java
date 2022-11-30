package com.example.huscompagnietproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    //DatabaseReference dbReference;

    private TextInputEditText fullNameInput;
    private TextInputEditText emailInput;
    private TextInputEditText passwordInput;
    private TextInputEditText conPasswordInput;
    private Button signUpButton;
    private TextView loginLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        //dbReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://huscompagnietproject-default-rtdb.europe-west1.firebasedatabase.app/");

        fullNameInput = findViewById(R.id.nameInput);
        emailInput = findViewById(R.id.emailRegister);
        passwordInput = findViewById(R.id.passwordRegister);
        conPasswordInput = findViewById(R.id.confirmPasswordRegister);
        signUpButton = findViewById(R.id.registerButton);
        loginLabel = findViewById(R.id.loginLabel);

        signUpButton.setOnClickListener(view -> {
            signUpUser();
        });

        loginLabel.setOnClickListener(view -> {
            finish();
        });
    }



    private void signUpUser() {
        String fullName = fullNameInput.getText().toString();
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();
        String passwordCon = conPasswordInput.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            // Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(RegisterActivity.this, "User created successfully, please log in!", Toast.LENGTH_SHORT).show();
                            finish();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authentication failed, please try again.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });

    /*    if (fullName.isEmpty() || email.isEmpty() || password.isEmpty() || passwordCon.isEmpty()) {
            // Ensuring all fields are filled
            Toast.makeText(this, "Please fill in all fields!", Toast.LENGTH_LONG).show();
        } else if(!password.equals(passwordCon)) {
            //Checking if password and confirm password matches
            Toast.makeText(this, "Passwords does not match!", Toast.LENGTH_LONG).show();
        } else {
            dbReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    //Checking if email is already registered
                    if(dataSnapshot.hasChild(email)){
                        Toast.makeText(RegisterActivity.this, "Email is already registered", Toast.LENGTH_SHORT).show();
                    } else {
                        // Sending data to Firebase Realtime Database using email is unique ID
                        dbReference.child("users").child(email).child("fullname").setValue(fullName);
                        dbReference.child("users").child(email).child("password").setValue(password);

                        //Finishes activity after successful creation of a user
                        Toast.makeText(RegisterActivity.this, "User is registered!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
*/



    }
}