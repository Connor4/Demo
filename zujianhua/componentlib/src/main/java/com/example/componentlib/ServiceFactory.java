package com.example.componentlib;

public class ServiceFactory {

    private ServiceFactory() {

    }

    private static final ServiceFactory Instance = new ServiceFactory();

    public static ServiceFactory getInstance() {
        return Instance;
    }

    private ILoginInterface mLogin;
    private IMineInterface mMine;

    public ILoginInterface getLogin() {
        return mLogin;
    }

    public void setLogin(ILoginInterface login) {
        mLogin = login;
    }

    public IMineInterface getMine() {
        return mMine;
    }

    public void setMine(IMineInterface mine) {
        mMine = mine;
    }
}
