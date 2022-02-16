package com.example.infozyn_sona.feed.Feed2018;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.infozyn_sona.R;
import com.example.infozyn_sona.feed.feed2018_Adapters.Feed2018_FundedProject_Adapter;
import com.example.infozyn_sona.feed.sonaNews;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Feed2018_FundedProject extends AppCompatActivity {

    RecyclerView recyclerView_2018_fp;
    DatabaseReference reference_2018_fp;
    Feed2018_FundedProject_Adapter adapter_2018_fp;
    ArrayList<sonaNews> arrayList_2018_fp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed2018_funded_project);
        recyclerView_2018_fp=findViewById(R.id.recyclerView_feed2018_FundedProject);
        reference_2018_fp= FirebaseDatabase.getInstance().getReference("Notice").child("2018").child("Funded Projects");
        recyclerView_2018_fp.setHasFixedSize(true);
        recyclerView_2018_fp.setLayoutManager(new LinearLayoutManager(this));

        arrayList_2018_fp=new ArrayList<>();
        adapter_2018_fp = new Feed2018_FundedProject_Adapter(arrayList_2018_fp,this);
        recyclerView_2018_fp.setAdapter(adapter_2018_fp);

        reference_2018_fp.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    sonaNews user =dataSnapshot.getValue(sonaNews.class);
                    arrayList_2018_fp.add(user);
                }
                adapter_2018_fp.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Feed2018_FundedProject.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}