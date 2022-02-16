package com.example.infozyn_sona.feed.feed2022;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.infozyn_sona.R;
import com.example.infozyn_sona.feed.Feed2022_Adapters.Feed2022_FineArts_Adapter;
import com.example.infozyn_sona.feed.sonaNews;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Feed2022_FineArts extends AppCompatActivity {

    RecyclerView recyclerView_2022_Finearts;
    DatabaseReference reference_2022_Finearts;
    Feed2022_FineArts_Adapter adapter_2022_Finearts;
    ArrayList<sonaNews> arrayList_2022_Finearts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed2022_fine_arts);

        recyclerView_2022_Finearts=findViewById(R.id.recyclerView_feed2022_Finearts);
        reference_2022_Finearts= FirebaseDatabase.getInstance().getReference("Notice").child("2022").child("FineArts");
        recyclerView_2022_Finearts.setHasFixedSize(true);
        recyclerView_2022_Finearts.setLayoutManager(new LinearLayoutManager(this));

        arrayList_2022_Finearts=new ArrayList<>();
        adapter_2022_Finearts = new Feed2022_FineArts_Adapter(arrayList_2022_Finearts,this);
        recyclerView_2022_Finearts.setAdapter(adapter_2022_Finearts);

        reference_2022_Finearts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    sonaNews user =dataSnapshot.getValue(sonaNews.class);
                    arrayList_2022_Finearts.add(user);
                }
                adapter_2022_Finearts.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Feed2022_FineArts.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}