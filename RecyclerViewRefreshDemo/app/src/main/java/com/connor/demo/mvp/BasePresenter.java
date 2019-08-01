package com.connor.demo.mvp;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<T> {

    private WeakReference<T> mViewWeakRef;

    public BasePresenter(T View) {
        attachView(View);
    }

    /**
     * 将presenter和view绑定
     *
     * @param View view
     */
    public void attachView(T View) {
        mViewWeakRef = new WeakReference<>(View);
    }

    /**
     * 主动释放view引用
     */
    public void detachView() {
        if (isViewAttached()) {
            mViewWeakRef.clear();
        }
    }

    /**
     * 判断view是否还存活
     *
     * @return 是否存活
     */
    protected boolean isViewAttached() {
        return mViewWeakRef != null && mViewWeakRef.get() != null;
    }

    /**
     * 获取View
     *
     * @return view
     */
    protected T getMvpView() {
        return isViewAttached() ? mViewWeakRef.get() : null;
    }

}
