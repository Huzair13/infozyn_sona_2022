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

public class DisplayTeacherActivity extends AppCompatActivity {

    private RecyclerView csDep,ITDep,ADSDep,ECEDep,EEEDep,MECHDep,FTDep;
    private LinearLayout csNoData,ITNoData,ECENoData,EEENoData,MECHNoData,FTNoData,ADSNoData;
    private List<TeacherData>list1,list2,list3,list4,list5,list6,list7;
    private TeacherDisplayAdapter adapter;


    private DatabaseReference reference,dbref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_teacher);

        csDep=findViewById(R.id.csDep_display);
        MECHDep=findViewById(R.id.MECHDep_display);
        ITDep=findViewById(R.id.ITDep_display);
        FTDep=findViewById(R.id.FTDep_display);
        ECEDep=findViewById(R.id.ECEDep_display);
        EEEDep=findViewById(R.id.EEEDep_display);
        ADSDep=findViewById(R.id.ADSDep_display);

        FTNoData = findViewById(R.id.FTNoData_display);
        MECHNoData=findViewById(R.id.MECHNoData_display);
        EEENoData = findViewById(R.id.EEENoData_display);
        ECENoData = findViewById(R.id.ECENoData_display);
        ITNoData = findViewById(R.id.ITNoData_display);
        csNoData = findViewById(R.id.csNoData_display);
        ADSNoData = findViewById(R.id.ADSNoData_display);

        reference= FirebaseDatabase.getInstance().getReference().child("Teacher");

        csDep();
        MECHDep();
        ADSDep();
        ITDep();
        ECEDep();
        EEEDep();
        FTDep();


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
                    csDep.setLayoutManager(new LinearLayoutManager(DisplayTeacherActivity.this));
                    adapter= new TeacherDisplayAdapter(list1,DisplayTeacherActivity.this,"CSE");
                    csDep.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(DisplayTeacherActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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
                    ITDep.setLayoutManager(new LinearLayoutManager(DisplayTeacherActivity.this));
                    adapter=new TeacherDisplayAdapter(list2,DisplayTeacherActivity.this,"IT");
                    ITDep.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(DisplayTeacherActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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
                    ADSDep.setLayoutManager(new LinearLayoutManager(DisplayTeacherActivity.this));
                    adapter=new TeacherDisplayAdapter(list3,DisplayTeacherActivity.this,"ADS");
                    ADSDep.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(DisplayTeacherActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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
                    ECEDep.setLayoutManager(new LinearLayoutManager(DisplayTeacherActivity.this));
                    adapter=new TeacherDisplayAdapter(list4,DisplayTeacherActivity.this,"ECE");
                    ECEDep.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(DisplayTeacherActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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
                    EEEDep.setLayoutManager(new LinearLayoutManager(DisplayTeacherActivity.this));
                    adapter=new TeacherDisplayAdapter(list5,DisplayTeacherActivity.this,"EEE");
                    EEEDep.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(DisplayTeacherActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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
                    MECHDep.setLayoutManager(new LinearLayoutManager(DisplayTeacherActivity.this));
                    adapter=new TeacherDisplayAdapter(list6,DisplayTeacherActivity.this,"MECH");
                    MECHDep.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(DisplayTeacherActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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
                    FTDep.setLayoutManager(new LinearLayoutManager(DisplayTeacherActivity.this));
                    adapter=new TeacherDisplayAdapter(list7,DisplayTeacherActivity.this,"FT");
                    FTDep.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(DisplayTeacherActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}