package com.example.infozyn_sona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.infozyn_sona.homepage.home_page;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText LogEmail;
    EditText LogPassword;
    TextView TvRegHere;
    Button btnLogin;

    ImageView btnGoogle;

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TvRegHere=findViewById(R.id.RegHere);

        LogEmail=findViewById(R.id.inputEmail);
        LogPassword=findViewById(R.id.InputPassword);
        btnLogin=findViewById(R.id.ButtonLogin);
        btnGoogle=findViewById(R.id.gllogin);

        mAuth=FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(view -> {
            LoginUser();
        });
        TvRegHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Register.class));
            }
        });

        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, GoogleSigninActivity.class);
                startActivity(intent);
            }
        });

    }
    private void LoginUser(){
        String email = LogEmail.getText().toString();
        String password =LogPassword.getText().toString();

        if(TextUtils.isEmpty(email)){
            LogEmail.setError("Email Cannot be empty");
            LogEmail.requestFocus();
        }else if(TextUtils.isEmpty(password)){
            LogPassword.setError("Password Cannot be empty");
            LogPassword.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "User Logged in successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, home_page.class));
                    }else{
                        Toast.makeText(MainActivity.this, "Login Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}