package com.example.youtubedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.youtubedemo.fragment.FragmentVideoPlayer;
import com.google.android.youtube.player.YouTubeApiServiceUtil;
import com.google.android.youtube.player.YouTubeInitializationResult;

import butterknife.ButterKnife;

public class ActivityVideoDetail extends AppCompatActivity {

  //  @BindView(R.id.toolbar)  Toolbar toolbar;
  //  @BindView(R.id.tv_title) TextView textViewTitle;
  //  @BindView(R.id.tv_desc) TextView textViewDesc;

    String videoId,title,desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        videoId = intent.getStringExtra("videoId");

        YouTubeInitializationResult result = YouTubeApiServiceUtil.isYouTubeApiServiceAvailable(this);
        if (result!=YouTubeInitializationResult.SUCCESS){
            result.getErrorDialog(this,0).show();
        }
        FragmentVideoPlayer fragmentVideoPlayer = (FragmentVideoPlayer)getSupportFragmentManager().findFragmentById(R.id.fragment_youtube);
        fragmentVideoPlayer.setVideoId(videoId);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            // Retry initialization if user performed a recovery action
            //  getYouTubePlayerProvider().initialize(DeveloperKey.DEVELOPER_KEY, this);
        }
    }

 /*   @Override
    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerSupportFragmentX) getSupportFragmentManager().findFragmentById(R.id.fragment_youtube);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        if (!wasRestored) {
            youTubePlayer.cueVideo(videoId);
        }
    }*/
}