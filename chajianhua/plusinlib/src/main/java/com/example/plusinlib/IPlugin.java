package com.example.plusinlib;

import android.app.Activity;
import android.os.Bundle;

// 约束
public interface IPlugin {
    int FROM_INTERNAL = 0;
    int FROM_EXTERNAL = 1;

    void attach(Activity proxyActivity);

    void onCreate(Bundle saveInstanceState);

    void onResume();
}
