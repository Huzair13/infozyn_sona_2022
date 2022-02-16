package com.example.infozyn_sona.feed.delete.Feed2022_d;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.infozyn_sona.R;
import com.example.infozyn_sona.feed.delete.Feed2021_d.FeedDel2021_FundedProjects;
import com.example.infozyn_sona.feed.delete.Feed2021_d_Adapters.Feed2021_FundedProjects_del_Adapter;
import com.example.infozyn_sona.feed.delete.Feed2022_d_Adapters.Feed2022_FundedProjects_del_Adapter;
import com.example.infozyn_sona.feed.sonaNews;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FeedDel2022_FundedProjects extends AppCompatActivity {

    RecyclerView recyclerView_2022_fp_d;
    DatabaseReference reference_2022_fp_d;
    Feed2022_FundedProjects_del_Adapter adapter_2022_fp_d;
    ArrayList<sonaNews> arrayList_2022_fp_d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_del2022_funded_projects);

        recyclerView_2022_fp_d=findViewById(R.id.recyclerView_Feed2022_fp_d);
        reference_2022_fp_d= FirebaseDatabase.getInstance().getReference("Notice").child("2022").child("Funded Projects");
        recyclerView_2022_fp_d.setHasFixedSize(true);
        recyclerView_2022_fp_d.setLayoutManager(new LinearLayoutManager(this));
        arrayList_2022_fp_d=new ArrayList<>();
        adapter_2022_fp_d = new Feed2022_FundedProjects_del_Adapter(arrayList_2022_fp_d,this);
        recyclerView_2022_fp_d.setAdapter(adapter_2022_fp_d);

        reference_2022_fp_d.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    sonaNews user =dataSnapshot.getValue(sonaNews.class);
                    arrayList_2022_fp_d.add(user);
                }
                adapter_2022_fp_d.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(FeedDel2022_FundedProjects.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}