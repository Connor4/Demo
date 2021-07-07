package com.connor.demo.RxJava.cache2;


/**
 * Create by dzb 2021/07/07
 */
class CacheTest2 implements Resource.ResourceListener, MemoryCache.ResourceRemoveListener {
    LruMemoryCache mLruMemoryCache;
    ActiveResource activeResource;
    LruBitmapPool mLruBitmapPool;

    public Resource test(Key key) {
        mLruBitmapPool = new LruBitmapPool(10);
        // 内存缓存
        mLruMemoryCache = new LruMemoryCache(10);
        mLruMemoryCache.setResourceRemoveListener(this);
        // 活动资源缓存
        activeResource = new ActiveResource(this);

        /**
         * 第一步从活动资源中查找是否有正在使用的照片
         */
        Resource resource = activeResource.get(key);
        if (resource != null) {
            resource.acquire();
            return resource;
        }

        /**
         * 第二部 从内存缓存中查找
         */
        resource = mLruMemoryCache.get(key);
        if (resource != null) {
            mLruMemoryCache.remove2(key);
            resource.acquire();
            activeResource.activate(key, resource);
            return resource;
        }
        return null;
    }

    @Override
    public void onResourceReleased(Key key, Resource resource) {
        activeResource.deactivate(key);
        mLruMemoryCache.put(key, resource);
    }

    @Override
    public void onResourceRemoved(Resource resource) {
        mLruBitmapPool.put(resource.getBitmap());
    }
}
