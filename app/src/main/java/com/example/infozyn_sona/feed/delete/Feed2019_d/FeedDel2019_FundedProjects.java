package com.example.infozyn_sona.feed.delete.Feed2019_d;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.infozyn_sona.R;
import com.example.infozyn_sona.feed.delete.Feed2019_d_Adapters.Feed2019_FundedProjects_del_Adapter;
import com.example.infozyn_sona.feed.delete.Feed2020_d.FeedDel2020_FundedProjects;
import com.example.infozyn_sona.feed.delete.Feed2020_d_Adapters.Feed2020_FundedProjects_del_Adapter;
import com.example.infozyn_sona.feed.sonaNews;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FeedDel2019_FundedProjects extends AppCompatActivity {

    RecyclerView recyclerView_2019_fp_d;
    DatabaseReference reference_2019_fp_d;
    Feed2019_FundedProjects_del_Adapter adapter_2019_fp_d;
    ArrayList<sonaNews> arrayList_2019_fp_d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_del2019_funded_projects);

        recyclerView_2019_fp_d=findViewById(R.id.recyclerView_Feed2019_fp_d);
        reference_2019_fp_d= FirebaseDatabase.getInstance().getReference("Notice").child("2019").child("Funded Projects");
        recyclerView_2019_fp_d.setHasFixedSize(true);
        recyclerView_2019_fp_d.setLayoutManager(new LinearLayoutManager(this));
        arrayList_2019_fp_d=new ArrayList<>();
        adapter_2019_fp_d = new Feed2019_FundedProjects_del_Adapter(arrayList_2019_fp_d,this);
        recyclerView_2019_fp_d.setAdapter(adapter_2019_fp_d);

        reference_2019_fp_d.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    sonaNews user =dataSnapshot.getValue(sonaNews.class);
                    arrayList_2019_fp_d.add(user);
                }
                adapter_2019_fp_d.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(FeedDel2019_FundedProjects.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}