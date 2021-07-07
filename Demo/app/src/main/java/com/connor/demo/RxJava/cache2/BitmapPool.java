package com.connor.demo.RxJava.cache2;

import android.graphics.Bitmap;

/**
 * Create by dzb 2021/07/07
 */
public interface BitmapPool {
    void put(Bitmap bitmap);

    /**
     * 获得一个可复用的Bitmap
     * 三个参数计算出 内存大小
     */
    Bitmap get(int width, int height, Bitmap.Config config);
}
