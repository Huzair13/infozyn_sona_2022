package com.example.infozyn_sona.feed.delete.Update_Feed.update_2019;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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

import com.example.infozyn_sona.feed.delete.Feed2019_d.FeedDel2019_awards;
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

public class UpdateFeed2019_Awards extends AppCompatActivity {

    private CardView update_notice_img;
    private ImageView notice;
    private EditText update_title_txt,update_message_txt;
    private Button update_notice_btn;

    private String title,message,image;
    private final int REQ=1;
    private Bitmap bitmap=null;
    private ProgressDialog pd;
    private StorageReference storageReference;
    private DatabaseReference reference;
    private String downloadUrl,uniquekey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_feed2019_awards);

        title=getIntent().getStringExtra("titleiv");
        message=getIntent().getStringExtra("message");
        image=getIntent().getStringExtra("postimage");

        uniquekey=getIntent().getStringExtra("key");

        pd=new ProgressDialog(this);

        update_title_txt=findViewById(R.id.noticeTitle_update2019_awards);
        update_message_txt=findViewById(R.id.noticeMessage_update2019_awards);
        update_notice_btn=findViewById(R.id.uploadNoticeBtn_update2019_awards);
        update_notice_img=findViewById(R.id.addimage_update2019_awards);
        notice=findViewById(R.id.noticeImageView_update2019_awards);

        reference= FirebaseDatabase.getInstance().getReference().child("Notice").child("2019").child("Awards");
        storageReference = FirebaseStorage.getInstance().getReference();

        try {
            Picasso.get().load(image).into(notice);
        } catch (Exception e) {
            e.printStackTrace();
        }

        update_title_txt.setText(title);
        update_message_txt.setText(message);

        update_notice_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        update_notice_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title=update_title_txt.getText().toString();
                message=update_message_txt.getText().toString();
                checkValidation();
            }
        });
    }

    private void checkValidation() {
        if(title.isEmpty()){
            update_title_txt.setError("Empty");
            update_title_txt.requestFocus();
        }else if(message.isEmpty()) {
            update_message_txt.setError("Empty");
            update_message_txt.requestFocus();
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
        filepath=storageReference.child("Notice").child(finalimg+"jpg");
        final UploadTask uploadTask=filepath.putBytes(finalimg);
        uploadTask.addOnCompleteListener(UpdateFeed2019_Awards.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
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
                    Toast.makeText(UpdateFeed2019_Awards.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void openGallery() {
        Intent pickimage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickimage, REQ);
    }

    private void UpdateData(String s) {
        HashMap hp=new HashMap();
        hp.put("titleiv",title);
        hp.put("message",message);
        hp.put("postimage",s);


        reference.child(uniquekey).updateChildren(hp).addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                pd.dismiss();
                Toast.makeText(UpdateFeed2019_Awards.this, "Post Updated Successfully", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(UpdateFeed2019_Awards.this, FeedDel2019_awards.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(UpdateFeed2019_Awards.this, "Something Went Wrong!!", Toast.LENGTH_SHORT).show();
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
            notice.setImageBitmap(bitmap);
        }
    }
}