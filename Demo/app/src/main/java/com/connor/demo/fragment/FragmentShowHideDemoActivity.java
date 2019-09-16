package com.connor.demo.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;

import com.connor.demo.R;

/**
 * 会发生fragment重叠现象
 * 当系统内存不足，Fragment 的宿主 Activity 回收的时候，Fragment 的实例并没有随之被回收。
 * Activity 被系统回收时，会主动调用 onSaveInstance() 方法来保存视图层（View Hierarchy），
 * 所以当 Activity 通过导航再次被重建时，之前被实例化过的 Fragment 依然会出现在 Activity 中，
 * 此时的 FragmentTransaction 中的相当于又再次 add 了 fragment 进去的，hide()和show()方法对之前保存的fragment已经失效了，所以就出现了重叠。
 *
 * 解决方案：
 * 1.
 * protected  void  onSaveInstanceState(Bundle outState) {
 * //如果用以下这种做法则不保存状态，再次进来的话会显示默认tab
 * //总是执行这句代码来调用父类去保存视图层的状态
 * //super.onSaveInstanceState(outState);
 * }
 *
 * 2.
 * 在manifest中声明 android:configChanges="keyboardHidden|orientation|screenSize">
 * 这样就不会走activity的生命周期
 */
public class FragmentShowHideDemoActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener{
    private Fragment1 mFragment1;
    private Fragment2 mFragment2;
    private Fragment3 mFragment3;
    private Fragment4 mFragment4;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        mFragmentManager = getSupportFragmentManager();
        RadioGroup radioGroup = findViewById(R.id.fragment_rg);
        radioGroup.setOnCheckedChangeListener(this);
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
        ft.commitAllowingStateLoss();
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
