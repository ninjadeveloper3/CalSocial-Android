package com.CalSocial.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.core.view.GestureDetectorCompat;

import com.CalSocial.R;

public class SwipeToRevealHidden extends FrameLayout implements View.OnClickListener, GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {

    private View viewToAnimate;
    private GestureDetectorCompat mDetector;
    private Callback callback;
    private boolean swipeOpened = false;


    public SwipeToRevealHidden(Context context) {
        super(context);

        initializeViews(context, null);

    }

    public SwipeToRevealHidden(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initializeViews(context, attrs);

    }

    public SwipeToRevealHidden(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeViews(context, attrs);

    }

    public SwipeToRevealHidden(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initializeViews(context, attrs);

    }

    private void initializeViews(Context context, AttributeSet set) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.widget_swipe_to_reveal_hidden, this);

        mDetector = new GestureDetectorCompat(context, this);
        mDetector.setOnDoubleTapListener(this);
        if (set == null) {

            return;
        }

    }

    public Callback getCallback() {
        return callback;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        this.setOnClickListener(this);
        viewToAnimate = this.getChildAt(1);

    }

    @Override
    public void onClick(View v) {


    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        if (callback != null) {
            callback.onClickEventCalled(e, swipeOpened);
        }
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        viewToAnimate.post(new Runnable() {
            @Override
            public void run() {

                if (e1.getX() > e2.getX()) {
                    //this is left scroll
                    //The view is to be moved to the left
                    viewToAnimate.animate().translationX(0).setDuration(100).setStartDelay(0);
                    swipeOpened = true;

                } else {
                    //this is right scroll
                    //move the view out of the screen to the right
                    int pixelsToMove = viewToAnimate.getWidth();
                    viewToAnimate.animate().translationX(pixelsToMove).setDuration(100).setStartDelay(0);
                    swipeOpened = false;

                }
            }
        });
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        if (this.mDetector.onTouchEvent(event)) {
            return true;
        }
        return super.dispatchTouchEvent(event);
    }

    public interface Callback {
        void onClickEventCalled(MotionEvent e, boolean swipeOpened);
    }
}
