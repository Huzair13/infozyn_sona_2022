package com.example.infozyn_sona.feed.delete.Feed2021_d;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.infozyn_sona.R;

public class del_2021_cat extends AppCompatActivity {

    private RelativeLayout FineArts_d,Awards_d,Upev_d,FundedProjects_d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del2021_cat);

        FineArts_d=findViewById(R.id.finearts_2021_d);
        Awards_d=findViewById(R.id.awards_2021_d);
        Upev_d=findViewById(R.id.upcoming_event__2021_d);
        FundedProjects_d=findViewById(R.id.fundedProjects_2021_d);

        Awards_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(del_2021_cat.this, FeedDel2021_awards.class));
            }
        });

        FineArts_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(del_2021_cat.this, FeedDel2021_FineArts.class));
            }
        });

        Upev_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(del_2021_cat.this,FeedDel2021_Upcoming_Events.class));
            }
        });

        FundedProjects_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(del_2021_cat.this,FeedDel2021_FundedProjects.class));
            }
        });
    }
}