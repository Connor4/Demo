package com.connor.demo.RxJava.cache;

import android.text.TextUtils;
import android.util.LruCache;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;

/**
 * Create by dzb 2021/05/25
 */
public class MemoryCache implements ICache {
    private LruCache<String, String> mCache;

    public MemoryCache() {
        final int maxMemory = (int) Runtime.getRuntime().maxMemory();
        final int cacheSize = maxMemory / 8;
        mCache = new LruCache<String, String>(cacheSize) {
            @Override
            protected int sizeOf(String key, String value) {
                try {
                    return value.getBytes("UTF-8").length;
                } catch (UnsupportedEncodingException e) {
                    return value.getBytes().length;
                }
            }
        };
    }

    @Override
    public <T> Observable<T> get(final String key, final Class<T> cls) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<T> e) throws Exception {
                String result = mCache.get(key);
                if (TextUtils.isEmpty(result)) {
                    e.onComplete();
                } else {
                    T t = new Gson().fromJson(result, cls);
                    e.onNext(t);
                }
            }
        });
    }

    @Override
    public <T> void put(String key, T t) {
        if (t != null) {
            mCache.put(key, new Gson().toJson(t));
        }
    }

    public void clearMemory(String key) {
        mCache.remove(key);
    }
}
