package com.example.infozyn_sona.feed;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.infozyn_sona.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class News extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference reference;
    RecyclerAdapter adapter;
    ArrayList<sonaNews> arrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        recyclerView=findViewById(R.id.recyclerView);
        reference=FirebaseDatabase.getInstance().getReference("Notice");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        arrayList=new ArrayList<>();
        adapter = new RecyclerAdapter(arrayList,this);
        recyclerView.setAdapter(adapter);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    sonaNews user =dataSnapshot.getValue(sonaNews.class);
                    arrayList.add(user);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(News.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}