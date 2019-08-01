package com.connor.demo.mvp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.connor.recyclerviewrefreshdemo.R;

public class MvpActivity extends Activity implements MvpContract.View {
    private MvpPresenter mPresenter;
    private TextView mTv;
    private Button mBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mvp_main);
        mPresenter = new MvpPresenter(this);

        mTv = findViewById(R.id.mvp_activity_tv);
        mBtn = findViewById(R.id.mvp_activity_btn);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.getText();
            }
        });
    }

    @Override
    public void onLoadText(String text) {
        mTv.setText(text);
    }
}
