package com.example.infozyn_sona.feed.Feed2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.infozyn_sona.R;
import com.example.infozyn_sona.feed.Feed2021_Adapters.Feed2021_Upcoming_Event_Adapter;
import com.example.infozyn_sona.feed.sonaNews;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Feed2021_Upcoming_event extends AppCompatActivity {

    RecyclerView recyclerView_2021_upev;
    DatabaseReference reference_2021_upev;
    Feed2021_Upcoming_Event_Adapter adapter_2021_upev;
    ArrayList<sonaNews> arrayList_2021_upev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed2021_upcoming_event);

        recyclerView_2021_upev=findViewById(R.id.recyclerView_feed2021_upev);
        reference_2021_upev= FirebaseDatabase.getInstance().getReference("Notice").child("2021").child("Upcoming Events");
        recyclerView_2021_upev.setHasFixedSize(true);
        recyclerView_2021_upev.setLayoutManager(new LinearLayoutManager(this));

        arrayList_2021_upev=new ArrayList<>();
        adapter_2021_upev = new Feed2021_Upcoming_Event_Adapter(arrayList_2021_upev,this);
        recyclerView_2021_upev.setAdapter(adapter_2021_upev);

        reference_2021_upev.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    sonaNews user =dataSnapshot.getValue(sonaNews.class);
                    arrayList_2021_upev.add(user);
                }
                adapter_2021_upev.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Feed2021_Upcoming_event.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}