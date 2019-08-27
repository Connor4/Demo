package com.example.arouter2;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dalvik.system.DexFile;

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
        this.mContext = application;
        List<String> classNames = getClassName("com.example.util");
        for (String className : classNames) {
            try {
                Class<?> aClass = Class.forName(className);
                // 判断这个类是否IRouter实现类
                if (IRouter.class.isAssignableFrom(aClass)) {
                    IRouter iRouter = (IRouter) aClass.newInstance();
                    iRouter.putActivity();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClass(mContext, aClass);
        if (bundle != null) {
            intent.putExtra("bundle", bundle);
        }
        mContext.startActivity(intent);
    }

    private List<String> getClassName(String packageName) {
        // 创建一个class对象的集合
        List<String> classList = new ArrayList<>();
        String path = null;
        try {
            // 通过包管理器 获取到应用信息类然后获取apk的完整路径
            path = mContext.getPackageManager().getApplicationInfo(mContext.getPackageName(), 0).sourceDir;
            // 根据apk的完整路径获取到编译器的dex文件
            DexFile dexFile = new DexFile(path);
            // 获取编译后的dex文件中的素所有class
            Enumeration entries = dexFile.entries();

            while (entries.hasMoreElements()) {
                // 通过遍历所有的class的包名
                String name = (String) entries.nextElement();
                // 判断类的包名是否符合
                if (name.contains(packageName)) {
                    // 如果符合添加
                    classList.add(name);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classList;
    }

}
