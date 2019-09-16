package com.connor.demo.mvp;

public interface MvpContract {

    interface View {
        /**
         * 返回文案
         *
         * @param text text
         */
        void onLoadText(String text);
    }

    interface Presenter {
        /**
         * 模拟获取文案
         */
        void getText();
    }


}
