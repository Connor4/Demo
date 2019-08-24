package com.example.butterknife;

import android.app.Activity;

public class ButterKnife {
    public static void bind(Activity activity) {
        String activityName = activity.getClass().getName() + "_ViewBinding";
        try {
            Class<?> clazz = Class.forName(activityName);
            IBinder iBinder = (IBinder) clazz.newInstance();
            iBinder.bind(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
