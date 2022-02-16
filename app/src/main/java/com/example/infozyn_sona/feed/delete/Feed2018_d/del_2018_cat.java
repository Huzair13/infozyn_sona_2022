package com.example.infozyn_sona.feed.delete.Feed2018_d;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.infozyn_sona.R;
import com.example.infozyn_sona.feed.delete.Feed2019_d.FeedDel2019_FineArts;
import com.example.infozyn_sona.feed.delete.Feed2019_d.del_2019_cat;

public class del_2018_cat extends AppCompatActivity {

    RelativeLayout FineArts2018_d,Awards2018_d,fp2018_d,upev2018_d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del2018_cat);

        FineArts2018_d=findViewById(R.id.finearts_2018_d);
        Awards2018_d=findViewById(R.id.awards_2018_d);
        fp2018_d=findViewById(R.id.fundedProjects_2018_d);
        upev2018_d=findViewById(R.id.upcoming_event__2018_d);

        FineArts2018_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(del_2018_cat.this, FeedDel2018_FineArts.class));
            }
        });

        Awards2018_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(del_2018_cat.this,FeedDel2018_awards.class));
            }
        });

        upev2018_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(del_2018_cat.this,FeedDel2018_Upcoming_Events.class));
            }
        });

        fp2018_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(del_2018_cat.this,FeedDel2018_FundedProjects.class));
            }
        });

    }
}