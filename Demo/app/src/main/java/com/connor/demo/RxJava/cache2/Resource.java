package com.connor.demo.RxJava.cache2;

import android.graphics.Bitmap;

/**
 * Create by dzb 2021/07/07
 */
public class Resource {
    private Bitmap bitmap;
    private int acquired;
    private ResourceListener listener;
    private Key key;

    public interface ResourceListener {
        void onResourceReleased(Key key, Resource resource);
    }

    public void setResourceListener(Key key, ResourceListener listener) {
        this.key = key;
        this.listener = listener;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void recycle() {
        if (acquired > 0) {
            return;
        }
        if (!bitmap.isRecycled()) {
            bitmap.recycle();
        }
    }

    public void release() {
        if (--acquired == 0) {
            listener.onResourceReleased(key, this);
        }
    }

    public void acquire() {
        if (bitmap.isRecycled()) {
            throw new IllegalStateException("resource recycled");
        }
        ++acquired;
    }

}
