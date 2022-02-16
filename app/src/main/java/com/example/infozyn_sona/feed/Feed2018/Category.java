package com.example.infozyn_sona.feed.Feed2018;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.infozyn_sona.R;

public class Category extends AppCompatActivity {
    RelativeLayout Finearts_2018,upcomingEvents_2018,awards_2018,FundedProjects_2018;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        FundedProjects_2018=findViewById(R.id.fundedProjects_2018);
        awards_2018=findViewById(R.id.awards_2018);
        Finearts_2018=findViewById(R.id.finearts_2018);
        upcomingEvents_2018=findViewById(R.id.upcoming_event__2018);

        Finearts_2018.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Category.this,Feed_2018.class));
            }
        });
        upcomingEvents_2018.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Category.this, Feed_2018_Upcoming_event.class));
            }
        });
        awards_2018.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Category.this, Feed2018_awards.class));
            }
        });

        FundedProjects_2018.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Category.this, Feed2018_FundedProject.class));
            }
        });
    }
}