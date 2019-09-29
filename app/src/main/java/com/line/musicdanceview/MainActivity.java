package com.line.musicdanceview;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.line.musicdance.DefaultMusicDanceConfig;
import com.line.musicdance.MusicDanceView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MusicDanceView musicDanceView = findViewById(R.id.music_dance);
        musicDanceView.setMusicDanceConfig(new DefaultMusicDanceConfig() {
            @Override
            public float getNoteRadius() {
                return Resources.getSystem().getDisplayMetrics().density * 0;
            }
        });
    }
}
