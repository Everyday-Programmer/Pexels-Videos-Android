package com.example.pexelsvideos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;

import java.text.MessageFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recycler);
        PexelsApiClient.getPopularVideos(new PexelsApiClient.VideoResponseListener() {
            @Override
            public void onVideoResponse(ArrayList<Video> arrayList) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        VideoAdapter adapter = new VideoAdapter(MainActivity.this, arrayList);
                        recyclerView.setAdapter(adapter);

                        adapter.setOnItemClickListener(new VideoAdapter.OnItemClickListener() {
                            @Override
                            public void onClick(Video video) {
                                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this);
                                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.video_details_bottomsheet, null);
                                bottomSheetDialog.setContentView(view);
                                bottomSheetDialog.show();

                                ImageView imageView = view.findViewById(R.id.videoThumbnail);
                                TextView videoID = view.findViewById(R.id.videoID);
                                TextView videoQuality = view.findViewById(R.id.videoQuality);
                                TextView videoFileType = view.findViewById(R.id.videoFileType);
                                TextView videoResolution = view.findViewById(R.id.videoResolution);
                                TextView videoUploadedBy = view.findViewById(R.id.videoUploadedBy);
                                TextView videoDuration = view.findViewById(R.id.videoDuration);
                                MaterialButton play = view.findViewById(R.id.playVideo);
                                MaterialButton viewUser = view.findViewById(R.id.viewUser);

                                Glide.with(MainActivity.this).load(video.getLink()).into(imageView);
                                videoDuration.setText(MessageFormat.format("Video Duration: {0} seconds", video.getDuration()));
                                videoID.setText(MessageFormat.format("Video ID: {0}", video.getId()));
                                videoQuality.setText(MessageFormat.format("Video Quality: {0}", video.getQuality()));
                                videoFileType.setText(MessageFormat.format("Video File Type: {0}", video.getFile_type()));
                                videoResolution.setText(MessageFormat.format("Video Resolution: {0}x{1}", video.getWidth(), video.getHeight()));
                                videoUploadedBy.setText(MessageFormat.format("Uploaded By: {0}", video.getUserName()));

                                play.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(MainActivity.this, VideoPlayActivity.class);
                                        intent.putExtra("url", video.getLink());
                                        startActivity(intent);
                                    }
                                });

                                viewUser.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(video.getUserUrl()));
                                        startActivity(intent);
                                    }
                                });
                            }
                        });
                    }
                });
            }

            @Override
            public void onFailed(String error) {

            }
        });
    }
}