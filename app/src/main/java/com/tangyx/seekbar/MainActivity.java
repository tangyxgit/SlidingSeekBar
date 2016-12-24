package com.tangyx.seekbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.seekbar.BaseSeekBar;
import com.seekbar.sliding.SlidingSeekBar;
import com.seekbar.sliding.listener.OnRangeBarChangeListener;
import com.seekbar.sliding.text.TextSeekBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onGoBack();
        onTimer();
    }

    /**
     * 案例
     * 往返效果
     */
    private void onGoBack(){
        final TextSeekBar mGoBackSeekBar = (TextSeekBar) findViewById(R.id.only_back);
        final TextView mGo = (TextView) findViewById(R.id.go_only);
        final TextView mBack = (TextView) findViewById(R.id.go_back);
        mGoBackSeekBar.setCircleText(mGo.getText().toString());
        mGo.setVisibility(View.INVISIBLE);
        mGoBackSeekBar.setOnRangeBarChangeListener(new OnRangeBarChangeListener() {
            @Override
            public void onIndexChangeListener(BaseSeekBar rangeBar, int leftThumbIndex, int rightThumbIndex) {
                switch (leftThumbIndex){
                    case 0:
                        mGoBackSeekBar.setCircleText(mGo.getText().toString());
                        mGo.setVisibility(View.INVISIBLE);
                        mBack.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        mGoBackSeekBar.setCircleText(mBack.getText().toString());
                        mGo.setVisibility(View.VISIBLE);
                        mBack.setVisibility(View.INVISIBLE);
                        break;
                }
            }
        });
    }
    /**
     * 案例
     * 时间效果
     */
    private void onTimer(){
        SlidingSeekBar slidingSeekBar = (SlidingSeekBar) findViewById(R.id.time_bar);
        slidingSeekBar.setOnRangeBarChangeListener(new OnRangeBarChangeListener() {
            @Override
            public void onIndexChangeListener(BaseSeekBar rangeBar, int leftThumbIndex, int rightThumbIndex) {
                
            }
        });
    }
}
