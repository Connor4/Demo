package com.connor.demo.designpattern;

/**
 * 责任链模式
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
