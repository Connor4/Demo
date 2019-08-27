package com.example.arouter2;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;

public class ARouter {
    private Context mContext;
    // 装载所有activity对象的容器
    private Map<String, Class<? extends Activity>> activityMap;

    private ARouter() {
        activityMap = new HashMap<>();
    }

    private static final ARouter arouter = new ARouter();

    public static ARouter getInstance() {
        return arouter;
    }

    public void init(Application application) {
        this.mContext = application.getApplicationContext();
    }

    public void putActivity(String path, Class<? extends Activity> clazz) {
        if (path != null && clazz != null) {
            activityMap.put(path, clazz);
        }
    }

    public void jumpActivity(String path, Bundle bundle) {
        Class<? extends Activity> aClass = activityMap.get(path);
        if (aClass == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(mContext, aClass);
        if (bundle != null) {
            intent.putExtra("bundle", bundle);
        }
        mContext.startActivity(intent);
    }

}
