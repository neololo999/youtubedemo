package com.example.youtubedemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.youtubedemo.ActivityVideoDetail;
import com.example.youtubedemo.R;
import com.example.youtubedemo.models.Thumbnails;
import com.example.youtubedemo.models.TypeThumbnail;
import com.example.youtubedemo.models.Video;

import org.w3c.dom.Text;

import java.security.AccessControlContext;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoAdapterViewHolder> {

    private final Context context;
    private final ArrayList<Video> videoList;

    public VideoAdapter(Context context, ArrayList<Video> videoList){
       this.context =  context;
       this.videoList = videoList;
    }
    @NonNull
    @Override
    public VideoAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.intem_video2,parent,false);
        return new VideoAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapterViewHolder holder, int position) {
        Video video = videoList.get(position);
        if (video!=null){
            holder.textViewTitle.setText(video.snippet.title);
            if (video.snippet.thumbnails!=null){
                TypeThumbnail t = video.snippet.thumbnails.high;
                if (t==null) t = video.snippet.thumbnails.medium;
                if (t==null) t = video.snippet.thumbnails.standard;

                Glide.with(context).load(t.url).into(holder.imageViewThumbnail);
            }
        }

    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public class VideoAdapterViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_item_title)
        TextView textViewTitle;
        @BindView(R.id.iv_item_cover)
        ImageView imageViewThumbnail;
        public VideoAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Video video = videoList.get(getAdapterPosition());
                    Intent intent  = new Intent(context, ActivityVideoDetail.class);
                    intent.putExtra("videoId",video.contentDetails.videoId);
                    intent.putExtra("videoTitle",video.snippet.title);
                    intent.putExtra("videoDesc",video.snippet.description);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }
}
