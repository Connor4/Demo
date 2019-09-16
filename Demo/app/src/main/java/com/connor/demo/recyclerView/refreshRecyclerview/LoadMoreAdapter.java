package com.connor.demo.recyclerView.refreshRecyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.connor.demo.R;

import java.util.List;

/**
 * Connor on  2019-06-21
 */
public class LoadMoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> dataList;

    // 普通布局
    private static final int TYPE_ITEM = 1;
    // footer
    private static final int TYPE_FOOTER = 2;
    // 当前加载状态
    private int mLoadState = LOAD_COMPLETE;
    public static final int LOADING = 1;
    public static final int LOAD_COMPLETE = 2;
    public static final int LOADING_END = 3;

    public LoadMoreAdapter(List<String> dataList) {
        this.dataList = dataList;
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        // 根据viewType判断需要创建的view
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.adapter_recyclerview, viewGroup, false);
            return new ItemViewHolder(view);
        } else if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.layout_refresh_footer, viewGroup, false);
            return new FooterViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ItemViewHolder) {
            ItemViewHolder holder = (ItemViewHolder) viewHolder;
            holder.tvItem.setText(dataList.get(position));
        } else if (viewHolder instanceof FooterViewHolder) {
            FooterViewHolder holder = (FooterViewHolder) viewHolder;
            switch (mLoadState) {
                case LOADING: // 正在加载
                    holder.pbLoading.setVisibility(View.VISIBLE);
                    holder.tvLoading.setVisibility(View.VISIBLE);
                    holder.llEnd.setVisibility(View.GONE);
                    break;
                case LOAD_COMPLETE: // 加载完成
                    holder.pbLoading.setVisibility(View.INVISIBLE);
                    holder.tvLoading.setVisibility(View.INVISIBLE);
                    holder.llEnd.setVisibility(View.GONE);
                    break;
                case LOADING_END: // 加载到底
                    holder.pbLoading.setVisibility(View.GONE);
                    holder.tvLoading.setVisibility(View.GONE);
                    holder.llEnd.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size() == 0 ? 0 : dataList.size() + 1;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) layoutManager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    // 如果当前是footer的位置，那么该item占据2个单元格，正常情况下占据1个单元格
                    return getItemViewType(position) == TYPE_FOOTER ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvItem;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(R.id.tv_item);
        }
    }

    private class FooterViewHolder extends RecyclerView.ViewHolder {
        ProgressBar pbLoading;
        TextView tvLoading;
        LinearLayout llEnd;

        public FooterViewHolder(@NonNull View itemView) {
            super(itemView);
            pbLoading = itemView.findViewById(R.id.pb_loading);
            tvLoading = itemView.findViewById(R.id.tv_loading);
            llEnd = itemView.findViewById(R.id.ll_end);
        }
    }

    /**
     * 设置加载状态
     *
     * @param loadState loadState
     */
    public void setLoadState(int loadState) {
        mLoadState = loadState;
        notifyDataSetChanged();
    }
}
