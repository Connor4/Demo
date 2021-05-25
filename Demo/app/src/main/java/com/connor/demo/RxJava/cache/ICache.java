package com.connor.demo.RxJava.cache;


import io.reactivex.Observable;

/**
 * Create by dzb 2021/05/25
 */
public interface ICache {
     <T> Observable<T> get(String key, Class<T> cls);

     <T> void put(String key, T t);
}
