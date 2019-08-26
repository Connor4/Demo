package com.example.minecomponent;

import android.app.Application;

import com.example.componentlib.IAppInterface;
import com.example.componentlib.ServiceFactory;

public class MineApplication extends Application implements IAppInterface {
    private static Application application;

    public static Application getApplication() {
        return application;
    }

    @Override
    public void initialize(Application app) {
        application = app;
        ServiceFactory.getInstance().setMine(new MineService());
    }
}
