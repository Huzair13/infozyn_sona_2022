package com.example.infozyn_sona.feed.Feed2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.infozyn_sona.R;
import com.example.infozyn_sona.feed.Feed2021_Adapters.Feed2021_Awards_Adapter;
import com.example.infozyn_sona.feed.sonaNews;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Feed2021_Awards extends AppCompatActivity {

    RecyclerView recyclerView_2021_awards;
    DatabaseReference reference_2021_awards;
    Feed2021_Awards_Adapter adapter_2021_awards;
    ArrayList<sonaNews> arrayList_2021_awards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed2021_awards);

        recyclerView_2021_awards=findViewById(R.id.recyclerView_feed2021_awards);
        reference_2021_awards= FirebaseDatabase.getInstance().getReference("Notice").child("2021").child("Awards");
        recyclerView_2021_awards.setHasFixedSize(true);
        recyclerView_2021_awards.setLayoutManager(new LinearLayoutManager(this));

        arrayList_2021_awards=new ArrayList<>();
        adapter_2021_awards = new Feed2021_Awards_Adapter(arrayList_2021_awards,this);
        recyclerView_2021_awards.setAdapter(adapter_2021_awards);

        reference_2021_awards.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    sonaNews user =dataSnapshot.getValue(sonaNews.class);
                    arrayList_2021_awards.add(user);
                }
                adapter_2021_awards.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Feed2021_Awards.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}