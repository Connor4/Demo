package com.connor.demo.RxJava.cache2;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Create by dzb 2021/07/07
 */
public class ActiveResource {
    private ReferenceQueue<Resource> queue;
    private final Resource.ResourceListener resourceListener;
    private Map<Key, ResourceWeakReference> activeResources = new HashMap<>();
    private Thread cleanReferenceThread;
    private boolean isShutdown;

    public ActiveResource(Resource.ResourceListener listener) {
        this.resourceListener = listener;
    }

    public void activate(Key key, Resource resource) {
        resource.setResourceListener(key, resourceListener);
        activeResources.put(key, new ResourceWeakReference(key, resource, getReferenceQueue()));
    }

    public Resource deactivate(Key key) {
        ResourceWeakReference reference = activeResources.remove(key);
        if (reference != null) {
            return reference.get();
        }
        return null;
    }

    public Resource get(Key key) {
        ResourceWeakReference reference = activeResources.get(key);
        if (reference != null) {
            return reference.get();
        }
        return null;
    }

    private ReferenceQueue<Resource> getReferenceQueue() {
        if (null == queue) {
            queue = new ReferenceQueue<>();
            cleanReferenceThread = new Thread() {
                @Override
                public void run() {
                    while (!isShutdown) {
                        try {
                            //被回收掉的引用
                            ResourceWeakReference ref = (ResourceWeakReference) queue.remove();
                            activeResources.remove(ref.key);
                        } catch (InterruptedException e) {
                        }
                    }
                }
            };
            cleanReferenceThread.start();
        }
        return queue;
    }

    void shutdown() {
        isShutdown = true;
        if (cleanReferenceThread != null) {
            cleanReferenceThread.interrupt();
            try {
                //5s  必须结束掉线程
                cleanReferenceThread.join(TimeUnit.SECONDS.toMillis(5));
                if (cleanReferenceThread.isAlive()) {
                    throw new RuntimeException("Failed to join in time");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static final class ResourceWeakReference extends WeakReference<Resource> {
        final Key key;

        public ResourceWeakReference(Key key, Resource referent, ReferenceQueue<? super Resource> q) {
            super(referent, q);
            this.key = key;
        }
    }
}
