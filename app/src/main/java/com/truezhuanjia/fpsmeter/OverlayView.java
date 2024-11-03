package com.truezhuanjia.fpsmeter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
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
        paint.setColor(Color.GREEN);
        paint.setShadowLayer(1f,0.2f,0.2f, Color.BLACK);
        paint.setTextSize(50);
        paint.setAntiAlias(true);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        paint.setAntiAlias(true);
    }

    public void updateIntervalText(double intervalMs) {
        intervalText = String.format("Interval: %.2f ms, RefreshRate: %.2 Hz", intervalMs, 1000/intervalMs);
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

