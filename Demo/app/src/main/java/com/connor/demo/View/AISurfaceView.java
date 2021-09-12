package com.iflytek.auto.mall.hmi.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


import com.iflytek.auto.mall.hmi.R;
import com.iflytek.auto.mall.hmi.util.BitmapUtil;

import java.io.InputStream;

/**
 * @author junqiu
 * @description: AISurfaceView
 * @date :2020/7/27
 */
public class AISurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private final static String TAG = "AISurfaceView";
    private SurfaceHolder mSurfaceHolder;
    private int lastWidth = -1;
    private int lastHeight = -1;
    private boolean mPlayOnce; // 只播放一次
    /**
     * 线程运行开关
     */
    private boolean mIsThreadRunning = true;
    /**
     * 用于播放动画的图片资源id数组
     */
    private int[] mBitmapResourceIds;
    /**
     * 资源总数
     */
    private int totalCount;
    /**
     * 显示的图片
     */
    private Bitmap mBitmap;
    /**
     * 当前动画播放的位置
     */
    private int mCurrentIndex;
    private Canvas mCanvas;
    private long mFrameDuration = 50;
    private Thread thread;
    private Matrix mMatrix = null;
    private Paint paint = null;
    private SurfaceHolder surfaceHolder = null;
    private final Object object = new Object();

    public AISurfaceView(Context context) {
        this(context, null);
        initView(context, null);
    }

    public AISurfaceView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context, attrs);
    }

    public AISurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        initView(context, attrs);
    }

    private void initView(Context ctx, AttributeSet attrs) {
        mSurfaceHolder = this.getHolder();
        mSurfaceHolder.addCallback(this);
        mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_HARDWARE);
        //设置透明背景
        //setZOrderOnTop(true) 必须在setFormat方法之前，不然png的透明效果不生效
        setZOrderOnTop(true);
        mSurfaceHolder.setFormat(PixelFormat.TRANSLUCENT);
        mBitmapResourceIds = new int[1];
        if (attrs == null) {
            return;
        }
        TypedArray typedArray = ctx.obtainStyledAttributes(attrs, R.styleable.AISurfaceView);
        mFrameDuration = typedArray.getInt(R.styleable.AISurfaceView_frame_duration, 50);
        typedArray.recycle();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d(TAG, " surfaceCreated ");
//        start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d(TAG, " surfaceDestroyed ");
        destroy();
    }

    @Override
    @SuppressLint("DrawAllocation")
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        setMeasuredDimension(measureWidth(widthMeasureSpec, options), measureHeight(heightMeasureSpec, options));
    }

    /**
     * 根据资源计算宽度
     */
    private int measureWidth(int widthMeasureSpec, BitmapFactory.Options options) {
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        if (mode == MeasureSpec.AT_MOST) {
            if (mBitmapResourceIds == null || mBitmapResourceIds.length == 0) {
                return size;
            }
            if (options.outWidth == 0) {
                if (lastWidth <= 0) {
                    InputStream is = null;
                    try {
                        is = getResources().openRawResource(mBitmapResourceIds[0]);
                        BitmapFactory.decodeStream(is, null, options);
                        lastWidth = options.outWidth;
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (is != null) {
                                is.close();
                                is = null;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                if (lastWidth > 0) {
                    return Math.min(size, lastWidth);
                }
                return size;
            } else {
                lastWidth = options.outWidth;
                return Math.min(size, options.outWidth);
            }
        }
        return size;
    }

    /**
     * 根据资源计算高度
     */
    private int measureHeight(int heightMeasureSpec, BitmapFactory.Options options) {
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        if (mode == MeasureSpec.AT_MOST) {
            if (mBitmapResourceIds == null || mBitmapResourceIds.length == 0) {
                return size;
            }
            if (options.outHeight == 0) {
                if (lastHeight < 0) {
                    InputStream is = null;
                    try {
                        is = getResources().openRawResource(mBitmapResourceIds[0]);
                        BitmapFactory.decodeStream(is, null, options);
                        lastHeight = options.outHeight;
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (is != null) {
                                is.close();
                                is = null;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                if (lastHeight > 0) {
                    return Math.min(size, lastHeight);
                }
                return size;
            } else {
                lastHeight = options.outHeight;
                return Math.min(size, lastHeight);
            }
        }
        return size;
    }

    /**
     * 制图方法
     */
    private void drawView() {
        // 无资源文件退出
        if (mBitmapResourceIds == null) {
            Log.d(TAG, "the bitmapsrcIDs is null");
            mIsThreadRunning = false;
            return;
        }

        //防止是获取不到Canvas
        surfaceHolder = mSurfaceHolder;
        synchronized (object) {
            mCanvas = surfaceHolder.lockCanvas();
            //Log.d(TAG, "drawView: mCanvas= " + mCanvas);
            if (mCanvas == null) {
                return;
            }
            try {
                synchronized (object) {
                    if (mBitmapResourceIds != null && mBitmapResourceIds.length > 0) {
                        mBitmap = BitmapUtil.decodeSampledBitmapFromResource(getResources(), mBitmapResourceIds[mCurrentIndex], getWidth(), getHeight());
                    }
                }
                if (mBitmap == null) {
                    return;
                }
                mBitmap.setHasAlpha(true);
                //画图抗锯齿，圆滑处理
                if (paint == null) {
                    paint = new Paint();
                    paint.setAntiAlias(true);
                    paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
                    paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
                    paint.setStyle(Paint.Style.STROKE);
                }
                mCanvas.drawPaint(paint);
                // Matrix性能比Rect好37% , 移植小精灵
                if (mMatrix == null) {
                    mMatrix = new Matrix();
                    // 原图1:1 绘制
                    mMatrix.postScale(1.0f, 1.0f);
                }
                mCanvas.drawBitmap(mBitmap, mMatrix, paint);
                // 播放到最后一张图片
                if (mCurrentIndex == totalCount - 1) {
                    //播放到最后一张，当前index置零
                    mCurrentIndex = 0;
                    // 只执行一次，中断标志位
                    if (mPlayOnce) {
                        mIsThreadRunning = false;
                    }
                }
            } catch (Exception e) {
                Log.d(TAG, "drawView: e =" + e.toString());
                e.printStackTrace();
            } finally {
                mCurrentIndex++;
                if (mCurrentIndex >= totalCount) {
                    mCurrentIndex = 0;
                }
                //SurfaceView不可见时，会被销毁，会造成异常
                try {
                    if (mCanvas != null) {
                        // 将画布解锁并显示在屏幕上
                        if (getHolder() != null) {
                            surfaceHolder.unlockCanvasAndPost(mCanvas);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //bitmap 对象没有被回收
                if (null != mBitmap && !mBitmap.isRecycled()) {
                    //释放
                    mBitmap.recycle();
                    mBitmap = null;
                }
                //提醒系统及时回收
                System.gc();
            }
        }
    }

    @Override
    public void run() {
        // 每隔 ** ms刷新屏幕
        while (mIsThreadRunning) {
            drawView();
            try {
                Thread.sleep(mFrameDuration);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 开始 start
     */
    public void start() {
        Log.d(TAG, "start");
        mCurrentIndex = 0;
        mIsThreadRunning = true;
        //确保绘制线程不被销毁
        if (thread == null || !thread.isAlive()) {
            thread = new Thread(this, "BaseSurfaceViewDraw");
        }
        thread.start();
    }


    /**
     * 只执行一次动的start
     */
    public void startOnce() {
        mPlayOnce = true;
        start();
    }

    /**
     * destroy 销毁
     */
    private void destroy() {
        Log.d(TAG, "destroy");
        mIsThreadRunning = false;
        mPlayOnce = false;
        thread = null;
    }

    /**
     * 设置动画播放素材的id
     *
     * @param bitmapResourceIds 图片资源id数组
     */
    public void setBitmapResourceID(int[] bitmapResourceIds) {
        this.mBitmapResourceIds = bitmapResourceIds;
        totalCount = bitmapResourceIds.length;
    }
}