package com.example.infozyn_sona.feed.Feed2020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.infozyn_sona.R;
import com.example.infozyn_sona.feed.Feed2020_Adapters.Feed2020_FineArts_Adapter;
import com.example.infozyn_sona.feed.sonaNews;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Feed2020_FineArts extends AppCompatActivity {

    RecyclerView recyclerView_2020_Finearts;
    DatabaseReference reference_2020_Finearts;
    Feed2020_FineArts_Adapter adapter_2020_Finearts;
    ArrayList<sonaNews> arrayList_2020_Finearts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed2020_fine_arts);

        recyclerView_2020_Finearts=findViewById(R.id.recyclerView_feed2020_Finearts);
        reference_2020_Finearts= FirebaseDatabase.getInstance().getReference("Notice").child("2020").child("FineArts");
        recyclerView_2020_Finearts.setHasFixedSize(true);
        recyclerView_2020_Finearts.setLayoutManager(new LinearLayoutManager(this));

        arrayList_2020_Finearts=new ArrayList<>();
        adapter_2020_Finearts = new Feed2020_FineArts_Adapter(arrayList_2020_Finearts,this);
        recyclerView_2020_Finearts.setAdapter(adapter_2020_Finearts);

        reference_2020_Finearts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    sonaNews user =dataSnapshot.getValue(sonaNews.class);
                    arrayList_2020_Finearts.add(user);
                }
                adapter_2020_Finearts.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Feed2020_FineArts.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}