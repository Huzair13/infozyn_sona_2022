package com.example.infozyn_sona.feed.delete.Feed2022_d;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.infozyn_sona.R;

public class del_2022_cat extends AppCompatActivity {

    private RelativeLayout FineArts_d,Awards_d,Upev_d,FundedProjects_d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del2022_cat);

        FineArts_d=findViewById(R.id.finearts_2022_d);
        Awards_d=findViewById(R.id.awards_2022_d);
        Upev_d=findViewById(R.id.upcoming_event__2022_d);
        FundedProjects_d=findViewById(R.id.fundedProjects_2022_d);

        FineArts_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(del_2022_cat.this, FeedDel2022_FineArts.class));
            }
        });

        Awards_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(del_2022_cat.this,FeedDel2022_awards.class));
            }
        });

        Upev_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(del_2022_cat.this,FeedDel2022_Upcoming_Events.class));
            }
        });

        FundedProjects_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(del_2022_cat.this,FeedDel2022_FundedProjects.class));
            }
        });

    }
}