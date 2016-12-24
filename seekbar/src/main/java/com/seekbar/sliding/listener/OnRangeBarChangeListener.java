package com.seekbar.sliding.listener;

import com.seekbar.BaseSeekBar;

/**
 * Created by tangyx on 16/8/25.
 */
public interface OnRangeBarChangeListener {
    void onIndexChangeListener(BaseSeekBar rangeBar, int leftThumbIndex, int rightThumbIndex);
}
