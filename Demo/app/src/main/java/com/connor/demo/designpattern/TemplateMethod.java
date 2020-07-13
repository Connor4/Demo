package com.connor.demo.designpattern;

/**
 * 模板方法模式
 * 定义一个操作中的算法框架，而将一些步骤延迟到子类中，模板方法是的子类可以不改变一个算法的结构
 * 即可重新定义该算法的某些特定步骤。
 * <p>
 * 使用场景：
 * 1、有多个子类共有的方法，且逻辑相同。 2、重要的、复杂的方法，可以考虑作为模板方法。
 */
public class TemplateMethod {
    public static void main(String[] args) {
        AbstractClass c;
        c = new ConcreteClassA();
        c.TemplateMethod();

        c = new ConcreteClassB();
        c.TemplateMethod();
    }
}

abstract class AbstractClass {
    public abstract void PrimitiveOperation1();

    public abstract void PrimitiveOperation2();

    public void TemplateMethod() {
        PrimitiveOperation1();
        PrimitiveOperation2();
    }
}

class ConcreteClassA extends AbstractClass {

    @Override
    public void PrimitiveOperation1() {
        System.out.println("a PrimitiveOperation1");
    }

    @Override
    public void PrimitiveOperation2() {
        System.out.println("a PrimitiveOperation2");
    }
}

class ConcreteClassB extends AbstractClass {

    @Override
    public void PrimitiveOperation1() {
        System.out.println("b PrimitiveOperation1");
    }

    @Override
    public void PrimitiveOperation2() {
        System.out.println("b PrimitiveOperation2");
    }
}




