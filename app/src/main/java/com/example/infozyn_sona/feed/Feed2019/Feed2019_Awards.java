package com.example.infozyn_sona.feed.Feed2019;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.infozyn_sona.R;
import com.example.infozyn_sona.feed.feed2019_Adapters.Feed2019_Awards_Adapter;
import com.example.infozyn_sona.feed.sonaNews;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Feed2019_Awards extends AppCompatActivity {

    RecyclerView recyclerView_2019_awards;
    DatabaseReference reference_2019_awards;
    Feed2019_Awards_Adapter adapter_2019_awards;
    ArrayList<sonaNews> arrayList_2019_awards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed2019_awards);

        recyclerView_2019_awards=findViewById(R.id.recyclerView_feed2019_awards);
        reference_2019_awards= FirebaseDatabase.getInstance().getReference("Notice").child("2019").child("Awards");
        recyclerView_2019_awards.setHasFixedSize(true);
        recyclerView_2019_awards.setLayoutManager(new LinearLayoutManager(this));

        arrayList_2019_awards=new ArrayList<>();
        adapter_2019_awards = new Feed2019_Awards_Adapter(arrayList_2019_awards,this);
        recyclerView_2019_awards.setAdapter(adapter_2019_awards);

        reference_2019_awards.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    sonaNews user =dataSnapshot.getValue(sonaNews.class);
                    arrayList_2019_awards.add(user);
                }
                adapter_2019_awards.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Feed2019_Awards.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}