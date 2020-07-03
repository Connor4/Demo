package com.connor.demo.dialog;

import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by dzb 2020/7/3
 */
public abstract class DialogBuilder<T extends DialogBuilder> {
    private BaseDialog mDialog;

    public BaseDialog create() {

    }


    @Nullable
    protected abstract View onCreateContet();
}
