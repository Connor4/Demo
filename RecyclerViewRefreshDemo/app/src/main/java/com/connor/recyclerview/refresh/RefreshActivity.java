package com.connor.recyclerview.refresh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.connor.recyclerviewrefreshdemo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Connor on  2019-06-21
 */
public class RefreshActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LoadMoreAdapter loadMoreAdapter;
    private List<String> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.refresh_main);

        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.recycler_view);

        // 模拟获取数据
        getData();
        loadMoreAdapter = new LoadMoreAdapter(dataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(loadMoreAdapter);

        // 设置加载更多监听
        recyclerView.addOnScrollListener(new RefreshScrollListener() {
            @Override
            public void onLoadMore() {
                loadMoreAdapter.setLoadState(LoadMoreAdapter.LOADING);

                if (dataList.size() < 52) {
                    // 模拟获取网络数据，延时1s
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    getData();
                                    loadMoreAdapter.setLoadState(LoadMoreAdapter.LOAD_COMPLETE);
                                }
                            });
                        }
                    }, 1000);
                } else {
                    // 显示加载到底的提示
                    loadMoreAdapter.setLoadState(LoadMoreAdapter.LOADING_END);
                }
            }
        });
    }

    private void getData() {
        char letter = 'A';
        for (int i = 0; i < 26; i++) {
            dataList.add(String.valueOf(letter));
            letter++;
        }
    }
}
