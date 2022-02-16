package com.example.infozyn_sona;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.infozyn_sona.faculty.UpdateFaculty;
import com.example.infozyn_sona.feed.UploadNotice;
import com.example.infozyn_sona.feed.delete.FeedDel_category;
import com.example.infozyn_sona.gallery.UploadImage;
import com.example.infozyn_sona.homepage.home_page;

public class AdminPanel extends AppCompatActivity implements View.OnClickListener {

    CardView uploadNotice,addGalleryImage,addEbook,faculty,del_notice;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        uploadNotice=findViewById(R.id.addNotice);
        addGalleryImage=findViewById(R.id.addGalleryImage);
        addEbook=findViewById(R.id.addEbook);
        faculty=findViewById(R.id.faculty);
        del_notice=findViewById(R.id.delete_notice_admin_panel);


        uploadNotice.setOnClickListener(this);
        addGalleryImage.setOnClickListener(this);
        addEbook.setOnClickListener(this);
        faculty.setOnClickListener(this);
        del_notice.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.addNotice:
                Intent intent = new Intent(AdminPanel.this, UploadNotice.class);
                startActivity(intent);
                break;
            case R.id.addGalleryImage:
                intent=new Intent(AdminPanel.this, UploadImage.class);
                startActivity(intent);
                break;
            case R.id.addEbook:
                intent=new Intent(AdminPanel.this,UploadPdfActivity.class);
                startActivity(intent);
                break;
            case R.id.faculty:
                intent=new Intent(AdminPanel.this, UpdateFaculty.class);
                startActivity(intent);
                break;
            case R.id.delete_notice_admin_panel:
                intent=new Intent(AdminPanel.this, FeedDel_category.class);
                startActivity(intent);
                break;

        }
    }
}