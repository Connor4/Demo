package com.connor.demo.View;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Create by dzb 2021/07/09
 */
public class ChildView extends View {
    private final String TAG = "dzb";

    public ChildView(Context context) {
        super(context);
    }

    public ChildView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ChildView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "MyTextView:dispatchTouchEvent-->DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "MyTextView:dispatchTouchEvent-->MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "MyTextView:dispatchTouchEvent-->UP");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "MyTextView:onTouchEvent-->DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "MyTextView:onTouchEvent-->MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "MyTextView:onTouchEvent-->UP");
                break;
        }
        return super.onTouchEvent(event);
    }

}
