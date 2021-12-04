package com.example.infozyn_sona;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;

public class AboutSona extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_sona);
    }

    public void process(View view) {

        Intent intent=null, chooser=null;

        if(view.getId()==R.id.googleMap){

            intent=new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("geo:0,0?q=Sona College of Technology"));
            Intent.createChooser(intent,"Launch Maps");
            startActivity(intent);
        }
    }
}