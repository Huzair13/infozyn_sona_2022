package com.example.infozyn_sona.feed.Feed2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.infozyn_sona.R;

public class Category_2021 extends AppCompatActivity {

    RelativeLayout Finearts_2021,upcomingEvents_2021,awards_2021,FundedProjects_2021;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category2021);

        FundedProjects_2021=findViewById(R.id.fundedProjects_2021);
        awards_2021=findViewById(R.id.awards_2021);
        Finearts_2021=findViewById(R.id.finearts_2021);
        upcomingEvents_2021=findViewById(R.id.upcoming_event__2021);

        Finearts_2021.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Category_2021.this, Feed2021_FineArts.class));
            }
        });

        upcomingEvents_2021.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Category_2021.this, Feed2021_Upcoming_event.class));
            }
        });

        awards_2021.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Category_2021.this,Feed2021_Awards.class));
            }
        });

        FundedProjects_2021.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Category_2021.this,Feed2021_FundedProjects.class));
            }
        });

    }
}