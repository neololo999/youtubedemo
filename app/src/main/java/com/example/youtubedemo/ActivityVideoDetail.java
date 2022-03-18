package com.example.youtubedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.youtubedemo.fragment.FragmentVideoPlayer;
import com.google.android.youtube.player.YouTubeApiServiceUtil;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayerFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityVideoDetail extends AppCompatActivity {

    @BindView(R.id.toolbar)  Toolbar toolbar;
    @BindView(R.id.tv_title) TextView textViewTitle;
    @BindView(R.id.tv_desc) TextView textViewDesc;

    String videoId,title,desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
        ButterKnife.bind(this);
        getData();
        playVideo();


    }

    private void playVideo() {
        final YouTubeInitializationResult result = YouTubeApiServiceUtil.isYouTubeApiServiceAvailable(this);
        if (result!=YouTubeInitializationResult.SUCCESS){
            result.getErrorDialog(this,0).show();
        }
        FragmentVideoPlayer fragmentVideoPlayer = (FragmentVideoPlayer)getSupportFragmentManager().findFragmentById(R.id.fragment_youtube);
        fragmentVideoPlayer.setVideoId(videoId);

    }

    private void getData() {
        Intent intent = getIntent();
        videoId = intent.getStringExtra("videoId");
        title = intent.getStringExtra("videoTitle");
        desc = intent.getStringExtra("videoDesc");

        textViewTitle.setText(title);
        textViewDesc.setText(desc);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);

    }
}