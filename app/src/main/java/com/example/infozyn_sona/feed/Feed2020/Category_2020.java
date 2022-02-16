package com.example.infozyn_sona.feed.Feed2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.infozyn_sona.R;

public class Category_2020 extends AppCompatActivity {

    RelativeLayout Finearts_2020,upcomingEvents_2020,awards_2020,FundedProjects_2020;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category2020);

        FundedProjects_2020=findViewById(R.id.fundedProjects_2020);
        awards_2020=findViewById(R.id.awards_2020);
        Finearts_2020=findViewById(R.id.finearts_2020);
        upcomingEvents_2020=findViewById(R.id.upcoming_event__2020);

        Finearts_2020.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Category_2020.this, Feed2020_FineArts.class));
            }
        });

        upcomingEvents_2020.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Category_2020.this,Feed2020_Upcoming_Events.class));
            }
        });

        awards_2020.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Category_2020.this,Feed2020_Awards.class));
            }
        });

        FundedProjects_2020.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Category_2020.this,Feed2020_FundedProjects.class));
            }
        });
    }
}