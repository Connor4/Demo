package com.connor.recyclerviewrefreshdemo.refresh;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Connor on  2019-06-21
 */
public abstract class RefreshScrollListener extends RecyclerView.OnScrollListener {
    private boolean mSlidingUp = false;

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            assert layoutManager != null;
            int lastItemPosition = layoutManager.findLastCompletelyVisibleItemPosition();
            int itemCount = layoutManager.getItemCount();

            if (lastItemPosition == (itemCount - 1) && mSlidingUp) {
                // 加载更多
                onLoadMore();
            }
        }
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        mSlidingUp = dy > 0;
    }

    public abstract void onLoadMore();

}
