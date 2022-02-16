package com.example.infozyn_sona.feed.delete.Feed2020_d;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.infozyn_sona.R;
import com.example.infozyn_sona.feed.delete.Feed2020_d_Adapters.Feed2020_FineArts_del_Adapter;
import com.example.infozyn_sona.feed.delete.Feed2021_d.FeedDel2021_FineArts;
import com.example.infozyn_sona.feed.delete.Feed2021_d_Adapters.Feed2021_FineArts_del_Adapter;
import com.example.infozyn_sona.feed.sonaNews;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FeedDel2020_FineArts extends AppCompatActivity {

    RecyclerView recyclerView_2020_FineArts_d;
    DatabaseReference reference_2020_FineArts_d;
    Feed2020_FineArts_del_Adapter adapter_2020_FineArts_d;
    ArrayList<sonaNews> arrayList_2020_FineArts_d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_del2020_fine_arts);

        recyclerView_2020_FineArts_d=findViewById(R.id.recyclerView_Feed2020_FineArts_d);
        reference_2020_FineArts_d= FirebaseDatabase.getInstance().getReference("Notice").child("2020").child("FineArts");
        recyclerView_2020_FineArts_d.setHasFixedSize(true);
        recyclerView_2020_FineArts_d.setLayoutManager(new LinearLayoutManager(this));
        arrayList_2020_FineArts_d=new ArrayList<>();
        adapter_2020_FineArts_d = new Feed2020_FineArts_del_Adapter(arrayList_2020_FineArts_d,this);
        recyclerView_2020_FineArts_d.setAdapter(adapter_2020_FineArts_d);

        reference_2020_FineArts_d.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    sonaNews user =dataSnapshot.getValue(sonaNews.class);
                    arrayList_2020_FineArts_d.add(user);
                }
                adapter_2020_FineArts_d.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(FeedDel2020_FineArts.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}