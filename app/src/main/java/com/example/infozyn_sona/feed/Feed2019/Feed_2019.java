package com.example.infozyn_sona.feed.Feed2019;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.infozyn_sona.R;
import com.example.infozyn_sona.feed.feed2019_Adapters.Feed2019_Adapter;
import com.example.infozyn_sona.feed.sonaNews;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Feed_2019 extends AppCompatActivity {

    RecyclerView recyclerView_2019;
    DatabaseReference reference_2019;
    Feed2019_Adapter adapter_2019;
    ArrayList<sonaNews> arrayList_2019;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed2019);

        recyclerView_2019=findViewById(R.id.recyclerView_feed2019);
        reference_2019= FirebaseDatabase.getInstance().getReference("Notice").child("2019").child("FineArts");
        recyclerView_2019.setHasFixedSize(true);
        recyclerView_2019.setLayoutManager(new LinearLayoutManager(this));

        arrayList_2019=new ArrayList<>();
        adapter_2019 = new Feed2019_Adapter(arrayList_2019,this);
        recyclerView_2019.setAdapter(adapter_2019);

        reference_2019.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    sonaNews user =dataSnapshot.getValue(sonaNews.class);
                    arrayList_2019.add(user);
                }
                adapter_2019.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Feed_2019.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}