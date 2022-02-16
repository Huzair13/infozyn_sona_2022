package com.example.infozyn_sona.feed.Feed2020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.infozyn_sona.R;
import com.example.infozyn_sona.feed.Feed2020_Adapters.Feed2020_Upcoming_event_Adapter;
import com.example.infozyn_sona.feed.sonaNews;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Feed2020_Upcoming_Events extends AppCompatActivity {

    RecyclerView recyclerView_2020_upev;
    DatabaseReference reference_2020_upev;
    Feed2020_Upcoming_event_Adapter adapter_2020_upev;
    ArrayList<sonaNews> arrayList_2020_upev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed2020_upcoming_events);

        recyclerView_2020_upev=findViewById(R.id.recyclerView_feed2020_upev);
        reference_2020_upev= FirebaseDatabase.getInstance().getReference("Notice").child("2020").child("Upcoming Events");
        recyclerView_2020_upev.setHasFixedSize(true);
        recyclerView_2020_upev.setLayoutManager(new LinearLayoutManager(this));

        arrayList_2020_upev=new ArrayList<>();
        adapter_2020_upev = new Feed2020_Upcoming_event_Adapter(arrayList_2020_upev,this);
        recyclerView_2020_upev.setAdapter(adapter_2020_upev);

        reference_2020_upev.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    sonaNews user =dataSnapshot.getValue(sonaNews.class);
                    arrayList_2020_upev.add(user);
                }
                adapter_2020_upev.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Feed2020_Upcoming_Events.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}