package com.line.musicdance;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

/**
 * Created by chenliu on 2019-09-26.
 */
public class MusicDanceView extends View {

    private static final String TAG = "MusicDanceView";
    private Paint paint;
    private List<MusicNote> musicNotes;
    private float noteSpace;
    private float noteWidth;
    private float noteRadius;
    private float step;
    private int invalidateMillis;
    private IncreaseRunnable increaseRunnable;
    private RectF rect;


    public MusicDanceView(Context context) {
        this(context, null);
    }

    public MusicDanceView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MusicDanceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int w = (int) (getNoteCount() * noteWidth + (getNoteCount() - 1) * noteSpace);
        int h = (int) dp2px(25);
        int widthSize = resolveSize(w, widthMeasureSpec);
        int heightSize = resolveSize(h, heightMeasureSpec);
        setMeasuredDimension(widthSize, heightSize);
    }

    private void init() {
        rect = new RectF();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        increaseRunnable = new IncreaseRunnable();
        setMusicDanceConfig(new DefaultMusicDanceConfig());
    }

    @Override
    public void setVisibility(int v) {
        if (getVisibility() != v) {
            super.setVisibility(v);
            if (v == GONE || v == INVISIBLE) {
                stop();
            } else {
                start();
            }
        }
    }

    public int getNoteCount() {
        return musicNotes.size();
    }

    public void setMusicDanceConfig(@NonNull IMusicDanceConfig musicDanceConfig) {
        musicNotes = musicDanceConfig.getMusicNotes();
        noteSpace = musicDanceConfig.getNoteSpace();
        noteWidth = musicDanceConfig.getNoteWidth();
        noteRadius = musicDanceConfig.getNoteRadius();
        step = musicDanceConfig.getStep();
        invalidateMillis = musicDanceConfig.getInvalidateMillis();
        requestLayout();
        start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int size = musicNotes.size();
        for (int i = 0; i < size; i++) {
            rect.left = i * noteWidth + i * noteSpace;
            rect.right = rect.left + noteWidth;
            rect.top = getHeight() - musicNotes.get(i).heightPercent * getHeight();
            rect.bottom = getHeight();
            canvas.drawRoundRect(rect, noteRadius, noteRadius, paint);
        }
    }

    public void start() {
        removeCallbacks(increaseRunnable);
        post(increaseRunnable);
    }

    public void stop() {
        removeCallbacks(increaseRunnable);
    }


    class IncreaseRunnable implements Runnable {
        @Override
        public void run() {
            for (MusicNote musicNote : musicNotes) {
                float noteH = musicNote.heightPercent;
                if (noteH >= musicNote.maxHeightPercent) {
                    musicNote.increasing = false;
                } else if (noteH <= musicNote.minHeightPercent) {
                    musicNote.increasing = true;
                }
                if (musicNote.increasing) {
                    noteH += step;
                } else {
                    noteH -= step;
                }
                musicNote.heightPercent = noteH;
            }
            invalidate();
            postDelayed(this, invalidateMillis);
        }
    }

    private float dp2px(int dp) {
        return getResources().getDisplayMetrics().density * dp;
    }

}
