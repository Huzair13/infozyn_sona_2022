package com.example.infozyn_sona.feed.delete.Feed2019_d;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.infozyn_sona.R;
import com.example.infozyn_sona.feed.delete.Feed2019_d_Adapters.Feed2019_Awards_del_Adapter;
import com.example.infozyn_sona.feed.sonaNews;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FeedDel2019_awards extends AppCompatActivity {

    RecyclerView recyclerView_2019_awards_d;
    DatabaseReference reference_2019_awards_d;
    Feed2019_Awards_del_Adapter adapter_2019_awards_d;
    ArrayList<sonaNews> arrayList_2019_awards_d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_del2019_awards);

        recyclerView_2019_awards_d=findViewById(R.id.recyclerView_Feed2019_awards_d);
        reference_2019_awards_d= FirebaseDatabase.getInstance().getReference("Notice").child("2019").child("Awards");
        recyclerView_2019_awards_d.setHasFixedSize(true);
        recyclerView_2019_awards_d.setLayoutManager(new LinearLayoutManager(this));

        arrayList_2019_awards_d=new ArrayList<>();
        adapter_2019_awards_d = new Feed2019_Awards_del_Adapter(arrayList_2019_awards_d,this);
        recyclerView_2019_awards_d.setAdapter(adapter_2019_awards_d);

        reference_2019_awards_d.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    sonaNews user =dataSnapshot.getValue(sonaNews.class);
                    arrayList_2019_awards_d.add(user);
                }
                adapter_2019_awards_d.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(FeedDel2019_awards.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}