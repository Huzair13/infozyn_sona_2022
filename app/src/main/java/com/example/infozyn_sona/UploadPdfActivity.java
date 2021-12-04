package com.example.infozyn_sona;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class UploadPdfActivity extends AppCompatActivity {

    private CardView addpdf;
    private EditText pdftitle;
    private Button uploadPdfBtn;
    private TextView pdftextview;
    private String pdfname,title;

    private final int REQ=1;
    private Uri pdfData;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    String downloadUrl = "";
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_pdf);

        databaseReference= FirebaseDatabase.getInstance().getReference();
        storageReference= FirebaseStorage.getInstance().getReference();
        pd=new ProgressDialog(this);

        pd=new ProgressDialog(this);

        addpdf = findViewById(R.id.addpdf);
        pdftitle = findViewById(R.id.pdfTitle);
        uploadPdfBtn=findViewById(R.id.uploadPdfBtn);
        pdftextview=findViewById(R.id.pdftextview);

        addpdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        uploadPdfBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title=pdftitle.getText().toString();
                if(title.isEmpty()){
                    pdftitle.requestFocus();
                }else if(pdfData==null){
                    Toast.makeText(UploadPdfActivity.this, "Pleae Upload Pdf", Toast.LENGTH_SHORT).show();
                }else{
                    uploadPdf();
                }
            }
        });

    }

    private void uploadPdf() {
        pd.setTitle("Please Wait....");
        pd.setMessage("Uploading Pdf....");
        pd.show();
        StorageReference reference;
        reference = storageReference.child("pdf/"+pdfname+"-"+System.currentTimeMillis()+".pdf");
        reference.putFile(pdfData)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> uritask =taskSnapshot.getStorage().getDownloadUrl();
                        while(uritask.isComplete());
                        Uri uri=uritask.getResult();
                        uploadData(String.valueOf(uri));
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(UploadPdfActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void uploadData(String valueOf) {
        String uniquekey=databaseReference.child("pdf").push().getKey();

        HashMap data =new HashMap();
        data.put("Pdf Title",title);
        data.put("PdfUrl",downloadUrl);

        databaseReference.child("pdf").child(uniquekey).setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                pd.dismiss();
                Toast.makeText(UploadPdfActivity.this, "Pdf Uploaded Succesfully", Toast.LENGTH_SHORT).show();
                pdftitle.setText("");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(UploadPdfActivity.this, "Failed to upload Pdf", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openGallery() {
        Intent intent=new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Pdf File"),REQ);
    }



    @SuppressLint("Range")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQ && resultCode==RESULT_OK){
            pdfData=data.getData();

            if(pdfData.toString().startsWith("content://")){
                Cursor cursor=null;
                try {
                    cursor=UploadPdfActivity.this.getContentResolver().query(pdfData,null,null,null,null);
                    if(cursor!=null && cursor.moveToFirst()){
                        pdfname=cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }else if(pdfData.toString().startsWith("file://")){
                pdfname=new File(pdfData.toString()).getName();

            }
            pdftextview.setText(pdfname);
            
        }
    }
}