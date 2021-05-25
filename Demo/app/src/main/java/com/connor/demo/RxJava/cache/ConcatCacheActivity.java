package com.connor.demo.RxJava.cache;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.connor.demo.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class ConcatCacheActivity extends Activity {
    NetworkCache<Person> mNetworkCache;
    Observable<Person> mObservable;
    List<String> mData = new ArrayList<>();
    private String key = ConcatCacheActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concat_cache);
        init();
        findViewById(R.id.get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 使用flatMap将list结果转换成单个string
                mObservable.flatMap(new Function<Person, ObservableSource<String>>() {
                    @NonNull
                    @Override
                    public ObservableSource<String> apply(@NonNull Person person) throws Exception {
                        return Observable.fromIterable(person.getData());
                    }
                }).subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        Log.d("dzb", "接收到数据" + s);
                    }
                });
            }
        });

        findViewById(R.id.memory).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CacheLoader.getInstance(ConcatCacheActivity.this).clearMemory(key);
            }
        });
        findViewById(R.id.memory_disk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CacheLoader.getInstance(ConcatCacheActivity.this).clearMemoryDisk(key);
            }
        });
    }

    private void init() {
        for (int i = 0; i < 20; i++) {
            mData.add("数据" + i);
        }
        mNetworkCache = new NetworkCache<Person>() {
            @Override
            public Observable<Person> get(String key, Class<Person> t) {
                return Observable.just(mData)
                        .flatMap(new Function<List<String>, ObservableSource<Person>>() {
                            @NonNull
                            @Override
                            public ObservableSource<Person> apply(@NonNull List<String> strings) throws Exception {
                                Log.d("dzb", "从网络获取");
                                Person person = new Person();
                                person.setData(strings);
                                return Observable.just(person);
                            }
                        });
            }
        };
        mObservable = CacheLoader.getInstance(this).asDataObservable(key, Person.class, mNetworkCache);
    }
}