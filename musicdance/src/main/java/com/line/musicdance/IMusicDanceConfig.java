package com.line.musicdance;


import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;

import java.util.List;

/**
 * Created by chenliu on 2019-09-29.
 */
public interface IMusicDanceConfig {

    float getStep();

    float getNoteWidth();

    float getNoteSpace();

    float getNoteRadius();

    @ColorInt
    int getNoteColor();

    int getInvalidateMillis();

    @NonNull
    List<MusicNote> getMusicNotes();
}
