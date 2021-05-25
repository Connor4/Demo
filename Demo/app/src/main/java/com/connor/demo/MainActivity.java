package com.connor.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.connor.demo.RxJava.RxJavaActivity;
import com.connor.demo.RxJava.cache.ConcatCacheActivity;
import com.connor.demo.aidl.AidlActivity;
import com.connor.demo.eventbus.MessageEvent;
import com.connor.demo.fragment.FragmentShowHideDemoActivity;
import com.connor.demo.fragment.ViewpagerFragmentActivity;
import com.connor.demo.glide.GlideActivity;
import com.connor.demo.mvp.MvpActivity;
import com.connor.demo.recyclerView.normalRecyclerView.NormalUseActivity;
import com.connor.demo.recyclerView.refreshRecyclerview.RefreshActivity;
import com.connor.demo.valueanimator.AnimatorActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.normal_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NormalUseActivity.class));
            }
        });

        findViewById(R.id.refresh_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RefreshActivity.class));
            }
        });

        findViewById(R.id.glide_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GlideActivity.class));
            }
        });

        findViewById(R.id.mvp_activity_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MvpActivity.class));
            }
        });

        findViewById(R.id.rx_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RxJavaActivity.class));
            }
        });

        findViewById(R.id.rx_cache_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ConcatCacheActivity.class));
            }
        });

        findViewById(R.id.fragment_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FragmentShowHideDemoActivity.class));
            }
        });

        findViewById(R.id.fragment_viewpager_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ViewpagerFragmentActivity.class));
            }
        });

        findViewById(R.id.aidl_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AidlActivity.class));
            }
        });
        findViewById(R.id.animator_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AnimatorActivity.class));
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        /* Do something */
        Log.d("TAG", "receive event from EventBusActivity");
    }

}
