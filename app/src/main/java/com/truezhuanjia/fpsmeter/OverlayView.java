package com.truezhuanjia.fpsmeter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class OverlayView extends View {

    private Paint paint;
    private String intervalText = "Interval: 0 ms";

    public OverlayView(Context context) {
        super(context);
        init();
    }

    public OverlayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(50);
        paint.setAntiAlias(true);
    }

    public void updateIntervalText(double intervalMs) {
        intervalText = String.format("Interval: %.2f ms", intervalMs);
        invalidate(); // Request to redraw the view
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(intervalText, 50, 100, paint); // Draw text on canvas
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false; // Return false to let touch events pass through
    }

}

