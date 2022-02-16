package com.example.infozyn_sona.feed.Feed2019;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsService;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.infozyn_sona.R;
import com.example.infozyn_sona.feed.Feed2018.Category;
import com.example.infozyn_sona.feed.Feed2018.Feed_2018;
import com.example.infozyn_sona.feed.Feed2018.Feed_2018_Upcoming_event;

public class Category_2019 extends AppCompatActivity {

    RelativeLayout Finearts_2019,upcomingEvents_2019,awards_2019,FundedProjects_2019;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category2019);

        FundedProjects_2019=findViewById(R.id.fundedProjects_2019);
        awards_2019=findViewById(R.id.awards_2019);
        Finearts_2019=findViewById(R.id.finearts_2019);
        upcomingEvents_2019=findViewById(R.id.upcoming_event__2019);

        Finearts_2019.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Category_2019.this, Feed_2019.class));
            }
        });
        upcomingEvents_2019.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Category_2019.this, Feed2019_Upcoming_event.class));
            }
        });

        awards_2019.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Category_2019.this,Feed2019_Awards.class));
            }
        });

        FundedProjects_2019.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Category_2019.this,Feed2019_FundedProjects.class));
            }
        });

    }
}