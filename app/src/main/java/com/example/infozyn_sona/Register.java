package com.example.infozyn_sona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.infozyn_sona.feed.UploadNotice;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {

    EditText RegEmail;
    EditText RegPass;
    EditText Regpass2,regUserName,regphone;
    TextView LoginHere;
    Button RegHere;
    ProgressDialog progressDialog;

    FirebaseAuth mAuth ;
    FirebaseUser mUser;
    FirebaseDatabase database;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        LoginHere=findViewById(R.id.loginhere);

        RegEmail=findViewById(R.id.regemail);
        RegPass=findViewById(R.id.regpassword);
        Regpass2=findViewById(R.id.regpassword2);
        regUserName=findViewById(R.id.RegUserName);
        regphone=findViewById(R.id.regphone);

        RegHere=findViewById(R.id.buttonreg);

        progressDialog=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();

        database=FirebaseDatabase.getInstance();


        LoginHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this, MainActivity.class));
            }
        });


        RegHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerforAuth();
            }
        });
    }



    private void PerforAuth(){
        String email=RegEmail.getText().toString();
        String password =RegPass.getText().toString();
        String confirmPass=Regpass2.getText().toString();
        String name=regUserName.getText().toString();
        String phone=regphone.getText().toString();
        String profile="null";

        if(name.isEmpty()){
            regUserName.setError("Please Enter Name");
            regUserName.requestFocus();
        }
        else if(email.isEmpty()){
            RegEmail.setError("Email Cannot Be Empty");
            RegEmail.requestFocus();
        }else if(phone.isEmpty()){
            regphone.setError("Enter Phone Number");
            regphone.requestFocus();
        }
        else if(password.isEmpty() || password.length()<6){
            RegPass.setError("Password must contain >=6 characters");
            RegPass.requestFocus();
        }else if(!password.equals(confirmPass)){
            Regpass2.setError("Password Not Matching");
            Regpass2.requestFocus();
        }else {
            progressDialog.setMessage("Please wait while Registration");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();



            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();

                        UserDetails userDetails=new UserDetails(name,email,phone,profile);
                        String id=task.getResult().getUser().getUid();
                        database.getReference().child("Sona_users").child(id).setValue(userDetails);

                        sendUserToNextActivity();
                        Toast.makeText(Register.this, "Registration Successful Log in Now", Toast.LENGTH_SHORT).show();

                    }else{
                        progressDialog.dismiss();
                        Toast.makeText(Register.this, "ERROR !!", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }
    private void sendUserToNextActivity(){
        Intent intent=new Intent(Register.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
}