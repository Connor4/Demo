package com.connor.demo.designpattern;

/**
 * 装饰模式
 * 定义的抽象Component作为具体对象ConcreteComponent和装饰内容基类AbsDecorator；
 * 在抽象装饰基类AbsDecorator中定义setComponent()方法，用于传递需要装饰的对象Component进行，在Operation()中调用Component的Operation()。
 * 装饰过程如main方法所示，最后调用对象去执行启动装饰。
 * 将核心功能跟装饰功能区分开。
 * <p>
 * 适用场景：
 * 为已有功能添加更多功能的一种方式，类似给核心功能添加一些附加功能。
 * 当没有Component的时候，可以直接将ConcreteComponent和AbsDecorator用作同一个。
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