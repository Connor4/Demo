package com.connor.demo.fragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;

import com.connor.demo.R;

public class FragmentShowHideDemoActivity extends Activity implements RadioGroup.OnCheckedChangeListener{
    private Fragment1 mFragment1;
    private Fragment2 mFragment2;
    private Fragment3 mFragment3;
    private Fragment4 mFragment4;
    private FragmentManager mFragmentManager;
    private RadioGroup mRadioGroup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        mFragmentManager = getFragmentManager();
        mRadioGroup = findViewById(R.id.fragment_rg);
        mRadioGroup.setOnCheckedChangeListener(this);
        showFragment(1);
    }

    private void showFragment(int page) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();

        // 想要显示一个fragment,先隐藏所有fragment，防止重叠
        hideFragments(ft);
        switch (page) {
            case 1:
                // 如果fragment1已经存在则将其显示出来
                if (mFragment1 != null)
                    ft.show(mFragment1);
                    // 否则添加fragment1，注意添加后是会显示出来的，replace方法也是先remove后add
                else {
                    mFragment1 = new Fragment1();
                    ft.add(R.id.fl_content_main, mFragment1);
                }
                break;
            case 2:
                if (mFragment2 != null)
                    ft.show(mFragment2);
                else {
                    mFragment2 = new Fragment2();
                    ft.add(R.id.fl_content_main, mFragment2);
                }
                break;
            case 3:
                if (mFragment3 != null) {
                    ft.show(mFragment3);
                } else {
                    mFragment3 = new Fragment3();
                    ft.add(R.id.fl_content_main, mFragment3);
                }
                break;
            case 4:
                if (mFragment4 != null) {
                    ft.show(mFragment4);
                } else {
                    mFragment4 = new Fragment4();
                    ft.add(R.id.fl_content_main, mFragment4);
                }
                break;
        }
        ft.commit();
    }

    // 当fragment已被实例化，相当于发生过切换，就隐藏起来
    public void hideFragments(FragmentTransaction ft) {
        if (mFragment1 != null)
            ft.hide(mFragment1);
        if (mFragment2 != null)
            ft.hide(mFragment2);
        if (mFragment3 != null)
            ft.hide(mFragment3);
        if (mFragment4 != null)
            ft.hide(mFragment4);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_1:
                showFragment(1);
                break;
            case R.id.rb_2:
                showFragment(2);
                break;
            case R.id.rb_3:
                showFragment(3);
                break;
            case R.id.rb_4:
                showFragment(4);
                break;
        }
    }

}
