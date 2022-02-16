package com.example.infozyn_sona.feed.Feed2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.infozyn_sona.R;
import com.example.infozyn_sona.feed.Feed2021_Adapters.Feed2021_FundedProjects_Adapter;
import com.example.infozyn_sona.feed.sonaNews;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Feed2021_FundedProjects extends AppCompatActivity {

    RecyclerView recyclerView_2021_fp;
    DatabaseReference reference_2021_fp;
    Feed2021_FundedProjects_Adapter adapter_2021_fp;
    ArrayList<sonaNews> arrayList_2021_fp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed2021_funded_projects);

        recyclerView_2021_fp=findViewById(R.id.recyclerView_feed2021_fp);
        reference_2021_fp= FirebaseDatabase.getInstance().getReference("Notice").child("2021").child("Funded Projects");
        recyclerView_2021_fp.setHasFixedSize(true);
        recyclerView_2021_fp.setLayoutManager(new LinearLayoutManager(this));

        arrayList_2021_fp=new ArrayList<>();
        adapter_2021_fp = new Feed2021_FundedProjects_Adapter(arrayList_2021_fp,this);
        recyclerView_2021_fp.setAdapter(adapter_2021_fp);

        reference_2021_fp.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    sonaNews user =dataSnapshot.getValue(sonaNews.class);
                    arrayList_2021_fp.add(user);
                }
                adapter_2021_fp.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Feed2021_FundedProjects.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}