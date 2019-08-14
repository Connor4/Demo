package com.connor.demo.recyclerView.normalRecyclerView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.connor.recyclerviewrefreshdemo.R;

import java.util.List;

/**
 * Connor on  2019-06-21
 */
public class RvAdapter extends RecyclerView.Adapter<RvAdapter.TvViewHolder> {
    private LayoutInflater mLayoutInflater;
    private List<String> mData;

    RvAdapter(Context context, List<String> data) {
        mLayoutInflater = LayoutInflater.from(context);
        mData = data;
    }

    @NonNull
    @Override
    public TvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TvViewHolder(mLayoutInflater.inflate(R.layout.adapter_recyclerview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final TvViewHolder holder, int position) {
        holder.tv.setText(mData.get(position));
        final View itemView = holder.itemView;
        if (mOnItemClickListener != null) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final int position = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class TvViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        TvViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_item);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

}