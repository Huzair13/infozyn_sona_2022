package com.example.infozyn_sona.faculty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.infozyn_sona.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UpdateFaculty extends AppCompatActivity {

    FloatingActionButton fab;

    private RecyclerView csDep,ITDep,ADSDep,ECEDep,EEEDep,MECHDep,FTDep;
    private LinearLayout csNoData,ITNoData,ECENoData,EEENoData,MECHNoData,FTNoData,ADSNoData;
    private List<TeacherData>list1,list2,list3,list4,list5,list6,list7;
    private TeacherAdapter adapter;


    private DatabaseReference reference,dbref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_faculty);

        csDep=findViewById(R.id.csDep);
        MECHDep=findViewById(R.id.MECHDep);
        ITDep=findViewById(R.id.ITDep);
        FTDep=findViewById(R.id.FTDep);
        ECEDep=findViewById(R.id.ECEDep);
        EEEDep=findViewById(R.id.EEEDep);
        ADSDep=findViewById(R.id.ADSDep);

        FTNoData = findViewById(R.id.FTNoData);
        MECHNoData=findViewById(R.id.MECHNoData);
        EEENoData = findViewById(R.id.EEENoData);
        ECENoData = findViewById(R.id.ECENoData);
        ITNoData = findViewById(R.id.ITNoData);
        csNoData = findViewById(R.id.csNoData);
        ADSNoData = findViewById(R.id.ADSNoData);

        reference= FirebaseDatabase.getInstance().getReference().child("Teacher");

        csDep();
        MECHDep();
        ADSDep();
        ITDep();
        ECEDep();
        EEEDep();
        FTDep();


        fab=findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UpdateFaculty.this,AddTeacher.class));

            }
        });

    }

    private void csDep() {
        dbref=reference.child("CSE");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list1=new ArrayList<>();
                if(!snapshot.exists()){
                    csNoData.setVisibility(View.VISIBLE);
                    csDep.setVisibility(View.GONE);
                }else{
                    csNoData.setVisibility(View.GONE);
                    csDep.setVisibility(View.VISIBLE);
                    for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                        TeacherData data = dataSnapshot.getValue(TeacherData.class);
                        list1.add(data);
                    }
                    csDep.setHasFixedSize(true);
                    csDep.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter=new TeacherAdapter(list1,UpdateFaculty.this,"CSE");
                    csDep.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(UpdateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void ITDep() {
        dbref=reference.child("IT");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list2=new ArrayList<>();
                if(!snapshot.exists()){
                    ITNoData.setVisibility(View.VISIBLE);
                    ITDep.setVisibility(View.INVISIBLE);
                }else{
                    ITNoData.setVisibility(View.INVISIBLE);
                    ITDep.setVisibility(View.VISIBLE);
                    for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                        TeacherData data =dataSnapshot.getValue(TeacherData.class);
                        list2.add(data);
                    }
                    ITDep.setHasFixedSize(true);
                    ITDep.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter=new TeacherAdapter(list2,UpdateFaculty.this,"IT");
                    ITDep.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(UpdateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void ADSDep() {
        dbref=reference.child("ADS");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list3=new ArrayList<>();
                if(!snapshot.exists()){
                    ADSNoData.setVisibility(View.VISIBLE);
                    ADSDep.setVisibility(View.GONE);
                }else{
                    ADSNoData.setVisibility(View.GONE);
                    ADSDep.setVisibility(View.VISIBLE);
                    for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                        TeacherData data =dataSnapshot.getValue(TeacherData.class);
                        list3.add(data);
                    }
                    ADSDep.setHasFixedSize(true);
                    ADSDep.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter=new TeacherAdapter(list3,UpdateFaculty.this,"ADS");
                    ADSDep.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(UpdateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void ECEDep() {
        dbref=reference.child("ECE");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list4=new ArrayList<>();
                if(!snapshot.exists()){
                    ECENoData.setVisibility(View.VISIBLE);
                    ECEDep.setVisibility(View.GONE);
                }else{
                    ECENoData.setVisibility(View.GONE);
                    ECEDep.setVisibility(View.VISIBLE);
                    for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                        TeacherData data =dataSnapshot.getValue(TeacherData.class);
                        list4.add(data);
                    }
                    ECEDep.setHasFixedSize(true);
                    ECEDep.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter=new TeacherAdapter(list4,UpdateFaculty.this,"ECE");
                    ECEDep.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(UpdateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void EEEDep() {
        dbref=reference.child("EEE");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list5=new ArrayList<>();
                if(!snapshot.exists()){
                    EEENoData.setVisibility(View.VISIBLE);
                    EEEDep.setVisibility(View.GONE);
                }else{
                    EEENoData.setVisibility(View.GONE);
                    EEEDep.setVisibility(View.VISIBLE);
                    for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                        TeacherData data =dataSnapshot.getValue(TeacherData.class);
                        list5.add(data);
                    }
                    EEEDep.setHasFixedSize(true);
                    EEEDep.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter=new TeacherAdapter(list5,UpdateFaculty.this,"EEE");
                    EEEDep.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(UpdateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void MECHDep() {
        dbref=reference.child("MECH");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list6=new ArrayList<>();
                if(!snapshot.exists()){
                    MECHNoData.setVisibility(View.VISIBLE);
                    MECHDep.setVisibility(View.GONE);
                }else{
                    MECHNoData.setVisibility(View.GONE);
                    MECHDep.setVisibility(View.VISIBLE);
                    for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                        TeacherData data =dataSnapshot.getValue(TeacherData.class);
                        list6.add(data);
                    }
                    MECHDep.setHasFixedSize(true);
                    MECHDep.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter=new TeacherAdapter(list6,UpdateFaculty.this,"MECH");
                    MECHDep.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(UpdateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void FTDep() {
        dbref=reference.child("FT");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list7=new ArrayList<>();
                if(!snapshot.exists()){
                    FTNoData.setVisibility(View.VISIBLE);
                    FTDep.setVisibility(View.GONE);
                }else{
                    FTNoData.setVisibility(View.GONE);
                    FTDep.setVisibility(View.VISIBLE);
                    for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                        TeacherData data =dataSnapshot.getValue(TeacherData.class);
                        list7.add(data);
                    }
                    FTDep.setHasFixedSize(true);
                    FTDep.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter=new TeacherAdapter(list7,UpdateFaculty.this,"FT");
                    FTDep.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(UpdateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}