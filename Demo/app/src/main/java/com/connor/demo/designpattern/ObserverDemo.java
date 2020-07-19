package com.connor.demo.designpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式
 * 定义对象间一种一对多的关系，当一个对象状态发生改变的时候，所有依赖它的对象将得到通知
 */
public class ObserverDemo {
    public static void main(String[] args) {
        ObserverSubject subject = new ObserverSubject();

        new HexObserver(subject);
        new BinaryObserver(subject);

        System.out.println("First state change: 15");
        subject.setState(15);
        System.out.println("Second state change: 10");
        subject.setState(10);
    }
}

class ObserverSubject {
    private List<Observer> observers = new ArrayList<Observer>();
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}

abstract class Observer {
    protected ObserverSubject mSubject;

    public abstract void update();
}

class BinaryObserver extends Observer {

    public BinaryObserver(ObserverSubject subject) {
        this.mSubject = subject;
        this.mSubject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Binary String: "
                + Integer.toBinaryString(mSubject.getState()));
    }
}

class HexObserver extends Observer {

    public HexObserver(ObserverSubject subject) {
        this.mSubject = subject;
        this.mSubject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Hex String: "
                + Integer.toHexString(mSubject.getState()).toUpperCase());
    }
}
