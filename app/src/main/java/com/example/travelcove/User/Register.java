package com.example.travelcove.User;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travelcove.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    FirebaseFirestore fStore;

    EditText passwordInput , nameInput , emailInput;
    CheckBox showPasswordBox;
    TextView registerButton , signInButton;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        passwordInput = findViewById(R.id.passwordInput);
        showPasswordBox = findViewById(R.id.showPassword);
        nameInput = findViewById(R.id.nameInput);
        emailInput = findViewById(R.id.emailInput);
        registerButton = findViewById(R.id.loginField);
        signInButton = findViewById(R.id.signInField);
        progressBar = findViewById(R.id.progressBar);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext() , Welcome_page.class));
            finish();
        }

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameInput.getText().toString().trim();
                String email = emailInput.getText().toString().trim();
                String password = passwordInput.getText().toString().trim();

                if(TextUtils.isEmpty(name)){
                    nameInput.setError("Name required");
                    return;
                }

                if(TextUtils.isEmpty(email)){
                    emailInput.setError("Email required");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    passwordInput.setError("Password required");
                    return;
                }

                if(password.length() < 6){
                    passwordInput.setError("Password must contain at least 6 characters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //We will register the user in firebase now

                fAuth.createUserWithEmailAndPassword(email , password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful() ) {
                            Toast.makeText(Register.this, "Signed in successfully", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);


                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String , Object> user = new HashMap<>();
                            user.put("Name" , name);
                            user.put("Email" , email);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.i("Travel" , "user profile created for " + userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull  Exception e) {
                                    Log.i("Travel" , "failure on " + e.toString());
                                }
                            });

                            startActivity(new Intent(getApplicationContext() , Welcome_page.class));
                        }


                        else{
                            Toast.makeText(Register.this, "Error : " + task.getException().getMessage() , Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }
        });

        showPasswordBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    passwordInput.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }

                else {
                    passwordInput.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

    }

    public void loginButtonHandle(View view){
        startActivity(new Intent(getApplicationContext() , Login.class));
    }

}