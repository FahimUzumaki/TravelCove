package com.example.travelcove.User;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.travelcove.R;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText emailInput , passwordInput;
    CheckBox showPasswordBox;
    TextView loginButton , signUpButton;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailInput = findViewById(R.id.emailInputLogin);
        passwordInput = findViewById(R.id.passwordInputLogin);
        showPasswordBox = findViewById(R.id.showPasswordLogin);
        progressBar = findViewById(R.id.progressBarLogin);
        loginButton = findViewById(R.id.loginField);
        signUpButton = findViewById(R.id.signUpField);
        fAuth = FirebaseAuth.getInstance();

        /*if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext() , Welcome_page.class));
            finish();
        }*/

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString().trim();
                String password = passwordInput.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    emailInput.setError("Email required");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    passwordInput.setError("Password required");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //log in using firebase authentication

                fAuth.signInWithEmailAndPassword(email , password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                            startActivity(new Intent(getApplicationContext() , Welcome_page.class));
                        }

                        else{
                            Toast.makeText(Login.this, "Error : " + task.getException().getMessage() , Toast.LENGTH_SHORT).show();
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


    public void signUpButtonHandle(View view){
        startActivity(new Intent(getApplicationContext() , Register.class));
    }

    public void signUp(View view){
        startActivity(new Intent(getApplicationContext() , Register.class));
    }
}