package com.connor.demo.designpattern;

/**
 * 登录状态的状态模式
 * 可能因为登录状态要长期保持，所以context用单例保存
 * <p>
 * create by dzb at 2021/7/31
 */
public class StateDemo2 {
    public static void main(String[] args) {
        // 首先设置登录状态
        boolean isLogin = true;
        Context.getInstance().setLoginState(isLogin);
        Context.getInstance().favorite();
        Context.getInstance().buy();
        isLogin = false;
        Context.getInstance().setLoginState(isLogin);
        Context.getInstance().favorite();
        Context.getInstance().buy();
    }

    interface State {
        void favorite();

        void buy();
    }

    static class LoginState implements State {

        @Override
        public void favorite() {
            System.out.println("收藏成功");
        }

        @Override
        public void buy() {
            System.out.println("购买成功");
        }
    }

    /**
     *
     */
    static class LogoutState implements State {

        @Override
        public void favorite() {
            gotoLoginActivity();
        }

        @Override
        public void buy() {
            gotoLoginActivity();
        }

        private void gotoLoginActivity() {
            System.out.println("跳转登录页");
        }
    }

    /**
     * 需要用单例模式
     */
    static class Context {
        private State mState;

        private Context() {

        }

        private static class Holder {
            public static Context Instance = new Context();
        }

        public static Context getInstance() {
            return Holder.Instance;
        }

        private void setState(State state) {
            mState = state;
        }

        public void favorite() {
            mState.favorite();
        }

        public void buy() {
            mState.buy();
        }

        public void setLoginState(boolean login) {
            if (login) {
                setState(new LoginState());
            } else {
                setState(new LogoutState());
            }
        }
    }
}
