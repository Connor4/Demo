package com.connor.demo.designpattern;

public class Proxy {

    abstract class Subject {
        abstract void request();
    }

    class RealSubject extends Subject {
        @Override
        void request() {
            System.out.println("真实的请求");
        }
    }

    class ProxyClass extends Subject {
        RealSubject realSubject;

        @Override
        void request() {
            if (realSubject == null) {
                realSubject = new RealSubject();
            }
            realSubject.request();
        }
    }

    public void test() {
        ProxyClass proxyClass = new ProxyClass();
        proxyClass.request();
    }

}
