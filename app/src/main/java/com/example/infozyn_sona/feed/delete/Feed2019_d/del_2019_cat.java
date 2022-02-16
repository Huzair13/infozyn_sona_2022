package com.example.infozyn_sona.feed.delete.Feed2019_d;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.infozyn_sona.R;
import com.example.infozyn_sona.feed.delete.Feed2020_d.FeedDel2020_FineArts;
import com.example.infozyn_sona.feed.delete.Feed2020_d.del_2020_cat;

public class del_2019_cat extends AppCompatActivity {

    RelativeLayout FineArts2019_d,Awards2019_d,fp2019_d,upev2019_d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del2019_cat);

        FineArts2019_d=findViewById(R.id.finearts_2019_d);
        Awards2019_d=findViewById(R.id.awards_2019_d);
        fp2019_d=findViewById(R.id.fundedProjects_2019_d);
        upev2019_d=findViewById(R.id.upcoming_event__2019_d);

        FineArts2019_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(del_2019_cat.this, FeedDel2019_FineArts.class));
            }
        });

        Awards2019_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(del_2019_cat.this,FeedDel2019_awards.class));
            }
        });

        upev2019_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(del_2019_cat.this,FeedDel2019_Upcoming_Events.class));
            }
        });

        fp2019_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(del_2019_cat.this,FeedDel2019_FundedProjects.class));
            }
        });

    }
}