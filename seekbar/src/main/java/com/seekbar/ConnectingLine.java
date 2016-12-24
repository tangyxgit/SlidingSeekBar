/*
 * Copyright 2013, Edmodo, Inc. 
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this work except in compliance with the License.
 * You may obtain a copy of the License in the LICENSE file, or at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" 
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language 
 * governing permissions and limitations under the License. 
 */

package com.seekbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Class representing the blue connecting line between the two thumbs.
 */
public class ConnectingLine {

    // Member Variables ////////////////////////////////////////////////////////

    private final Paint mPaint;

    private final float mY;

    // Constructor /////////////////////////////////////////////////////////////

    public ConnectingLine(Context ctx, float y, float connectingLineWeight, int connectingLineColor) {

        float mConnectingLineWeight = dp2px(ctx,1.5f);
        // Initialize the paint, set values
        mPaint = new Paint();
        mPaint.setColor(connectingLineColor);
        mPaint.setStrokeWidth(mConnectingLineWeight);
        mPaint.setAntiAlias(true);

        mY = y;
    }

    /**
     * Draw the connecting line between the two thumbs.
     * 
     * @param canvas the Canvas to draw to
     * @param leftThumb the left thumb
     * @param rightThumb the right thumb
     */
    public void draw(Canvas canvas, BaseThumb leftThumb, BaseThumb rightThumb) {
        canvas.drawLine(leftThumb.getX(), mY, rightThumb.getX(), mY, mPaint);
    }
    /**
     * dp转px
     *
     * @param context 上下文
     * @param dpValue dp值
     * @return px值
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
