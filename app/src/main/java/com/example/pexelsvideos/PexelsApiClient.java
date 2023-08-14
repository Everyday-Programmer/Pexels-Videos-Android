package com.example.pexelsvideos;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PexelsApiClient {
    private static final String API = "563492ad6f91700001000001f8018fa3798844489979ded127c25470";
    private static final String BASE_URL = "https://api.pexels.com/videos/popular?page=1&per_page=50";

    public interface VideoResponseListener {
        void onVideoResponse(ArrayList<Video> arrayList);
        void onFailed(String error);
    }

    public static void getPopularVideos(final  VideoResponseListener videoResponseListener) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(BASE_URL)
                .addHeader("Authorization", API)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                videoResponseListener.onFailed("Request Failed: "+ e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        String responseBody = response.body().string();
                        JSONObject jsonObject = new JSONObject(responseBody);
                        JSONArray jsonArray = jsonObject.getJSONArray("videos");
                        ArrayList<Video> arrayList = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            String videoUrl = jsonObject1.getJSONArray("video_files").getJSONObject(0).getString("link");
                            Video video = new Video();
                            video.setLink(videoUrl);
                            video.setId(jsonObject1.getString("id"));
                            video.setFile_type(jsonObject1.getJSONArray("video_files").getJSONObject(0).getString("file_type"));
                            video.setWidth(jsonObject1.getInt("width"));
                            video.setHeight(jsonObject1.getInt("height"));
                            video.setDuration(jsonObject1.getInt("duration"));
                            video.setQuality(jsonObject1.getJSONArray("video_files").getJSONObject(0).getString("quality"));
                            video.setUserName(jsonObject1.getJSONObject("user").getString("name"));
                            video.setUserUrl(jsonObject1.getJSONObject("user").getString("url"));
                            arrayList.add(video);
                        }
                        videoResponseListener.onVideoResponse(arrayList);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}