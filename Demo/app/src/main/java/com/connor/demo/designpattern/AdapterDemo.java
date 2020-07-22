package com.connor.demo.designpattern;

/**
 * 适配器模式
 */
public class AdapterDemo {
    public static void main(String[] args) {
        Target target = new Adapter();
        target.Request();
    }
}

abstract class Target {
    public abstract void Request();
}

class Adaptee {
    public void SpecificRequest() {

    }
}

class Adapter extends Target {
    private Adaptee mAdaptee = new Adaptee();

    @Override public void Request() {
        mAdaptee.SpecificRequest();
    }

}



