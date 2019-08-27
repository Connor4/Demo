package com.example.arouter;

import android.app.Application;

import com.example.arouter2.ARouter;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.getInstance().init(this);
    }
}
