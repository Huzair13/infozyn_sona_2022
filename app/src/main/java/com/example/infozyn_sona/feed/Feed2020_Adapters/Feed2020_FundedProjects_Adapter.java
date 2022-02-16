package com.example.infozyn_sona.feed.Feed2020_Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infozyn_sona.R;
import com.example.infozyn_sona.feed.sonaNews;
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

public class Feed2020_FundedProjects_Adapter extends RecyclerView.Adapter<Feed2020_FundedProjects_Adapter.Viewholder2020_fp> {

    private ArrayList<sonaNews> arrayList;
    private Context context;
    Feed2020_FundedProjects_Adapter.OnItemClickListerner onItemClickListerner;
    DatabaseReference reference;
    boolean testclick=false;

    public Feed2020_FundedProjects_Adapter(ArrayList<sonaNews> arrayList,Context context){
        this.arrayList =arrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public Feed2020_FundedProjects_Adapter.Viewholder2020_fp onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.view_news,parent,false);
        return new Viewholder2020_fp(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Feed2020_FundedProjects_Adapter.Viewholder2020_fp holder, int position) {
        sonaNews sonaNews=arrayList.get(position);

        DatabaseReference Likref= FirebaseDatabase.getInstance().getReference("Likes");
        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        String userid=firebaseUser.getUid();
        String postkey= sonaNews.getKey();

        holder.getLikebuttonstatus_2020_fp(postkey,userid);

        holder.Like_2020_fp.setOnClickListener(new View.OnClickListener() {
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

        holder.title_2020_fp.setText(sonaNews.getTitleiv());
        holder.message_2020_fp.setText(sonaNews.getMessage());
        holder.Date_2020_fp.setText(sonaNews.getDate());
        holder.Time_2020_fp.setText(sonaNews.getTime());
        holder.message_2020_fp.setOnStateChangeListener(new ExpandableTextView.OnStateChangeListener() {
            @Override
            public void onStateChange(boolean isShrink) {
                sonaNews contentItems=arrayList.get(position);
                contentItems.setShrink(isShrink);
                arrayList.set(position,contentItems);
            }
        });
        holder.message_2020_fp.setText(sonaNews.getMessage());
        holder.message_2020_fp.resetState(sonaNews.isShrink());

        try {
            Picasso.get().load(sonaNews.getPostimage()).into(holder.postimage_2020_fp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Viewholder2020_fp extends RecyclerView.ViewHolder {

        ImageView profileimage_2020_fp;
        ImageView postimage_2020_fp;
        TextView title_2020_fp;
        ExpandableTextView message_2020_fp;
        ImageView share_2020_fp;
        TextView Date_2020_fp;
        TextView Time_2020_fp;
        ImageView Like_2020_fp;
        TextView like_txt_2020_fp;

        public Viewholder2020_fp(@NonNull View itemView) {
            super(itemView);

            profileimage_2020_fp=itemView.findViewById(R.id.ivprofile);
            postimage_2020_fp=itemView.findViewById(R.id.ivpost);
            title_2020_fp=itemView.findViewById(R.id.title_new);
            message_2020_fp=itemView.findViewById(R.id.ivmessage);
            share_2020_fp=itemView.findViewById(R.id.ivshare);
            Like_2020_fp=itemView.findViewById(R.id.ivlike);
            Date_2020_fp=itemView.findViewById(R.id.ivDate);
            Time_2020_fp=itemView.findViewById(R.id.ivTime);
            like_txt_2020_fp=itemView.findViewById(R.id.like_txt);

        }

        public void getLikebuttonstatus_2020_fp(String postkey, String userid) {
            reference= FirebaseDatabase.getInstance().getReference("Likes");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.child(postkey).hasChild(userid)){
                        int Likecount=(int)snapshot.child(postkey).getChildrenCount();
                        like_txt_2020_fp.setText(Likecount+" Likes");
                        Like_2020_fp.setImageResource(R.drawable.ic_thumb_blue);
                    }
                    else{
                        int Likecount=(int)snapshot.child(postkey).getChildrenCount();
                        like_txt_2020_fp.setText(Likecount+" Likes");
                        Like_2020_fp.setImageResource(R.drawable.ic_baseline_thumb_up_24);
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

    public void setOnItemClickListerner(Feed2020_FundedProjects_Adapter.OnItemClickListerner onItemClickListerner){
        this.onItemClickListerner=onItemClickListerner;
    }
}
