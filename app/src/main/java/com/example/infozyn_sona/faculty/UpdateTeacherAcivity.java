package com.example.infozyn_sona.faculty;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.infozyn_sona.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class UpdateTeacherAcivity extends AppCompatActivity {

    private ImageView UpdateTeacherImage;
    private EditText UpdateTeacherName,UpdateTeacherEmail,UpdateTeacherPost;
    private Button updateTeacherButton,DeleteTeacherButton;

    private String name,email,image,post;
    private final int REQ=1;
    private Bitmap bitmap=null;
    private ProgressDialog pd;
    private  StorageReference storageReference;
    private DatabaseReference reference;
    private String downloadUrl,uniquekey,category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_teacher_acivity);

        name=getIntent().getStringExtra("name");
        email=getIntent().getStringExtra("email");
        post=getIntent().getStringExtra("post");
        image=getIntent().getStringExtra("image");

        uniquekey=getIntent().getStringExtra("key");
        category=getIntent().getStringExtra("category");

        pd=new ProgressDialog(this);

        UpdateTeacherImage=findViewById(R.id.UpdateTeacherImage);
        UpdateTeacherName=findViewById(R.id.UpdateTeacherName);
        UpdateTeacherEmail=findViewById(R.id.UpdateTeacherEmail);
        UpdateTeacherPost=findViewById(R.id.UpdateTeacherPost);
        updateTeacherButton=findViewById(R.id.updateTeacherButton);
        DeleteTeacherButton=findViewById(R.id.DeleteTeacherButton);

        reference= FirebaseDatabase.getInstance().getReference().child("Teacher");
        storageReference = FirebaseStorage.getInstance().getReference();


        try {
            Picasso.get().load(image).into(UpdateTeacherImage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        UpdateTeacherEmail.setText(email);
        UpdateTeacherPost.setText(post);
        UpdateTeacherName.setText(name);

        UpdateTeacherImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        updateTeacherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=UpdateTeacherName.getText().toString();
                email=UpdateTeacherEmail.getText().toString();
                post=UpdateTeacherPost.getText().toString();
                checkValidation();
            }
        });

        DeleteTeacherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData();
            }
        });

    }

    private void openGallery() {
        Intent pickimage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickimage, REQ);
    }

    private void deleteData() {

        reference.child(category).child(uniquekey).removeValue()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(UpdateTeacherAcivity.this, "Teacher Deleted Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(UpdateTeacherAcivity.this,UpdateFaculty.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UpdateTeacherAcivity.this, "Something Went Wrong !!!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void checkValidation() {
        if(name.isEmpty()){
            UpdateTeacherName.setError("Empty");
            UpdateTeacherName.requestFocus();
        }else if(email.isEmpty()) {
            UpdateTeacherEmail.setError("Empty");
            UpdateTeacherEmail.requestFocus();
        }else if(post.isEmpty()) {
            UpdateTeacherPost.setError("Empty");
            UpdateTeacherPost.requestFocus();
        }else if(bitmap==null){
            UpdateData(image);
        }else{
            uploadImage();
        }
    }

    private void uploadImage() {

        pd.setMessage("Uploading....");
        pd.show();
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,50,baos);
        byte[] finalimg = baos.toByteArray();
        final StorageReference filepath;
        filepath=storageReference.child("Teacher").child(finalimg+"jpg");
        final UploadTask uploadTask=filepath.putBytes(finalimg);
        uploadTask.addOnCompleteListener(UpdateTeacherAcivity.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if(task.isSuccessful()){
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    downloadUrl=String.valueOf(uri);
                                    UpdateData(downloadUrl);
                                }
                            });
                        }
                    });
                }else{
                    pd.dismiss();
                    Toast.makeText(UpdateTeacherAcivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void UpdateData(String s) {

        HashMap hp=new HashMap();
        hp.put("name",name);
        hp.put("email",email);
        hp.put("post",post);
        hp.put("image",s);


        reference.child(category).child(uniquekey).updateChildren(hp).addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                pd.dismiss();
                Toast.makeText(UpdateTeacherAcivity.this, "Teacher Updated Successfully", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(UpdateTeacherAcivity.this,UpdateFaculty.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(UpdateTeacherAcivity.this, "Something Went Wrong!!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQ && resultCode==RESULT_OK){
            Uri uri=data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            UpdateTeacherImage.setImageBitmap(bitmap);
        }
    }
}