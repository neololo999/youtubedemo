package com.example.youtubedemo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.youtubedemo.util.Constant;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragmentX;

public class FragmentVideoPlayer extends YouTubePlayerSupportFragmentX implements YouTubePlayer.OnInitializedListener {

    OnVideoPlayListener onVideoPlayListener;
    public interface OnVideoPlayListener{
        void onPlaying(String videoId);
    }

    String videoId;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (bundle!=null && bundle.containsKey("KEY_VIDEO_ID")){
            videoId = bundle.getString("KEY_VIDEO_ID");
        }else{
            videoId = arguments.getString("KEY_VIDEO_ID");
        }

        initialize(Constant.API_KEY,this);

    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
        initialize(Constant.API_KEY,this);

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (videoId!=null){
            if (b){
                youTubePlayer.play();
            }else{
                youTubePlayer.loadVideo(videoId);
            }
            if (onVideoPlayListener!=null){
                onVideoPlayListener.onPlaying(videoId);
            }
        }

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(getActivity(),1).show();
        }

    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("KEY_VIDEO_ID",videoId);
    }
}
