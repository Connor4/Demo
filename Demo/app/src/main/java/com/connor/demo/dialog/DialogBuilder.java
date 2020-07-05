package com.connor.demo.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;

import com.connor.demo.R;

/**
 * 基类Dialog构造器，提供基础的构造方法，并且自定义title、content、operate
 * Created by dzb 2020/7/3
 */
public abstract class DialogBuilder<T extends DialogBuilder> {
    private BaseDialog mDialog;
    private ViewGroup mDialogView;
    private Context mContext;

    protected String mTitle;
    private boolean mCancelable = true;
    private boolean mCanceledOnTouchOutside = true;

    DialogBuilder(Context context) {
        mContext = context;
    }

    public T setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            mTitle = title;
        }
        return (T) this;
    }

    public T setCancelable(boolean cancelable) {
        mCancelable = cancelable;
        return (T) this;
    }

    public T setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
        mCanceledOnTouchOutside = canceledOnTouchOutside;
        return (T) this;
    }

    public BaseDialog create() {
        return create(R.style.DialogStyle);
    }

    public BaseDialog create(@StyleRes int style) {
        mDialog = new BaseDialog(mContext, style);
        mDialogView = onCreateDialogView(mDialog.getContext());
        View title = onCreateTitle();
        View content = onCreateContent();
        View operate = onCreateOperatorView();
        if (title != null) {
            // 进行一些处理
            mDialogView.addView(title);
        }
        if (content != null) {
            mDialogView.addView(content);
        }
        if (operate != null) {
            mDialogView.addView(operate);
        }
        mDialog.addContentView(mDialogView, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return mDialog;
    }

    // dialog的根布局，用于承载。
    @NonNull
    protected ViewGroup onCreateDialogView(Context context) {
        // 演示随便给个
        return new LinearLayout(context);
    }

    // 以下是dialog的三部分，title、content、operate部分
    // 三部分均通过抽像方法让子类具体的dialog builder去构建自己的样式
    @Nullable
    protected abstract View onCreateOperatorView();

    @Nullable
    protected abstract View onCreateContent();

    @Nullable
    protected abstract View onCreateTitle();
}
