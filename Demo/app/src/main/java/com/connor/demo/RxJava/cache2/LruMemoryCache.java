package com.connor.demo.RxJava.cache2;

import android.os.Build;
import android.util.LruCache;

/**
 * Create by dzb 2021/07/07
 */
public class LruMemoryCache extends LruCache<Key, Resource> implements MemoryCache {
    private ResourceRemoveListener listener;
    private boolean isRemoved;
    public LruMemoryCache(int maxSize) {
        super(maxSize);
    }
    @Override
    protected int sizeOf(Key key, Resource value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //当在4.4以上手机复用的时候 需要通过此函数获得占用内存
            return value.getBitmap().getAllocationByteCount();
        }
        return value.getBitmap().getByteCount();
    }
    @Override
    protected void entryRemoved(boolean evicted, Key key, Resource oldValue, Resource newValue) {
        //给复用池使用
        if (null != listener && null != oldValue && !isRemoved) {
            listener.onResourceRemoved(oldValue);
        }
    }
    @Override
    public Resource remove2(Key key) {
        // 如果是主动移除的不会掉 listener.onResourceRemoved
        isRemoved = true;
        Resource remove = remove(key);
        isRemoved = false;
        return remove;
    }
    /**
     * 资源移除监听
     */
    @Override
    public void setResourceRemoveListener(ResourceRemoveListener listener) {
        this.listener = listener;
    }
}
