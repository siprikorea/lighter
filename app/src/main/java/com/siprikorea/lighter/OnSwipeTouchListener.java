package com.siprikorea.lighter;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class OnSwipeTouchListener implements OnTouchListener {

    private static final int SWIPE_THRESHOLD = 100;

    boolean mIsDown;
    float mCurrentX;
    float mCurrentY;

    public OnSwipeTouchListener(Context context) {
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();

        float x = motionEvent.getX();
        float y = motionEvent.getY();

        switch (action & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                mCurrentX = x;
                mCurrentY = y;
                mIsDown = true;
                break;

            case MotionEvent.ACTION_MOVE:
                float diffY = y - mCurrentY;
                float diffX = x - mCurrentX;

                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD) {
                        mCurrentX = x;
                        mCurrentY = y;
                        if (diffX > 0) {
                            onSwipeRight();
                        } else {
                            onSwipeLeft();
                        }
                    }
                } else {
                    if (Math.abs(diffY) > SWIPE_THRESHOLD) {
                        mCurrentX = x;
                        mCurrentY = y;
                        if (diffY > 0) {
                            onSwipeBottom();
                        } else {
                            onSwipeTop();
                        }
                    }
                }
                break;

            case MotionEvent.ACTION_UP:
                mIsDown = false;
                break;
        }

        return true;
    }

    public void onSwipeRight() {
    }

    public void onSwipeLeft() {
    }

    public void onSwipeTop() {
    }

    public void onSwipeBottom() {
    }
}
