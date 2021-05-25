package com.connor.demo.RxJava.cache;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Create by dzb 2021/05/25
 */
public class CacheLoader {
    private static CacheLoader sLoader;
    private static Application sApplication;
    private ICache mMemoryCache, mDiskCache;

    public static Application getApplication() {
        return sApplication;
    }

    private CacheLoader() {
        mMemoryCache = new MemoryCache();
        mDiskCache = new DiskCache();
    }

    public static CacheLoader getInstance(Context context) {
        sApplication = (Application) context.getApplicationContext();
        if (sLoader == null) {
            synchronized (CacheLoader.class) {
                if (sLoader == null) {
                    sLoader = new CacheLoader();
                }
            }
        }
        return sLoader;
    }

    public <T> Observable<T> asDataObservable(String key, Class<T> cls, NetworkCache<T> networkCache) {
        // concat函数会按照顺序，前面的observable方法没有调用onComplete不会执行下一个observable，
        // 如果前一个找到数据执行onNext就不会执行接下来的observable
        return Observable.concat(memory(key, cls), disk(key, cls), network(key, cls, networkCache))
                .firstElement()
                .toObservable();
    }

    private <T> Observable<T> memory(String key, Class<T> cls) {
        return mMemoryCache.get(key, cls).doOnNext(new Consumer<T>() {
            @Override
            public void accept(@NonNull T t) throws Exception {
                if (t != null) {
                    Log.d("dzb", "来自内存");
                }
            }
        });
    }

    private <T> Observable<T> disk(final String key, Class<T> cls) {
        return mDiskCache.get(key, cls).doOnNext(new Consumer<T>() {
            @Override
            public void accept(@NonNull T t) throws Exception {
                if (t != null) {
                    Log.d("dzb", "来自磁盘");
                    mMemoryCache.put(key, t);
                }
            }
        });
    }

    private <T> Observable<T> network(final String key, Class<T> cls, NetworkCache<T> networkCache) {
        return networkCache.get(key, cls).doOnNext(new Consumer<T>() {
            @Override
            public void accept(@NonNull T t) throws Exception {
                if (t != null) {
                    Log.d("dzb", "来自网络");
                    mDiskCache.put(key, t);
                    mMemoryCache.put(key, t);
                }
            }
        });
    }

    public void clearMemory(String key) {
        ((MemoryCache) mMemoryCache).clearMemory(key);
    }


    public void clearMemoryDisk(String key) {
        ((MemoryCache) mMemoryCache).clearMemory(key);
        ((DiskCache) mDiskCache).clearDiskMemory(key);
    }

}
