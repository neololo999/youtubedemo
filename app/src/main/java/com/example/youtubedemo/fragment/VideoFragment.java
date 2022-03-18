package com.example.youtubedemo.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubedemo.R;
import com.example.youtubedemo.adapter.VideoAdapter;
import com.example.youtubedemo.models.ResponseVideo;
import com.example.youtubedemo.models.Video;
import com.example.youtubedemo.retrofit.ApiClient;
import com.example.youtubedemo.retrofit.ApiInterface;
import com.example.youtubedemo.util.Constant;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoFragment extends Fragment {


    
    @BindView(R.id.recyclerViewVideo) RecyclerView recyclerView;
    @BindView(R.id.progressbarVideo)  ProgressBar progressBar;

    VideoAdapter videoAdapter;
    ArrayList<Video> videoList;
    ApiInterface apiInterface;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_videos,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        videoList = new ArrayList<>();
        videoAdapter = new VideoAdapter(getContext(),videoList);
        recyclerView.setAdapter(videoAdapter);
        getVideos();


    }

    private void getVideos() {
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ResponseVideo> callVideos = apiInterface.getAllVideos(10, Constant.PLAYLIST_ID,Constant.API_KEY);
        callVideos.enqueue(new Callback<ResponseVideo>() {
            @Override
            public void onResponse(Call<ResponseVideo> call, Response<ResponseVideo> response) {

                ResponseVideo responseVideos = response.body();
                if (responseVideos!=null){
                    progressBar.setVisibility(View.GONE);
                    if (responseVideos.items.size()>0){
                        for (Video item:responseVideos.items) {
                            videoList.add(item);
                        }
                        videoAdapter.notifyDataSetChanged();
                    }
                }else{
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(getContext(), jObjError.getJSONObject("error").getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseVideo> call, Throwable t) {


            }
        });
    }
}
