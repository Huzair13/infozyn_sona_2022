package com.example.infozyn_sona.feed.Feed2018;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.infozyn_sona.R;
import com.example.infozyn_sona.feed.feed2018_Adapters.Feed2018_Adapter;
import com.example.infozyn_sona.feed.sonaNews;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Feed_2018 extends AppCompatActivity {

    RecyclerView recyclerView_2018;
    DatabaseReference reference_2018;
    Feed2018_Adapter adapter_2018;
    ArrayList<sonaNews> arrayList_2018;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed2018);

        recyclerView_2018=findViewById(R.id.recyclerView_feed2018);
        reference_2018= FirebaseDatabase.getInstance().getReference("Notice").child("2018").child("FineArts");
        recyclerView_2018.setHasFixedSize(true);
        recyclerView_2018.setLayoutManager(new LinearLayoutManager(this));

        arrayList_2018=new ArrayList<>();
        adapter_2018 = new Feed2018_Adapter(arrayList_2018,this);
        recyclerView_2018.setAdapter(adapter_2018);

        reference_2018.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    sonaNews user =dataSnapshot.getValue(sonaNews.class);
                    arrayList_2018.add(user);
                }
                adapter_2018.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Feed_2018.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}