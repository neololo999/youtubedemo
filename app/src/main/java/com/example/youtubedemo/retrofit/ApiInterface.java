package com.example.youtubedemo.retrofit;

import com.example.youtubedemo.models.ResponseVideo;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    //GET https://youtube.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=10&playlistId=PLGmPGq178FstD1trKDpAvkZJdSnUM_j14&key=[YOUR_API_KEY] HTTP/1.1

   // @GET("playlistItems?part=snippet%2CcontentDetails")
   @GET("playlistItems?part=snippet%2CcontentDetails")
    Call<ResponseVideo> getAllVideos(@Query("maxResults") int max, @Query("playlistId") String playListId, @Query("key") String apiKey);
}
