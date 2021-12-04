package com.example.infozyn_sona.feed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infozyn_sona.R;
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

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<sonaNews> arrayList;
    private Context context;

    OnItemClickListerner onItemClickListerner;
    DatabaseReference reference;
    boolean testclick=false;


    public RecyclerAdapter(ArrayList<sonaNews> arrayList, Context context){

        this.arrayList =arrayList;
        this.context=context;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_news,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        sonaNews sonaNews=arrayList.get(position);

        DatabaseReference Likref=FirebaseDatabase.getInstance().getReference("Likes");
        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        String userid=firebaseUser.getUid();
        String postkey= sonaNews.getKey();

        holder.getLikebuttonstatus(postkey,userid);

        holder.Like.setOnClickListener(new View.OnClickListener() {
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


        holder.title.setText(sonaNews.getTitleiv());
        holder.message.setText(sonaNews.getMessage());
        holder.Date.setText(sonaNews.getDate());
        holder.Time.setText(sonaNews.getTime());
        holder.message.setOnStateChangeListener(new ExpandableTextView.OnStateChangeListener() {
            @Override
            public void onStateChange(boolean isShrink) {
                sonaNews contentItems=arrayList.get(position);
                contentItems.setShrink(isShrink);
                arrayList.set(position,contentItems);
            }
        });
        holder.message.setText(sonaNews.getMessage());
        holder.message.resetState(sonaNews.isShrink());

        try {
            Picasso.get().load(sonaNews.getPostimage()).into(holder.postimage);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {

        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView profileimage;
        ImageView postimage;
        TextView title;
        ExpandableTextView message;
        ImageView share;
        TextView Date;
        TextView Time;
        ImageView Like;
        TextView like_txt;


        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            profileimage=itemView.findViewById(R.id.ivprofile);
            postimage=itemView.findViewById(R.id.ivpost);
            title=itemView.findViewById(R.id.title_new);
            message=itemView.findViewById(R.id.ivmessage);
            share=itemView.findViewById(R.id.ivshare);
            Like=itemView.findViewById(R.id.ivlike);
            Date=itemView.findViewById(R.id.ivDate);
            Time=itemView.findViewById(R.id.ivTime);
            like_txt=itemView.findViewById(R.id.like_txt);


        }

        public void getLikebuttonstatus(final String postkey,final String userid){
            reference= FirebaseDatabase.getInstance().getReference("Likes");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.child(postkey).hasChild(userid)){
                        int Likecount=(int)snapshot.child(postkey).getChildrenCount();
                        like_txt.setText(Likecount+" Likes");
                        Like.setImageResource(R.drawable.ic_thumb_blue);
                    }
                    else{
                        int Likecount=(int)snapshot.child(postkey).getChildrenCount();
                        like_txt.setText(Likecount+" Likes");
                        Like.setImageResource(R.drawable.ic_baseline_thumb_up_24);
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
    public void setOnItemClickListerner(OnItemClickListerner onItemClickListerner){
        this.onItemClickListerner=onItemClickListerner;
    }
}
