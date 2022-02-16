package com.example.infozyn_sona.feed.Feed2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.infozyn_sona.R;
import com.example.infozyn_sona.feed.Feed2021_Adapters.Feed2021_FineArts_Adapter;
import com.example.infozyn_sona.feed.sonaNews;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Feed2021_FineArts extends AppCompatActivity {

    RecyclerView recyclerView_2021_Finearts;
    DatabaseReference reference_2021_Finearts;
    Feed2021_FineArts_Adapter adapter_2021_Finearts;
    ArrayList<sonaNews> arrayList_2021_Finearts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed2021_fine_arts);

        recyclerView_2021_Finearts=findViewById(R.id.recyclerView_feed2021_Finearts);
        reference_2021_Finearts= FirebaseDatabase.getInstance().getReference("Notice").child("2021").child("FineArts");
        recyclerView_2021_Finearts.setHasFixedSize(true);
        recyclerView_2021_Finearts.setLayoutManager(new LinearLayoutManager(this));

        arrayList_2021_Finearts=new ArrayList<>();
        adapter_2021_Finearts = new Feed2021_FineArts_Adapter(arrayList_2021_Finearts,this);
        recyclerView_2021_Finearts.setAdapter(adapter_2021_Finearts);

        reference_2021_Finearts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    sonaNews user =dataSnapshot.getValue(sonaNews.class);
                    arrayList_2021_Finearts.add(user);
                }
                adapter_2021_Finearts.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Feed2021_FineArts.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}