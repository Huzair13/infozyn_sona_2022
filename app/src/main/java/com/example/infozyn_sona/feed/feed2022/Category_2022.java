package com.example.infozyn_sona.feed.feed2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.infozyn_sona.R;
import com.example.infozyn_sona.feed.Feed2021.Category_2021;
import com.example.infozyn_sona.feed.Feed2021.Feed2021_FineArts;

public class Category_2022 extends AppCompatActivity {

    RelativeLayout Finearts_2022,upcomingEvents_2022,awards_2022,FundedProjects_2022;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category2022);

        FundedProjects_2022=findViewById(R.id.fundedProjects_2022);
        awards_2022=findViewById(R.id.awards_2022);
        Finearts_2022=findViewById(R.id.finearts_2022);
        upcomingEvents_2022=findViewById(R.id.upcoming_event__2022);

        Finearts_2022.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Category_2022.this, Feed2022_FineArts.class));
            }
        });
        upcomingEvents_2022.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Category_2022.this, Feed2022_Upcoming_event.class));
            }
        });

        awards_2022.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Category_2022.this, Feed2022_Awards.class));
            }
        });

        FundedProjects_2022.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Category_2022.this, Feed2022_FundedProjects.class));
            }
        });

    }
}