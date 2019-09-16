package com.connor.demo.valueanimator;

import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.connor.demo.R;

public class AnimatorActivity extends Activity {
    private final static String TAG = "dzb";
    private TextView mAnimateTarget;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animator_main);
    }

    private void valueAnimator() {
        // 1 直接计算500ms，0f到1f的值，来进行动画
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 1f);
        valueAnimator.setDuration(500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int curValue = (int)animation.getAnimatedValue();
                Log.d(TAG,"curValue:"+curValue);
            }
        });
        valueAnimator.start();

        // 2 使用估值器计算值
        ValueAnimator valueAnimator1 = ValueAnimator.ofObject(new FloatEvaluator(), 0f, 1f);
        valueAnimator1.setDuration(500);
        valueAnimator1.start();
    }

    private void objectAniamtor() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mAnimateTarget, "alpha", 0f, 1f);
        objectAnimator.start();
    }
}
