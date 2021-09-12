package com.iflytek.auto.mall.hmi.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.viewpager.widget.ViewPager;

import com.iflytek.auto.mall.hmi.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 中间那断开的indicator
 * create by dzb at 2021/9/10
 */
public class GapIndicatorView extends View {
    private static final String TAG = GapIndicatorView.class.getSimpleName();
    private Paint mIndicatorPaint;
    private Paint mBackgroundPaint;
    private RectF mIndicatorRectLeft = new RectF(); // 左右两块一起构成一个
    private RectF mIndicatorRectRight = new RectF();
    private int mViewWidth;
    private int mViewHeight;
    private int mIndicatorWidth;
    private int mIndicatorHeight;
    private int mGapWidth;
    private int mIndicatorColor;
    private int mBackgroundColor;
    private int mIndicatorRadius = 3;
    private int mPageNum;

    private List<Integer> mPositionList = new ArrayList<>(); // 锚点位置
    private List<RectF> mAllIndicatorRect = new ArrayList<>(); // 所有指示器的位置
    private IndicatorOnPageChangeListener mPageChangeListener = new IndicatorOnPageChangeListener();
    private float mLastPositionOffsetSum;

    public GapIndicatorView(Context context) {
        this(context, null);
    }

    public GapIndicatorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GapIndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.GapIndicatorView, defStyleAttr, 0);
        mIndicatorWidth = typedArray.getDimensionPixelSize(R.styleable.GapIndicatorView_GapIndicatorWidth, 40);
        mIndicatorHeight = typedArray.getDimensionPixelSize(R.styleable.GapIndicatorView_GapIndicatorHeight, 6);
        mGapWidth = typedArray.getDimensionPixelSize(R.styleable.GapIndicatorView_GapIndicatorGapWidth, 30);
        mIndicatorColor = typedArray.getColor(R.styleable.GapIndicatorView_GapIndicatorColor, Color.TRANSPARENT);
        mBackgroundColor = typedArray.getColor(R.styleable.GapIndicatorView_GapIndicatorBackgroundColor, Color.TRANSPARENT);
        typedArray.recycle();
        init();
    }

    private void init() {
        mIndicatorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mIndicatorPaint.setColor(mIndicatorColor);
        mIndicatorPaint.setStyle(Paint.Style.FILL);
        mBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBackgroundPaint.setColor(mBackgroundColor);
        mBackgroundPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.EXACTLY;
        int heightMode = MeasureSpec.EXACTLY;
        setMeasuredDimension(MeasureSpec.makeMeasureSpec(mViewWidth, widthMode),
                MeasureSpec.makeMeasureSpec(mViewHeight, heightMode));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mPageNum; i++) {
            canvas.drawRoundRect(mAllIndicatorRect.get(i), mIndicatorRadius, mIndicatorRadius, mBackgroundPaint);
        }
        canvas.drawRoundRect(mIndicatorRectLeft, mIndicatorRadius, mIndicatorRadius, mIndicatorPaint);
        canvas.drawRoundRect(mIndicatorRectRight, mIndicatorRadius, mIndicatorRadius, mIndicatorPaint);
    }

    @UiThread
    public void setupWithViewPager(ViewPager viewPager, int listSize) {
        if (viewPager != null) {
            viewPager.addOnPageChangeListener(mPageChangeListener);
        }
        if (listSize < 0) {
            return;
        }
        int pageNum = (int) Math.ceil((double) listSize / 3);
        for (int i = 0; i < pageNum; i++) {
            mPositionList.add(i * (mIndicatorWidth + mGapWidth));
            // 初始化每个指示器背景
            RectF rectF = new RectF();
            rectF.set(i * (mIndicatorWidth + mGapWidth), 0, i * (mIndicatorWidth + mGapWidth) + mIndicatorWidth, mIndicatorHeight);
            mAllIndicatorRect.add(rectF);
        }
        // 初始化选中指示器
        mIndicatorRectLeft.set(0, 0, mIndicatorWidth, mIndicatorHeight);
        mPageNum = pageNum;
        // view宽度等于指示器总宽度加上间隔宽度
        mViewWidth = mIndicatorWidth * pageNum + mGapWidth * (pageNum - 1);
        mViewHeight = mIndicatorHeight;
        // 需要重新测量流程
        requestLayout();
    }

    /**
     * 设置指示器移动
     *
     * @param position       与vp关联的位置
     * @param positionOffset vp的
     */
    private void setIndicatorMove(int position, float positionOffset) {
        float currentPositionOffsetSum = position + positionOffset;
        boolean leftToRight = false; // 从右往左
        if (mLastPositionOffsetSum <= currentPositionOffsetSum) {
            leftToRight = true; // 从左往右
        }
        if (mLastPositionOffsetSum == currentPositionOffsetSum) {
            return;
        }
        float makeUpOffset; // 由于移动最后阶段会很慢，导致看起来不好看，修改一下数值
        int currentPosition; // 当前移动位置
        int currentPositionStart; // 当前锚点
        if (leftToRight) {
            makeUpOffset = Math.min(1, positionOffset + 0.03f);
            currentPositionStart = mPositionList.get(position);
            currentPosition = currentPositionStart + (int) (mIndicatorWidth * makeUpOffset);

            if (positionOffset != 0) {
                mIndicatorRectLeft.set(currentPosition, 0, currentPositionStart + mIndicatorWidth, mIndicatorHeight);
                mIndicatorRectRight.set(currentPositionStart + mIndicatorWidth + mGapWidth, 0,
                        currentPosition + mIndicatorWidth + mGapWidth, mIndicatorHeight);
            } else {
                // 当positionOffset为0说明已经切换选中到下一个了，直接选中下一个
                mIndicatorRectLeft.set(currentPositionStart, 0,
                        currentPositionStart + mIndicatorWidth, mIndicatorHeight);
                mIndicatorRectRight.set(0, 0, 0, 0);
            }
        } else {
            makeUpOffset = Math.max(0, positionOffset - 0.03f);
            currentPositionStart = position + 1 > mPositionList.size() ? mPositionList.get(position) : mPositionList.get(position + 1);
            currentPosition = currentPositionStart - (int) (mIndicatorWidth * (1 - makeUpOffset));

            if (positionOffset != 0) {
                mIndicatorRectLeft.set(currentPosition - mGapWidth, 0, currentPositionStart - mGapWidth, mIndicatorHeight);
                mIndicatorRectRight.set(currentPositionStart, 0, currentPosition + mIndicatorWidth, mIndicatorHeight);
            } else {
                // 右往左全程都是position一样的，不会变。所以停下的时候要按照当前position计算
                mIndicatorRectLeft.set(currentPositionStart - mGapWidth - mIndicatorWidth, 0,
                        currentPositionStart - mGapWidth, mIndicatorHeight);
                mIndicatorRectRight.set(0, 0, 0, 0);
            }
        }
//        Log.d(TAG, "left " + mIndicatorRectLeft.toString() + " right " + mIndicatorRectRight.toString());
        mLastPositionOffsetSum = currentPositionOffsetSum;
        invalidate();
    }

    private class IndicatorOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            setIndicatorMove(position, positionOffset);
        }

        @Override
        public void onPageSelected(int position) {
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }
}
