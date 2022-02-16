package com.example.infozyn_sona.feed.feed2022;

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

public class Feed2022_FundedProjects extends AppCompatActivity {

    RecyclerView recyclerView_2022_fp;
    DatabaseReference reference_2022_fp;
    Feed2021_FundedProjects_Adapter adapter_2022_fp;
    ArrayList<sonaNews> arrayList_2022_fp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed2022_funded_projects);

        recyclerView_2022_fp=findViewById(R.id.recyclerView_feed2022_fp);
        reference_2022_fp= FirebaseDatabase.getInstance().getReference("Notice").child("2022").child("Funded Projects");
        recyclerView_2022_fp.setHasFixedSize(true);
        recyclerView_2022_fp.setLayoutManager(new LinearLayoutManager(this));

        arrayList_2022_fp=new ArrayList<>();
        adapter_2022_fp = new Feed2021_FundedProjects_Adapter(arrayList_2022_fp,this);
        recyclerView_2022_fp.setAdapter(adapter_2022_fp);

        reference_2022_fp.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    sonaNews user =dataSnapshot.getValue(sonaNews.class);
                    arrayList_2022_fp.add(user);
                }
                adapter_2022_fp.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Feed2022_FundedProjects.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}