package com.connor.recyclerview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.connor.recyclerview.glide.GlideActivity;
import com.connor.recyclerview.normal.NormalUseActivity;
import com.connor.recyclerview.refresh.RefreshActivity;
import com.connor.recyclerviewrefreshdemo.R;

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

    }

}
