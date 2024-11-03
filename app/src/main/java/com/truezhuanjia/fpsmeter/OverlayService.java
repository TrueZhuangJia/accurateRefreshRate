package com.truezhuanjia.fpsmeter;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Choreographer;
import android.view.Gravity;
import android.view.WindowManager;

public class OverlayService extends Service {

    private WindowManager windowManager;
    private OverlayView overlayView;

    @Override
    public void onCreate() {
        super.onCreate();
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        overlayView = new OverlayView(this);

        // Set fixed dimensions for the overlay view
        int overlayWidth = 500;  // Width in pixels
        int overlayHeight = 120; // Height in pixels

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                overlayWidth,
                overlayHeight,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                PixelFormat.TRANSLUCENT
        );

        params.gravity = Gravity.TOP | Gravity.LEFT;
        params.x = 100; // X position
        params.y = 100; // Y position

        windowManager.addView(overlayView, params);
        startFrameTracking();
    }


    private void startFrameTracking() {
        Choreographer.getInstance().postFrameCallback(frameCallback);
    }

    private final Choreographer.FrameCallback frameCallback = new Choreographer.FrameCallback() {
        private long lastFrameTimeNanos = 0;

        @Override
        public void doFrame(long frameTimeNanos) {
            if (lastFrameTimeNanos != 0) {
                long interval = frameTimeNanos - lastFrameTimeNanos;
                double intervalMs = interval / 1_000_000.0;
                overlayView.updateIntervalText(intervalMs);
            }
            lastFrameTimeNanos = frameTimeNanos;
            Choreographer.getInstance().postFrameCallback(this);
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (overlayView != null) {
            windowManager.removeView(overlayView);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

