package com.example.infozyn_sona.feed.Feed2020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.infozyn_sona.R;
import com.example.infozyn_sona.feed.Feed2020_Adapters.Feed2020_Awards_Adapter;
import com.example.infozyn_sona.feed.sonaNews;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Feed2020_Awards extends AppCompatActivity {

    RecyclerView recyclerView_2020_awards;
    DatabaseReference reference_2020_awards;
    Feed2020_Awards_Adapter adapter_2020_awards;
    ArrayList<sonaNews> arrayList_2020_awards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed2020_awards);

        recyclerView_2020_awards=findViewById(R.id.recyclerView_feed2020_awards);
        reference_2020_awards= FirebaseDatabase.getInstance().getReference("Notice").child("2020").child("Awards");
        recyclerView_2020_awards.setHasFixedSize(true);
        recyclerView_2020_awards.setLayoutManager(new LinearLayoutManager(this));

        arrayList_2020_awards=new ArrayList<>();
        adapter_2020_awards = new Feed2020_Awards_Adapter(arrayList_2020_awards,this);
        recyclerView_2020_awards.setAdapter(adapter_2020_awards);

        reference_2020_awards.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    sonaNews user =dataSnapshot.getValue(sonaNews.class);
                    arrayList_2020_awards.add(user);
                }
                adapter_2020_awards.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Feed2020_Awards.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}