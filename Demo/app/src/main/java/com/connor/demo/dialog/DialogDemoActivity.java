package com.connor.demo.dialog;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.connor.demo.R;

/**
 * Create by dzb 2020/07/05
 */
class DialogDemoActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 随便给的
        setContentView(R.layout.activity_main);
        useMessageDialog();
        useCheckBoxDialog();
    }

    public void useMessageDialog() {
        new CustomDialog.MessageDialogBuilder(this)
                .setTitle("MessageDialogBuilder")
                .create()
                .show();
    }

    public void useCheckBoxDialog() {
        new CustomDialog.CheckboxDialogBuilder(this)
                .setTitle("CheckBoxDialog")
                .create(R.style.DialogStyle)
                .show();
    }

}
