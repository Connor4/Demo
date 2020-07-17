package com.connor.demo.dialog;

import android.app.Dialog;
import android.content.Context;

/**
 * 自定义Dialog的基类，用于对一致的操作，例如换肤管理
 * Created by dzb 2020/7/3
 */
public class BaseDialog extends Dialog {
    public BaseDialog(Context context) {
        super(context);
    }

    public BaseDialog(Context context, int theme) {
        super(context, theme);
    }

    protected BaseDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
}
