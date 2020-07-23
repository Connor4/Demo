package com.connor.demo.designpattern;

/**
 * 适配器模式
 *将一个类的接口转换成客户希望的另外一个接口，adapter模式是的本来由于接口不兼容而不能一起工作的类可以一起工作
 *
 * 使用场景：
 *两个类需要做的事相同或者相似，但是具有不同的接口要使用它。
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



