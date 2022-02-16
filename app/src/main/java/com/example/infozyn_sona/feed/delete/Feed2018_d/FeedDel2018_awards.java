package com.example.infozyn_sona.feed.delete.Feed2018_d;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.infozyn_sona.R;
import com.example.infozyn_sona.feed.delete.Feed2018_d_Adapters.Feed2018_Awards_del_Adapter;
import com.example.infozyn_sona.feed.delete.Feed2019_d.FeedDel2019_awards;
import com.example.infozyn_sona.feed.delete.Feed2019_d_Adapters.Feed2019_Awards_del_Adapter;
import com.example.infozyn_sona.feed.sonaNews;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FeedDel2018_awards extends AppCompatActivity {

    RecyclerView recyclerView_2018_awards_d;
    DatabaseReference reference_2018_awards_d;
    Feed2018_Awards_del_Adapter adapter_2018_awards_d;
    ArrayList<sonaNews> arrayList_2018_awards_d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_del2018_awards);

        recyclerView_2018_awards_d=findViewById(R.id.recyclerView_Feed2018_awards_d);
        reference_2018_awards_d= FirebaseDatabase.getInstance().getReference("Notice").child("2018").child("Awards");
        recyclerView_2018_awards_d.setHasFixedSize(true);
        recyclerView_2018_awards_d.setLayoutManager(new LinearLayoutManager(this));

        arrayList_2018_awards_d=new ArrayList<>();
        adapter_2018_awards_d = new Feed2018_Awards_del_Adapter(arrayList_2018_awards_d,this);
        recyclerView_2018_awards_d.setAdapter(adapter_2018_awards_d);

        reference_2018_awards_d.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    sonaNews user =dataSnapshot.getValue(sonaNews.class);
                    arrayList_2018_awards_d.add(user);
                }
                adapter_2018_awards_d.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(FeedDel2018_awards.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}