package com.connor.demo.designpattern;

/**
 * 外观模式
 * 为子系统的一组接口提供一个一致的界面，外观模式定义了一个高层接口，使得子系统更加容易使用。
 * <p>
 * <p>
 * 使用场景：
 * 1.复杂模块或者子系统提供外界访问的模块，减少耦合依赖
 */
public class FacadeDemo {
    public static void main(String[] args) {
        Facade f = new Facade();
        f.facadeMethod1();
        f.facedeMethod2();
    }
}

class ClassA {
    public void MethodA() {
        System.out.println("ClassA MethodA");
    }
}

class ClassB {
    public void MethodB() {
        System.out.println("ClassB MethodB");
    }
}

class Facade {
    ClassA mA;
    ClassB mB;

    public Facade() {
        mA = new ClassA();
        mB = new ClassB();
    }

    public void facadeMethod1() {
        mA.MethodA();
    }

    public void facedeMethod2() {
        mB.MethodB();
    }
}