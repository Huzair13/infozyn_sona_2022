package com.example.infozyn_sona.feed.delete.Feed2019_d;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.infozyn_sona.R;
import com.example.infozyn_sona.feed.delete.Feed2019_d_Adapters.Feed2019_Upcoming_Events_del_Adapter;
import com.example.infozyn_sona.feed.delete.Feed2020_d.FeedDel2020_Upcoming_Events;
import com.example.infozyn_sona.feed.delete.Feed2020_d_Adapters.Feed2020_Upcoming_Events_del_Adapter;
import com.example.infozyn_sona.feed.sonaNews;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FeedDel2019_Upcoming_Events extends AppCompatActivity {

    RecyclerView recyclerView_2019_upev_d;
    DatabaseReference reference_2019_upev_d;
    Feed2019_Upcoming_Events_del_Adapter adapter_2019_upev_d;
    ArrayList<sonaNews> arrayList_2019_upev_d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_del2019_upcoming_events);

        recyclerView_2019_upev_d=findViewById(R.id.recyclerView_Feed2019_upev_d);
        reference_2019_upev_d= FirebaseDatabase.getInstance().getReference("Notice").child("2019").child("Upcoming Events");
        recyclerView_2019_upev_d.setHasFixedSize(true);
        recyclerView_2019_upev_d.setLayoutManager(new LinearLayoutManager(this));
        arrayList_2019_upev_d=new ArrayList<>();
        adapter_2019_upev_d = new Feed2019_Upcoming_Events_del_Adapter(arrayList_2019_upev_d,this);
        recyclerView_2019_upev_d.setAdapter(adapter_2019_upev_d);

        reference_2019_upev_d.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    sonaNews user =dataSnapshot.getValue(sonaNews.class);
                    arrayList_2019_upev_d.add(user);
                }
                adapter_2019_upev_d.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(FeedDel2019_Upcoming_Events.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}