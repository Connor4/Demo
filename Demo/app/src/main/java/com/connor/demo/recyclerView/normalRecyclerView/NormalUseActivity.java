package com.connor.demo.recyclerView.normalRecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.connor.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Connor on  2019-06-21
 */
public class NormalUseActivity extends Activity {
    private RecyclerView mRecyclerView;
    private RvAdapter mRvAdapter;
    private List<String> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_main);
        mRecyclerView = findViewById(R.id.rv);
        for (int i = 0; i < 10; i++) {
            mData.add("" + i);
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRvAdapter = new RvAdapter(this, mData);
        mRecyclerView.setAdapter(mRvAdapter);
        mRvAdapter.setOnItemClickListener(new RvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                mData.remove(position);
//                mRvAdapter.notifyItemRemoved(position);
                Toast.makeText(NormalUseActivity.this, "click " + position, Toast.LENGTH_SHORT).show();

            }
        });

    }
}
