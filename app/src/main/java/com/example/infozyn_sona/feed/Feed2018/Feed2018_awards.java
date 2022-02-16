package com.example.infozyn_sona.feed.Feed2018;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.infozyn_sona.R;
import com.example.infozyn_sona.feed.feed2018_Adapters.Feed2018_awards_Adapter;
import com.example.infozyn_sona.feed.sonaNews;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Feed2018_awards extends AppCompatActivity {

    RecyclerView recyclerView_2018_awards;
    DatabaseReference reference_2018_awards;
    Feed2018_awards_Adapter adapter_2018_awards;
    ArrayList<sonaNews> arrayList_2018_awards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed2018_awards);

        recyclerView_2018_awards=findViewById(R.id.recyclerView_feed2018_awards);
        reference_2018_awards= FirebaseDatabase.getInstance().getReference("Notice").child("2018").child("Awards");
        recyclerView_2018_awards.setHasFixedSize(true);
        recyclerView_2018_awards.setLayoutManager(new LinearLayoutManager(this));

        arrayList_2018_awards=new ArrayList<>();
        adapter_2018_awards = new Feed2018_awards_Adapter(arrayList_2018_awards,this);
        recyclerView_2018_awards.setAdapter(adapter_2018_awards);

        reference_2018_awards.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    sonaNews user =dataSnapshot.getValue(sonaNews.class);
                    arrayList_2018_awards.add(user);
                }
                adapter_2018_awards.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Feed2018_awards.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}