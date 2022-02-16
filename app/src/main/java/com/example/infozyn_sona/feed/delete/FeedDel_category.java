package com.example.infozyn_sona.feed.delete;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.infozyn_sona.R;
import com.example.infozyn_sona.feed.delete.Feed2018_d.del_2018_cat;
import com.example.infozyn_sona.feed.delete.Feed2019_d.del_2019_cat;
import com.example.infozyn_sona.feed.delete.Feed2020_d.del_2020_cat;
import com.example.infozyn_sona.feed.delete.Feed2021_d.del_2021_cat;
import com.example.infozyn_sona.feed.delete.Feed2022_d.del_2022_cat;

public class FeedDel_category extends AppCompatActivity {

    RelativeLayout del2022,del2021,del2020,del2019,del2018;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_del_category);

        del2021=findViewById(R.id.del_2021_category);
        del2022=findViewById(R.id.del_2022_category);
        del2020=findViewById(R.id.del_2020_category);
        del2019=findViewById(R.id.del_2019_category);
        del2018=findViewById(R.id.del_2018_category);

        del2021.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FeedDel_category.this, del_2021_cat.class));
            }
        });

        del2022.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FeedDel_category.this, del_2022_cat.class));
            }
        });

        del2020.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FeedDel_category.this, del_2020_cat.class));
            }
        });

        del2019.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FeedDel_category.this, del_2019_cat.class));
            }
        });

        del2018.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FeedDel_category.this, del_2018_cat.class));
            }
        });

    }
}
