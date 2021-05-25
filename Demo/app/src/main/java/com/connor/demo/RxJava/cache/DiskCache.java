package com.connor.demo.RxJava.cache;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * Create by dzb 2021/05/25
 */
public class DiskCache implements ICache {
    private static final String NAME = ".db";
    File mFileDir;

    public DiskCache() {
        mFileDir = CacheLoader.getApplication().getCacheDir();
    }

    @Override
    public <T> Observable<T> get(final String key, Class<T> cls) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<T> e) throws Exception {
                T t = (T) getDiskData(key + NAME);
                if (t == null) {
                    e.onComplete();
                } else {
                    e.onNext(t);
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public <T> void put(final String key, final T t) {
        Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<T> e) throws Exception {
                boolean isSuccess = isSave(key + NAME, t);
                if (isSuccess) {
                    e.onNext(t);
                    e.onComplete();
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    private <T> boolean isSave(String fileName, T t) {
        File file = new File(mFileDir, fileName);
        ObjectOutputStream write = null;
        boolean isSuccess = false;
        try {
            FileOutputStream out = new FileOutputStream(file);
            write = new ObjectOutputStream(out);
            write.writeObject(t);
            isSuccess = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    private Object getDiskData(String fileName) {
        File file = new File(mFileDir, fileName);
        if (!file.exists()) {
            return null;
        }
        Object o = null;
        ObjectInputStream read = null;
        try {
            read = new ObjectInputStream(new FileInputStream(file));
            o = read.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeSilently(read);
        }
        return o;
    }

    private void closeSilently(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void clearDiskMemory(String key) {
        File file = new File(mFileDir, key + NAME);
        if (file.exists()) {
            file.delete();
        }
    }
}
