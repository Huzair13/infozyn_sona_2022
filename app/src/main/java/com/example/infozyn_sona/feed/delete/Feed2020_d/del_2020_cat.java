package com.example.infozyn_sona.feed.delete.Feed2020_d;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.infozyn_sona.R;

public class del_2020_cat extends AppCompatActivity {

    RelativeLayout FineArts2020_d,Awards2020_d,fp2020_d,upev2020_d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del2020_cat);

        FineArts2020_d=findViewById(R.id.finearts_2020_d);
        Awards2020_d=findViewById(R.id.awards_2020_d);
        fp2020_d=findViewById(R.id.fundedProjects_2020_d);
        upev2020_d=findViewById(R.id.upcoming_event__2020_d);

        FineArts2020_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(del_2020_cat.this, FeedDel2020_FineArts.class));
            }
        });

        Awards2020_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(del_2020_cat.this,FeedDel2020_awards.class));
            }
        });

        upev2020_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(del_2020_cat.this,FeedDel2020_Upcoming_Events.class));
            }
        });

        fp2020_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(del_2020_cat.this,FeedDel2020_FundedProjects.class));
            }
        });
    }
}