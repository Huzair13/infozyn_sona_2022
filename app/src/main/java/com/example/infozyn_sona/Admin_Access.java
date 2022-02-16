package com.example.infozyn_sona;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.infozyn_sona.feed.feed2022.Category_2022;
import com.google.android.material.textfield.TextInputEditText;

public class Admin_Access extends AppCompatActivity {

    EditText Access_Input;
    Button Enter_Admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_access);

        Access_Input=findViewById(R.id.InputAccessCodeAdmin);
        Enter_Admin=findViewById(R.id.ButtonLoginAdmin);

        Enter_Admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Access_code=Access_Input.getText().toString();
                checkAccess(Access_code);
            }
        });
    }

    private void checkAccess(String access_code) {
        if(access_code.equals("SonaInfo2022")){
            openAdminPanel();
        }
        else if(TextUtils.isEmpty(access_code)){
            Access_Input.setError("Access code can't be empty !!!");
            Access_Input.requestFocus();
        }
        else {
            Toast.makeText(Admin_Access.this, "Sorry !!! Access code is not Correct !!!", Toast.LENGTH_LONG).show();
        }
    }

    private void openAdminPanel() {
        Intent intent=new Intent(Admin_Access.this, AdminPanel.class);
        startActivity(intent);
    }
}