package com.connor.demo.designpattern;

/**
 * 装饰模式
 *
 **/
public class Decorator {

    public static void main(String[] args) {
        ConcreteComponent c = new ConcreteComponent();
        ConcreteDecoratorA d1 = new ConcreteDecoratorA();
        ConcreteDecoratorB d2 = new ConcreteDecoratorB();

        d1.setComponent(c);
        d2.setComponent(d1);
        d2.Operation();
    }

}

// 定义对象接口
abstract class Component {
    public abstract void Operation();
}

// 定义了一个具体对象
class ConcreteComponent extends Component {
    @Override
    public void Operation() {
        System.out.println("具体需要实现的方法");
    }
}

// 装饰抽象类
abstract class AbsDecorator extends Component {
    protected Component component;

    public void setComponent(Component component) {
        this.component = component;
    }

    @Override
    public void Operation() {
        if (component != null) {
            component.Operation();
        }
    }
}

// 具体实现类
class ConcreteDecoratorA extends AbsDecorator {
    private String function;

    @Override
    public void Operation() {
        super.Operation();
        function = "具体类A的职责";
        System.out.println("ConcreteDecoratorA 职责是 function");
    }
}

// 具体实现类
class ConcreteDecoratorB extends AbsDecorator {
    @Override
    public void Operation() {
        super.Operation();
        addBehavior();
        System.out.println("ConcreteDecoratorB 职责是 addBehavior ");
    }

    private void addBehavior() {

    }
}