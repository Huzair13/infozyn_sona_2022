package com.example.infozyn_sona.feed.Feed2019;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.infozyn_sona.R;
import com.example.infozyn_sona.feed.feed2019_Adapters.Feed2019_Upcoming_event_adapter;
import com.example.infozyn_sona.feed.sonaNews;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Feed2019_Upcoming_event extends AppCompatActivity {

    RecyclerView recyclerView_2019_upev;
    DatabaseReference reference_2019_upev;
    Feed2019_Upcoming_event_adapter adapter_2019_upev;
    ArrayList<sonaNews> arrayList_2019_upev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed2019_upcoming_event);

        recyclerView_2019_upev=findViewById(R.id.recyclerView_feed2019_upev);
        reference_2019_upev= FirebaseDatabase.getInstance().getReference("Notice").child("2019").child("Upcoming Events");
        recyclerView_2019_upev.setHasFixedSize(true);
        recyclerView_2019_upev.setLayoutManager(new LinearLayoutManager(this));

        arrayList_2019_upev=new ArrayList<>();
        adapter_2019_upev = new Feed2019_Upcoming_event_adapter(arrayList_2019_upev,this);
        recyclerView_2019_upev.setAdapter(adapter_2019_upev);

        reference_2019_upev.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    sonaNews user =dataSnapshot.getValue(sonaNews.class);
                    arrayList_2019_upev.add(user);
                }
                adapter_2019_upev.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Feed2019_Upcoming_event.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}