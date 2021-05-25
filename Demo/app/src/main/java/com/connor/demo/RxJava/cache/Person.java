package com.connor.demo.RxJava.cache;

import java.io.Serializable;
import java.util.List;

/**
 * Create by dzb 2021/05/25
 */
public class Person implements Serializable {
    public List<String> mData;

    public List<String> getData() {
        return mData;
    }

    public void setData(List<String> data) {
        mData = data;
    }
}
