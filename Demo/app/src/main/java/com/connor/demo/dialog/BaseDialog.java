package com.connor.demo.dialog;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;

/**
 * Created by dzb 2020/7/3
 */
public class BaseDialog extends AppCompatDialog {
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
