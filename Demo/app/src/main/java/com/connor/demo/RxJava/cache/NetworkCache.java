package com.connor.demo.RxJava.cache;


import io.reactivex.Observable;

/**
 * Create by dzb 2021/05/25
 */
public abstract class NetworkCache<T> {
    public abstract Observable<T> get(String key, Class<T> t);
}
