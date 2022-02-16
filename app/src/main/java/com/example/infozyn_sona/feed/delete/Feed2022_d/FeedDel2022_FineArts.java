package com.example.infozyn_sona.feed.delete.Feed2022_d;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.infozyn_sona.R;
import com.example.infozyn_sona.feed.delete.Feed2022_d_Adapters.Feed2022_FineArts_del_Adapter;
import com.example.infozyn_sona.feed.sonaNews;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FeedDel2022_FineArts extends AppCompatActivity {

    RecyclerView recyclerView1;
    DatabaseReference reference1;
    Feed2022_FineArts_del_Adapter adapter1;
    ArrayList<sonaNews> arrayList1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_notice);

        recyclerView1=findViewById(R.id.recyclerView_d);
        reference1= FirebaseDatabase.getInstance().getReference("Notice").child("2022").child("FineArts");
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        arrayList1=new ArrayList<>();
        adapter1 = new Feed2022_FineArts_del_Adapter(arrayList1,this);
        recyclerView1.setAdapter(adapter1);

        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    sonaNews user =dataSnapshot.getValue(sonaNews.class);
                    arrayList1.add(user);
                }
                adapter1.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(FeedDel2022_FineArts.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}