package com.example.infozyn_sona.feed.Feed2018;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.infozyn_sona.R;
import com.example.infozyn_sona.feed.feed2018_Adapters.Feed2018_upcomig_event_Adapter;
import com.example.infozyn_sona.feed.sonaNews;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Feed_2018_Upcoming_event extends AppCompatActivity {

    RecyclerView recyclerView_2018_upev;
    DatabaseReference reference_2018_upev;
    Feed2018_upcomig_event_Adapter adapter_2018_upev;
    ArrayList<sonaNews> arrayList_2018_upev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed2018_upcoming_event);

        recyclerView_2018_upev=findViewById(R.id.recyclerView_feed2018_upev);
        reference_2018_upev= FirebaseDatabase.getInstance().getReference("Notice").child("2018").child("Upcoming Events");
        recyclerView_2018_upev.setHasFixedSize(true);
        recyclerView_2018_upev.setLayoutManager(new LinearLayoutManager(this));

        arrayList_2018_upev=new ArrayList<>();
        adapter_2018_upev = new Feed2018_upcomig_event_Adapter(arrayList_2018_upev,this);
        recyclerView_2018_upev.setAdapter(adapter_2018_upev);

        reference_2018_upev.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    sonaNews user =dataSnapshot.getValue(sonaNews.class);
                    arrayList_2018_upev.add(user);
                }
                adapter_2018_upev.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Feed_2018_Upcoming_event.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}