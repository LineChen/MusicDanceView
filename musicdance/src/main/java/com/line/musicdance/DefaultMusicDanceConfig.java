package com.line.musicdance;

import android.content.res.Resources;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenliu on 2019-09-29.
 */
public class DefaultMusicDanceConfig implements IMusicDanceConfig {

    @Override
    public float getStep() {
        return 0.05f;
    }

    @Override
    public float getNoteWidth() {
        return Resources.getSystem().getDisplayMetrics().density * 3;
    }

    @Override
    public float getNoteSpace() {
        return Resources.getSystem().getDisplayMetrics().density * 3;
    }

    @Override
    public float getNoteRadius() {
        return Resources.getSystem().getDisplayMetrics().density * 3;
    }

    @Override
    public int getInvalidateMillis() {
        return 80;
    }

    @NonNull
    @Override
    public List<MusicNote> getMusicNotes() {
        List<MusicNote> musicNotes = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            MusicNote musicNote = new MusicNote();
            if (i == 0) {
                musicNote.heightPercent = 0.45f;
                musicNote.maxHeightPercent = 0.8f;
                musicNote.minHeightPercent = 0.45f;
            } else if (i == 1) {
                musicNote.heightPercent = 0.8f;
                musicNote.maxHeightPercent = 0.85f;
                musicNote.minHeightPercent = 0.5f;
            } else if (i == 2) {
                musicNote.heightPercent = 0.5f;
                musicNote.maxHeightPercent = 0.85f;
                musicNote.minHeightPercent = 0.5f;
            } else {
                musicNote.heightPercent = 0.9f;
                musicNote.maxHeightPercent = 0.9f;
                musicNote.minHeightPercent = 0.55f;
            }
            musicNote.increasing = i % 2 == 0;
            musicNotes.add(musicNote);
        }
        return musicNotes;
    }
}
