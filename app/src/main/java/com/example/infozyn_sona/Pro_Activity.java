package com.example.infozyn_sona;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.infozyn_sona.faculty.TeacherData;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.SignInAccount;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.concurrent.locks.ReadWriteLock;

import de.hdodenhof.circleimageview.CircleImageView;

public class Pro_Activity extends AppCompatActivity {
    private EditText TextViewFullName,TextViewMobile;
    private TextView TextViewWelcome,TextViewEmail;
    private ProgressDialog pd;
    private String name,email,phone;
    private Button update;
    private CircleImageView profile_image;
    private FirebaseAuth authProfile,auth;
    DatabaseReference databaseReference;
    FirebaseStorage storage;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro);

        TextViewWelcome=findViewById(R.id.Prof_welcome);
        TextViewFullName=findViewById(R.id.Profile_name);
        TextViewEmail=findViewById(R.id.Profile_Email);
        TextViewMobile=findViewById(R.id.Profile_Phone);
        profile_image=findViewById(R.id.Profile_image);


        pd=new ProgressDialog(this);

        //update profile
        databaseReference=FirebaseDatabase.getInstance().getReference("Sona_users");

        //setting profile picture

        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        storage=FirebaseStorage.getInstance();

        update=findViewById(R.id.profile_submit_btn);
        GoogleSignInAccount signInAccount= GoogleSignIn.getLastSignedInAccount(this);
        String signin_email=signInAccount.getEmail();


        FirebaseUser usernew= FirebaseAuth.getInstance().getCurrentUser();
        String usernew_email=usernew.getEmail();


        database.getReference().child("Sona_users").child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if(signin_email.equals(usernew_email)){
                            Glide.with(Pro_Activity.this).load(signInAccount.getPhotoUrl()).into(profile_image);
                        }else if(!signin_email.equals(usernew_email)){
                            UserDetails userDetails = snapshot.getValue(UserDetails.class);
                            Glide.with(Pro_Activity.this).load(userDetails.getProfileImg()).into(profile_image);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUserProfile();
            }
        });

        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,33);
            }
        });



        //display user
        authProfile=FirebaseAuth.getInstance();
        FirebaseUser firebaseUser=authProfile.getCurrentUser();

        if(firebaseUser==null){
            Toast.makeText(Pro_Activity.this, "Something Went Wrong ! User Details are not available at the moment", Toast.LENGTH_SHORT).show();
        }else{
            showUserProfile(firebaseUser);
        }

    }
    //UPDATE USER PROFILE
    private void updateUserProfile() {
        GoogleSignInAccount signInAccount= GoogleSignIn.getLastSignedInAccount(this);
        FirebaseUser usernew= FirebaseAuth.getInstance().getCurrentUser();


        if (isNameChanged() || isphonechanged()) {
            Toast.makeText(this, "Data has been updated", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Data is same and cannot be updated", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isNameChanged() {

        if(name==null && TextViewMobile.getText().toString()!=null){
            databaseReference.child(FirebaseAuth.getInstance().getUid()).child("name").setValue(TextViewFullName.getText().toString());
            return true;
        }
        else if(TextViewFullName.getText().toString()==null && name!=null){
            if(!name.equals(TextViewFullName.getText().toString())){
                databaseReference.child(FirebaseAuth.getInstance().getUid()).child("name").setValue("null");
                return true;
            }
            else{
                return false;
            }
        }

        else{
            if(!name.equals(TextViewFullName.getText().toString())){
                databaseReference.child(FirebaseAuth.getInstance().getUid()).child("name").setValue(TextViewFullName.getText().toString());
                return true;
            }
            else{
                return false;
            }
        }

    }


    private boolean isphonechanged() {

        if(phone==null && TextViewMobile.getText().toString()!=null){
            databaseReference.child(FirebaseAuth.getInstance().getUid()).child("mobile").setValue(TextViewMobile.getText().toString());
            return true;
        }

        else if(TextViewMobile.getText().toString()==null && phone!=null){

            if(!phone.equals(TextViewMobile.getText().toString())){
                databaseReference.child(FirebaseAuth.getInstance().getUid()).child("mobile").setValue("null");
                return true;
            }
            else{
                return false;
            }

        }
        else{
            if (!phone.equals(TextViewMobile.getText().toString())) {
                databaseReference.child(FirebaseAuth.getInstance().getUid()).child("mobile").setValue(TextViewMobile.getText().toString());
                return true;
            }
            else {
                return false;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data.getData()!=null){
            pd.setMessage("Updating Profile Picture...");
            pd.show();
            Uri profileUri=data.getData();
            profile_image.setImageURI(profileUri);
            final StorageReference reference=storage.getReference().child("profile_picture")
                    .child(FirebaseAuth.getInstance().getUid());
            reference.putFile(profileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    pd.dismiss();
                    Toast.makeText(Pro_Activity.this, "Uploaded", Toast.LENGTH_SHORT).show();

                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            pd.dismiss();
                            database.getReference().child("Sona_users").child(FirebaseAuth.getInstance().getUid())
                                    .child("profileImg").setValue(uri.toString());
                            Toast.makeText(Pro_Activity.this, "Profile Picture Uploaded", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            });
        }

    }

    private void showUserProfile(FirebaseUser firebaseUser) {
        String userID=firebaseUser.getUid();

        //EXTRACTING USER REFERENCE FROM DATABASE FOR "REGISTERED USERS"

        GoogleSignInAccount signInAccount= GoogleSignIn.getLastSignedInAccount(this);
        FirebaseUser usernew= FirebaseAuth.getInstance().getCurrentUser();
        String Sign_in_email=signInAccount.getEmail();
        String usernew_email=usernew.getEmail();

        DatabaseReference referenceProfile=FirebaseDatabase.getInstance().getReference("Sona_users");
        referenceProfile.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserDetails userDetails=snapshot.getValue(UserDetails.class);

                if(userDetails==null){
                    phone=userDetails.mobile;
                    TextViewFullName.setText(signInAccount.getDisplayName());
                    TextViewEmail.setText(signInAccount.getEmail());
                    if(userDetails.getMobile()!="null"){
                        TextViewMobile.setText(phone);
                    }
                    TextViewWelcome.setText("Welcome, "+signInAccount.getDisplayName()+"!");

                }else{
                    if(userDetails!=null){
                        name=userDetails.name;
                        email=firebaseUser.getEmail();
                        phone=userDetails.mobile;

                        TextViewWelcome.setText("Welcome, "+name+"!");
                        TextViewFullName.setText(name);
                        TextViewEmail.setText(email);
                        TextViewMobile.setText(phone);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(Pro_Activity.this, "Something Went Wrong !!! ", Toast.LENGTH_SHORT).show();
            }
        });

    }
}