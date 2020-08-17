package com.connor.demo.designpattern;

/**
 * 备忘录模式
 * 在不破坏封装性的前提下，捕获保存一个对象的状态。用于将对象恢复。
 * 使用这个模式主要是"封装性"
 *
 * 使用场景：
 * 使用功能比较复杂的，但需要维护或记录属性历史的类；或者需要保存的属性只是众多属性的一小部分。
 * 例如撤销命令
 */
class MementoDemo {
    public static void main(String[] args) {
        Originator o = new Originator();
        o.setState("on");
        o.show();

        CareTaker ct = new CareTaker();
        ct.setMemento(o.createMemento());

        o.setState("off");
        o.show();

        o.setMemento(ct.getMemento());
        o.show();
    }
}

/**
 * 使用者
 */
class Originator{
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Memento createMemento() {
        return new Memento(state);
    }

    public void setMemento(Memento memento) {
        state = memento.getState();
    }

    public void show() {
        System.out.println("State = " + state);
    }

}

/**
 *备忘录类
 */
class Memento{
    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}

/**
 * 管理者
 */
class CareTaker{
    private Memento memento;

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}
