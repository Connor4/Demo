package com.connor.demo.RxJava.cache2;

/**
 * Create by dzb 2021/07/07
 */
public interface MemoryCache {
    interface ResourceRemoveListener {
        void onResourceRemoved(Resource resource);
    }

    Resource put(Key key, Resource resource);

    void setResourceRemoveListener(ResourceRemoveListener listener);

    Resource remove2(Key key);
}
