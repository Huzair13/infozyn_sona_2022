package com.example.infozyn_sona.feed.delete.Feed2020_d_Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infozyn_sona.R;
import com.example.infozyn_sona.feed.delete.Feed2020_d.FeedDel2020_awards;
import com.example.infozyn_sona.feed.delete.Feed2021_d.FeedDel2021_awards;
import com.example.infozyn_sona.feed.delete.Feed2021_d_Adapters.Feed2021_Awards_del_Adapter;
import com.example.infozyn_sona.feed.delete.Update_Feed.update_2020.UpdateFeed2020_Awards;
import com.example.infozyn_sona.feed.delete.Update_Feed.update_2021.UpdateFeed2021_Awards;
import com.example.infozyn_sona.feed.sonaNews;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ru.embersoft.expandabletextview.ExpandableTextView;

public class Feed2020_Awards_del_Adapter extends RecyclerView.Adapter<Feed2020_Awards_del_Adapter.Viewholder2020_awards_d> {

    private ArrayList<sonaNews> arrayList;
    private Context context;

    AlertDialog.Builder builder;

    Feed2020_Awards_del_Adapter.OnItemClickListerner onItemClickListerner;
    DatabaseReference reference;

    boolean testclick=false;

    public Feed2020_Awards_del_Adapter(ArrayList<sonaNews>arrayList,Context context){

        this.arrayList =arrayList;
        this.context=context;

    }

    @NonNull
    @Override
    public Feed2020_Awards_del_Adapter.Viewholder2020_awards_d onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_news_delete,parent,false);
        return new Feed2020_Awards_del_Adapter.Viewholder2020_awards_d(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Feed2020_Awards_del_Adapter.Viewholder2020_awards_d holder, int position) {

        sonaNews sonaNews=arrayList.get(position);

        DatabaseReference Likref= FirebaseDatabase.getInstance().getReference("Likes");
        DatabaseReference Delref=FirebaseDatabase.getInstance().getReference("Notice").child("2020").child("Awards");

        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        String userid=firebaseUser.getUid();
        String postkey= sonaNews.getKey();

        holder.getLikebuttonstatus_2020_Awards_del(postkey,userid);

        holder.Like_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testclick=true;
                Likref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(testclick==true){
                            if(snapshot.child(postkey).hasChild(userid)){
                                Likref.child(postkey).child(userid).removeValue();
                                testclick=false;
                            }
                            else{
                                Likref.child(postkey).child(userid).setValue(true);
                                testclick=false;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        holder.update_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, UpdateFeed2020_Awards.class);
                intent.putExtra("titleiv",sonaNews.getTitleiv());
                intent.putExtra("message",sonaNews.getMessage());
                intent.putExtra("postimage",sonaNews.getPostimage());
                intent.putExtra("key",sonaNews.getKey());
                context.startActivity(intent);
            }
        });

        holder.del_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder=new AlertDialog.Builder(context);
                builder.setTitle("Alert").setMessage("Are you sure to delete the post?")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Delref.child(postkey).removeValue()
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show();

                                                Intent intent=new Intent(context, FeedDel2020_awards.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                context.startActivity(intent);

                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(context, "Something Went Wrong !!!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .show();

            }
        });


        holder.title_d.setText(sonaNews.getTitleiv());
        holder.message_d.setText(sonaNews.getMessage());
        holder.Date_d.setText(sonaNews.getDate());
        holder.Time_d.setText(sonaNews.getTime());

        holder.message_d.setOnStateChangeListener(new ExpandableTextView.OnStateChangeListener() {
            @Override
            public void onStateChange(boolean isShrink) {
                sonaNews contentItems=arrayList.get(position);
                contentItems.setShrink(isShrink);
                arrayList.set(position,contentItems);
            }
        });
        holder.message_d.setText(sonaNews.getMessage());
        holder.message_d.resetState(sonaNews.isShrink());

        try {
            Picasso.get().load(sonaNews.getPostimage()).into(holder.postimage_d);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Viewholder2020_awards_d extends RecyclerView.ViewHolder {

        private ImageView profileimage_d;
        private ImageView postimage_d;
        private TextView title_d;
        private ExpandableTextView message_d;
        private ImageView del_d,update_d;
        private TextView Date_d;
        private TextView Time_d;
        private ImageView Like_d;
        private TextView like_txt_d;

        public Viewholder2020_awards_d(@NonNull View itemView) {
            super(itemView);

            profileimage_d=itemView.findViewById(R.id.ivprofile_d);
            postimage_d=itemView.findViewById(R.id.ivpost_d);
            title_d=itemView.findViewById(R.id.title_new_d);
            message_d=itemView.findViewById(R.id.ivmessage_d);
            del_d=itemView.findViewById(R.id.iv_delete);
            update_d=itemView.findViewById(R.id.iv_update);
            Like_d=itemView.findViewById(R.id.ivlike_d);
            Date_d=itemView.findViewById(R.id.ivDate_d);
            Time_d=itemView.findViewById(R.id.ivTime_d);
            like_txt_d=itemView.findViewById(R.id.like_txt_d);

        }
        public void getLikebuttonstatus_2020_Awards_del(final String postkey,final String userid){
            reference= FirebaseDatabase.getInstance().getReference("Likes");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.child(postkey).hasChild(userid)){
                        int Likecount=(int)snapshot.child(postkey).getChildrenCount();
                        like_txt_d.setText(Likecount+" Likes");
                        Like_d.setImageResource(R.drawable.ic_thumb_blue);
                    }
                    else{
                        int Likecount=(int)snapshot.child(postkey).getChildrenCount();
                        like_txt_d.setText(Likecount+" Likes");
                        Like_d.setImageResource(R.drawable.ic_baseline_thumb_up_24);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
    public interface OnItemClickListerner{
        void onClick(int position);
    }
    public void setOnItemClickListerner(Feed2020_Awards_del_Adapter.OnItemClickListerner onItemClickListerner){
        this.onItemClickListerner=onItemClickListerner;
    }
}
