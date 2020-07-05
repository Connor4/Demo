package com.connor.demo.dialog;

import android.content.Context;
import android.view.View;

import androidx.annotation.Nullable;

import com.connor.demo.R;

/**
 * Create by dzb 2020/07/05
 */
class CustomDialog extends BaseDialog {

    public CustomDialog(Context context) {
        this(context, R.style.DialogStyle);
    }

    public CustomDialog(Context context, int theme) {
        super(context, theme);
        init();
    }

    private void init() {
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    public static class MessageDialogBuilder extends DialogBuilder<MessageDialogBuilder> {
        MessageDialogBuilder(Context context) {
            super(context);
        }

        @Nullable
        @Override
        protected View onCreateOperatorView() {
            return null;
        }

        @Nullable
        @Override
        protected View onCreateContent() {
            return null;
        }

        @Nullable
        @Override
        protected View onCreateTitle() {
            return null;
        }
    }

    public static class CheckboxDialogBuilder extends DialogBuilder<CheckboxDialogBuilder> {

        CheckboxDialogBuilder(Context context) {
            super(context);
        }

        @Nullable
        @Override
        protected View onCreateOperatorView() {
            return null;
        }

        @Nullable
        @Override
        protected View onCreateContent() {
            return null;
        }

        @Nullable
        @Override
        protected View onCreateTitle() {
            return null;
        }
    }


}
