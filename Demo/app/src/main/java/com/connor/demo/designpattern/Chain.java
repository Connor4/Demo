package com.connor.demo.designpattern;

/**
 * 责任链模式
 * 为请求创建了一个接收者对象的链，让链中对象都有机会进行处理请求，从而避免请求的发送者和接收者之间的耦合关系。
 * <p>
 * 使用场景：
 * 1.有多个对象可以处理同一个请求，具体哪个对象处理该请求由运行时自动确定
 * 2.不明确请求需要由哪个接收者处理
 */
public class Chain {

    public static void main(String[] args) {
        Handler handler1 = new ConcreteHandler1("ConcreteHandler1");
        Handler handler2 = new ConcreteHandler2("ConcreteHandler2");
        handler1.setSuccessor(handler2);

        int[] target = {1, 5, 12, 20};
        for (int i : target) {
            handler1.handleWork(i);
        }
    }

}

// 抽象处理基类
abstract class Handler {
    protected String name;
    protected Handler successor;

    public Handler(String name) {
        this.name = name;
    }

    public void setSuccessor(Handler superior) {
        this.successor = superior;
    }

    abstract void handleWork(int request);
}

// 具体实现类1
class ConcreteHandler1 extends Handler {
    public ConcreteHandler1(String name) {
        super(name);
    }

    @Override
    void handleWork(int request) {
        if (request >= 0 && request < 10) {
            System.out.println("ConcreteHandler1 handleWork " + request);
        } else {
            successor.handleWork(request);
        }
    }
}

// 具体实现类2
class ConcreteHandler2 extends Handler {
    public ConcreteHandler2(String name) {
        super(name);
    }

    @Override
    void handleWork(int request) {
        if (request >= 10 && request < 20) {
            System.out.println("ConcreteHandler2 handleWork " + request);
        } else {
            successor.handleWork(request);
        }
    }
}
