package com.example.zujianhua;

import android.app.Application;

import com.example.componentlib.AppConfig;
import com.example.componentlib.IAppInterface;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        // 将主app的上下文绑定到组件
        for (String name : AppConfig.COMPONENTS) {
            try {
                Class<?> clazz = Class.forName(name);
                Object object = clazz.newInstance();
                if (object instanceof IAppInterface) {
                    ((IAppInterface) object).initialize(this);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
