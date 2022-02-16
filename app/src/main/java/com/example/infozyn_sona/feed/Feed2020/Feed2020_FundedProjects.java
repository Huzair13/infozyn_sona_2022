package com.example.infozyn_sona.feed.Feed2020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.infozyn_sona.R;
import com.example.infozyn_sona.feed.Feed2020_Adapters.Feed2020_FundedProjects_Adapter;
import com.example.infozyn_sona.feed.sonaNews;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Feed2020_FundedProjects extends AppCompatActivity {

    RecyclerView recyclerView_2020_fp;
    DatabaseReference reference_2020_fp;
    Feed2020_FundedProjects_Adapter adapter_2020_fp;
    ArrayList<sonaNews> arrayList_2020_fp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed2020_funded_projects);

        recyclerView_2020_fp=findViewById(R.id.recyclerView_feed2020_fp);
        reference_2020_fp= FirebaseDatabase.getInstance().getReference("Notice").child("2020").child("Funded Projects");
        recyclerView_2020_fp.setHasFixedSize(true);
        recyclerView_2020_fp.setLayoutManager(new LinearLayoutManager(this));

        arrayList_2020_fp=new ArrayList<>();
        adapter_2020_fp = new Feed2020_FundedProjects_Adapter(arrayList_2020_fp,this);
        recyclerView_2020_fp.setAdapter(adapter_2020_fp);

        reference_2020_fp.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    sonaNews user =dataSnapshot.getValue(sonaNews.class);
                    arrayList_2020_fp.add(user);
                }
                adapter_2020_fp.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Feed2020_FundedProjects.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}