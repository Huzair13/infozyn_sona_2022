package com.example.infozyn_sona.homepage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.infozyn_sona.AboutSona;
import com.example.infozyn_sona.AdminPanel;
import com.example.infozyn_sona.Admin_Access;
import com.example.infozyn_sona.feed.Feed2021.Category_2021;
import com.example.infozyn_sona.feed.Feed2018.Category;
import com.example.infozyn_sona.feed.Feed2019.Category_2019;
import com.example.infozyn_sona.faculty.DisplayTeacherActivity;
import com.example.infozyn_sona.MainActivity;
import com.example.infozyn_sona.Pro_Activity;
import com.example.infozyn_sona.R;
import com.example.infozyn_sona.UserDetails;
import com.example.infozyn_sona.feed.Feed2020.Category_2020;
import com.example.infozyn_sona.feed.feed2022.Category_2022;
import com.example.infozyn_sona.gallery.Gallery;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import de.hdodenhof.circleimageview.CircleImageView;

public class home_page extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {

    SliderView sliderView;

    int[] images={R.drawable.sona_slider1,
    R.drawable.sona_slider2,
    R.drawable.sona_slider3,
    R.drawable.sona_slider4,
    R.drawable.sona_slider5};


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    BottomNavigationView bottomNavigationView;
    AlertDialog.Builder builder;

    DatabaseReference databaseReference;
    FirebaseAuth mAuth,authProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        builder=new AlertDialog.Builder(this);

        authProfile=FirebaseAuth.getInstance();
        FirebaseUser firebaseUser=authProfile.getCurrentUser();


        sliderView=findViewById(R.id.image_slider);

        SliderAdapter sliderAdapter=new SliderAdapter(images);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();



        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        toolbar=findViewById(R.id.toolbar);
        bottomNavigationView=findViewById(R.id.bot_view);

        mAuth=FirebaseAuth.getInstance();

        setSupportActionBar(toolbar);


        navigationView.bringToFront();
        ActionBarDrawerToggle toggle =new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(home_page.this);

        View headerview=navigationView.getHeaderView(0);
        TextView headername= headerview.findViewById(R.id.UserNameDash);
        TextView headeremail=headerview.findViewById(R.id.UserEmailDash);
        CircleImageView headerImg=headerview.findViewById(R.id.Profile_image);





        databaseReference=FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Sona_users").child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        GoogleSignInAccount signInAccount= GoogleSignIn.getLastSignedInAccount(home_page.this);
                        UserDetails userDetails=snapshot.getValue(UserDetails.class);

                        String signin_email=signInAccount.getEmail();
                        String user_email=firebaseUser.getEmail();

                        if(signin_email==user_email){
                            if(userDetails==null) {
                                headername.setText(signInAccount.getDisplayName());
                                headeremail.setText(signInAccount.getEmail());
                                Glide.with(home_page.this).load(signInAccount.getPhotoUrl()).into(headerImg);
                            }
                            else{
                                headername.setText(userDetails.getName());
                                headeremail.setText(firebaseUser.getEmail());
                                if(userDetails.getProfileImg()==null){
                                    Glide.with(home_page.this).load(signInAccount.getPhotoUrl()).into(headerImg);
                                }
                                else{
                                    Glide.with(home_page.this).load(userDetails.getProfileImg()).into(headerImg);
                                }
                            }
                        }
                        else{
                            headername.setText(userDetails.getName());
                            headeremail.setText(userDetails.getEmail());
                            Glide.with(home_page.this).load(userDetails.getProfileImg()).into(headerImg);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(home_page.this, "Something Wrong!!!", Toast.LENGTH_SHORT).show();
                    }
                });


    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user=mAuth.getCurrentUser();
        if(user==null){
            startActivity(new Intent(home_page.this, MainActivity.class));
        }
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
        super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                startActivity(new Intent(home_page.this, home_page.class));
                break;

            case R.id.nav_logout:
                builder.setTitle("Alert")
                        .setMessage("Are you sure to exit the app?")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mAuth.signOut();
                                startActivity(new Intent(home_page.this, MainActivity.class));
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .show();
                break;

            case R.id.nav_gallery:
                startActivity(new Intent(home_page.this, Gallery.class));
                break;
            case R.id.nav_share:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String Body = "Download this App";
                String Sub = "www.sonatech.ac.in";
                intent.putExtra(Intent.EXTRA_TEXT, Body);
                intent.putExtra(Intent.EXTRA_TEXT, Sub);
                startActivity(Intent.createChooser(intent, "Share using"));
                break;
            case R.id.bot_feed:
                startActivity(new Intent(home_page.this, Category_2022.class));
                break;
            case R.id.bot_location:
                startActivity(new Intent(home_page.this, AboutSona.class));
                break;
            case R.id.nav_profile:
                startActivity(new Intent(home_page.this, Pro_Activity.class));
                break;
            case R.id.nav_faculty:
                startActivity(new Intent(home_page.this, DisplayTeacherActivity.class));
                break;
            case R.id.nav_admin:
                startActivity(new Intent(home_page.this, Admin_Access.class));
                break;
            case R.id.News_2018:
                startActivity(new Intent(home_page.this, Category.class));
                break;
            case R.id.News_2019:
                startActivity(new Intent(home_page.this, Category_2019.class));
                break;
            case R.id.News_2020:
                startActivity(new Intent(home_page.this, Category_2020.class));
                break;
            case R.id.News_2021:
                startActivity(new Intent(home_page.this, Category_2021.class));
                break;
            case R.id.News_2022:
                startActivity(new Intent(home_page.this, Category_2022.class));
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);


        return true;

    }


}