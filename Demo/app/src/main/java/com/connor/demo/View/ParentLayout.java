package com.connor.demo.View;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Create by dzb 2021/07/09
 */
public class ParentLayout extends RelativeLayout {
    private final String TAG = "dzb";

    public ParentLayout(Context context) {
        super(context);
    }

    public ParentLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ParentLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "LinearLayout:dispatchTouchEvent-->DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "LinearLayout:dispatchTouchEvent-->MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "LinearLayout:dispatchTouchEvent-->UP");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    //只有ViewGroup的子类才有此方法
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "LinearLayout:onInterceptTouchEvent-->DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "LinearLayout:onInterceptTouchEvent-->MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "LinearLayout:onInterceptTouchEvent-->UP");
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "LinearLayout:onTouchEvent-->DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "LinearLayout:onTouchEvent-->MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "LinearLayout:onTouchEvent-->UP");
                break;
        }
        return super.onTouchEvent(event);
    }

}
