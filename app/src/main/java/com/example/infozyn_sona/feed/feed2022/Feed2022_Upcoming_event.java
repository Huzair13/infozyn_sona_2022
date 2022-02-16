package com.example.infozyn_sona.feed.feed2022;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.infozyn_sona.R;
import com.example.infozyn_sona.feed.Feed2022_Adapters.Feed2022_Upcoming_Event_Adapter;
import com.example.infozyn_sona.feed.sonaNews;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Feed2022_Upcoming_event extends AppCompatActivity {

    RecyclerView recyclerView_2022_upev;
    DatabaseReference reference_2022_upev;
    Feed2022_Upcoming_Event_Adapter adapter_2022_upev;
    ArrayList<sonaNews> arrayList_2022_upev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed2022_upcoming_event);

        recyclerView_2022_upev=findViewById(R.id.recyclerView_feed2022_upev);
        reference_2022_upev= FirebaseDatabase.getInstance().getReference("Notice").child("2022").child("Upcoming Events");
        recyclerView_2022_upev.setHasFixedSize(true);
        recyclerView_2022_upev.setLayoutManager(new LinearLayoutManager(this));

        arrayList_2022_upev=new ArrayList<>();
        adapter_2022_upev = new Feed2022_Upcoming_Event_Adapter(arrayList_2022_upev,this);
        recyclerView_2022_upev.setAdapter(adapter_2022_upev);

        reference_2022_upev.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    sonaNews user =dataSnapshot.getValue(sonaNews.class);
                    arrayList_2022_upev.add(user);
                }
                adapter_2022_upev.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Feed2022_Upcoming_event.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}