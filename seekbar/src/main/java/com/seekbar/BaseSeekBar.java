package com.seekbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;

import com.seekbar.sliding.R;
import com.seekbar.sliding.listener.OnRangeBarChangeListener;

/**
 * Created by tangyx on 16/8/25.
 *
 */
public class BaseSeekBar extends View {

    // Default values for variables
    private static final int DEFAULT_TICK_COUNT = 3;
    private static final float DEFAULT_TICK_HEIGHT_DP = 24;
    private static final float DEFAULT_BAR_WEIGHT_PX = 2;
    private static final int DEFAULT_BAR_COLOR = Color.LTGRAY;
    private static final float DEFAULT_CONNECTING_LINE_WEIGHT_PX = 4;

    // Corresponds to android.RUtil.color.holo_blue_light.
    private static final int DEFAULT_CONNECTING_LINE_COLOR = DEFAULT_BAR_COLOR;
    // Indicator value tells TimerThumb.java whether it should draw the circle or not
    protected static final float DEFAULT_THUMB_RADIUS_DP = -1;
    protected static final int DEFAULT_THUMB_COLOR_NORMAL = -1;
    protected static final int DEFAULT_THUMB_COLOR_PRESSED = -1;


    protected int mTickCount = DEFAULT_TICK_COUNT;
    // Instance variables for all of the customizable attributes
    protected float mTickHeightDP = DEFAULT_TICK_HEIGHT_DP;
    protected float mBarWeight = DEFAULT_BAR_WEIGHT_PX;
    protected int mBarColor = DEFAULT_BAR_COLOR;

    protected float mConnectingLineWeight = DEFAULT_CONNECTING_LINE_WEIGHT_PX;
    protected int mConnectingLineColor = DEFAULT_CONNECTING_LINE_COLOR;

    protected float mThumbRadiusDP = DEFAULT_THUMB_RADIUS_DP;

    protected int mThumbColorNormal = DEFAULT_THUMB_COLOR_NORMAL;
    protected int mThumbColorPressed = DEFAULT_THUMB_COLOR_PRESSED;

    protected OnRangeBarChangeListener mListener;

    public BaseSeekBar(Context context) {
        super(context);
    }

    public BaseSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    // View Methods ////////////////////////////////////////////////////////////

    @Override
    public Parcelable onSaveInstanceState() {

        final Bundle bundle = new Bundle();

        bundle.putParcelable("instanceState", super.onSaveInstanceState());

        bundle.putInt("TICK_COUNT", mTickCount);
        bundle.putFloat("TICK_HEIGHT_DP", mTickHeightDP);
        bundle.putFloat("BAR_WEIGHT", mBarWeight);
        bundle.putInt("BAR_COLOR", mBarColor);
        bundle.putFloat("CONNECTING_LINE_WEIGHT", mConnectingLineWeight);
        bundle.putInt("CONNECTING_LINE_COLOR", mConnectingLineColor);


        bundle.putFloat("THUMB_RADIUS_DP", mThumbRadiusDP);
        bundle.putInt("THUMB_COLOR_NORMAL", mThumbColorNormal);
        bundle.putInt("THUMB_COLOR_PRESSED", mThumbColorPressed);

        return bundle;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {

        if (state instanceof Bundle) {

            final Bundle bundle = (Bundle) state;

            mTickCount = bundle.getInt("TICK_COUNT");
            mTickHeightDP = bundle.getFloat("TICK_HEIGHT_DP");
            mBarWeight = bundle.getFloat("BAR_WEIGHT");
            mBarColor = bundle.getInt("BAR_COLOR");
            mConnectingLineWeight = bundle.getFloat("CONNECTING_LINE_WEIGHT");
            mConnectingLineColor = bundle.getInt("CONNECTING_LINE_COLOR");


            mThumbRadiusDP = bundle.getFloat("THUMB_RADIUS_DP");
            mThumbColorNormal = bundle.getInt("THUMB_COLOR_NORMAL");
            mThumbColorPressed = bundle.getInt("THUMB_COLOR_PRESSED");


        } else {

            super.onRestoreInstanceState(state);
        }
    }
    /**
     * Does all the functions of the constructor for RangeBar. Called by both
     * RangeBar constructors in lieu of copying the code for each constructor.
     *
     * @param context Context from the constructor.
     * @param attrs AttributeSet from the constructor.
     * @return none
     */
    protected void rangeBarInit(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SeekBar, 0, 0);

        try {
            mTickHeightDP = ta.getDimension(R.styleable.SeekBar_tickHeight, DEFAULT_TICK_HEIGHT_DP);
            mBarWeight = ta.getDimension(R.styleable.SeekBar_barWeight, DEFAULT_BAR_WEIGHT_PX);
            mBarColor = ta.getColor(R.styleable.SeekBar_barColor, DEFAULT_BAR_COLOR);
            mConnectingLineWeight = ta.getDimension(R.styleable.SeekBar_connectingLineWeight,
                    DEFAULT_CONNECTING_LINE_WEIGHT_PX);
            mConnectingLineColor = ta.getColor(R.styleable.SeekBar_connectingLineColor,
                    DEFAULT_CONNECTING_LINE_COLOR);
            mThumbRadiusDP = ta.getDimension(R.styleable.SeekBar_thumbRadius, DEFAULT_THUMB_RADIUS_DP);

            mThumbColorNormal = ta.getColor(R.styleable.SeekBar_thumbColorNormal, DEFAULT_THUMB_COLOR_NORMAL);
            mThumbColorPressed = ta.getColor(R.styleable.SeekBar_thumbColorPressed,
                    DEFAULT_THUMB_COLOR_PRESSED);

        } finally {

            ta.recycle();
        }

    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width;
        int height;

        // Get measureSpec mode and size values.
        final int measureWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        final int measureHeightMode = MeasureSpec.getMode(heightMeasureSpec);
        final int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        final int measureHeight = MeasureSpec.getSize(heightMeasureSpec);

        // The RangeBar width should be as large as possible.
        if (measureWidthMode == MeasureSpec.AT_MOST) {
            width = measureWidth;
        } else if (measureWidthMode == MeasureSpec.EXACTLY) {
            width = measureWidth;
        } else {
            width = 500;
        }

        // The RangeBar height should be as small as possible.
        int mDefaultHeight = 100;
        if (measureHeightMode == MeasureSpec.AT_MOST) {
            height = Math.min(mDefaultHeight, measureHeight);
        } else if (measureHeightMode == MeasureSpec.EXACTLY) {
            height = measureHeight;
        } else {
            height = mDefaultHeight;
        }

        setMeasuredDimension(width, height);
    }
    /**
     * If is invalid tickCount, rejects. TickCount must be greater than 1
     *
     * @param tickCount Integer
     * @return boolean: whether tickCount > 1
     */
    protected boolean isValidTickCount(int tickCount) {
        return (tickCount > 1);
    }
    /**
     * Sets a listener to receive notifications of changes to the RangeBar. This
     * will overwrite any existing set listeners.
     *
     * @param listener the RangeBar notification listener; null to remove any
     *            existing listener
     */
    public void setOnRangeBarChangeListener(OnRangeBarChangeListener listener) {
        mListener = listener;
    }

    public int getTickCount() {
        return mTickCount;
    }

    /**
     * Get yPos in each of the public attribute methods.
     *
     * @return float yPos
     */
    protected float getYPos() {
        return (getHeight() / 2f);
    }

}
