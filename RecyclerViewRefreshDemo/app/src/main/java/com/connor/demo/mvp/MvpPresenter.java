package com.connor.demo.mvp;

public class MvpPresenter extends BasePresenter<MvpContract.View> implements MvpContract.Presenter {

    public MvpPresenter(MvpContract.View view) {
        super(view);
    }

    @Override
    public void getText() {
        MvpContract.View view = getMvpView();
        if (view != null) {
            view.onLoadText("返回文案成功");
        }
    }

}
