package com.example.infozyn_sona.faculty;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infozyn_sona.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TeacherDisplayAdapter extends RecyclerView.Adapter<TeacherDisplayAdapter.TeacherDisplayViewAdapter> {


    private List<TeacherData> list;
    private Context context;
    private String category;

    public TeacherDisplayAdapter(List<TeacherData> list, Context context, String category) {
        this.list = list;
        this.context = context;
        this.category=category;
    }

    @NonNull
    @Override
    public TeacherDisplayAdapter.TeacherDisplayViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.faculty_display_view,parent,false);
        return new TeacherDisplayViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherDisplayAdapter.TeacherDisplayViewAdapter holder, int position) {

        TeacherData item=list.get(position);
        holder.name.setText(item.getName());
        holder.email.setText(item.getEmail());
        holder.post.setText(item.getPost());

        try {
            Picasso.get().load(item.getImage()).into(holder.imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TeacherDisplayViewAdapter extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView email;
        private TextView post;
        private ImageView imageView;


        public TeacherDisplayViewAdapter(@NonNull View itemView) {
            super(itemView);

                name=itemView.findViewById(R.id.teacherName_display);
                email=itemView.findViewById(R.id.teacherEmail_display);
                post=itemView.findViewById(R.id.teacherPost_display);
                imageView=itemView.findViewById(R.id.teacherImage_display);

        }
    }
}
