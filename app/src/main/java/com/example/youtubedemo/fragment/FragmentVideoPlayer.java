package com.example.youtubedemo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.youtubedemo.util.Constant;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragmentX;

public class FragmentVideoPlayer extends YouTubePlayerSupportFragmentX implements YouTubePlayer.OnInitializedListener {


    String videoId;
    private static final int RECOVERY_DIALOG_REQUEST = 1;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (bundle!=null && bundle.containsKey("KEY_VIDEO_ID")){
            videoId = bundle.getString("KEY_VIDEO_ID");
        }
        initialize(Constant.API_KEY,this);

    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
        initialize(Constant.API_KEY,this);

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        if (videoId!=null){
            if (wasRestored){
                youTubePlayer.play();
            }else{
                youTubePlayer.loadVideo(videoId);
            }
        }

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(getActivity(), RECOVERY_DIALOG_REQUEST).show();
        } else {
            String errorMessage = String.format("There was an error initializing the YouTubePlayer (%1$s)", errorReason.toString());
            Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("KEY_VIDEO_ID",videoId);
    }


}
