package com.connor.demo.designpattern;

/**
 * 策略模式
 * 定义了一系列的算法，分别封装起来，让它们之间可以互相替换。
 * 在实践中，策略模式可以封装任何类型的规则，当需要在不同时间应用不同的业务规则的时候，就可以考虑策略模式。
 * 在基础的策略模式中，策略的选择（具体的算法）需要在客户端（main方法）中实现，这里可以结合策略模式跟简单工厂模式
 * 将策略选择从客户端（main方法）迁移到Context类中
 */
public class StrategyDemo {

    public static void main(String[] args) {
        // 基础策略模式
        Context context;
        context = new Context(new ConcreteStrategyA());
        context.contextInterface();
        context = new Context(new ConcreteStrategyB());
        context.contextInterface();

        // 结合简单工厂模式
        ContextWithFactory context1 = new ContextWithFactory("策略1"); // 这里可以是下拉选择框，选择策略
        context1.AlgorithmInterface();
    }

}

// 抽象算法类
abstract class Strategy {
    public abstract void AlgorithmInterface();
}

// 具体算法
class ConcreteStrategyA extends Strategy {
    @Override
    public void AlgorithmInterface() {
        System.out.println("算法A具体实现");
    }
}

class ConcreteStrategyB extends Strategy {
    @Override
    public void AlgorithmInterface() {
        System.out.println("算法B具体实现");
    }
}

// 策略配置
class Context {
    Strategy mStrategy;

    Context(Strategy strategy) {
        mStrategy = strategy;
    }

    // 管理策略接口
    public void contextInterface() {
        mStrategy.AlgorithmInterface();
    }
}

// 结合简单工厂的策略配置
class ContextWithFactory extends Strategy {
    Strategy mStrategy;

    ContextWithFactory(String type) {
        switch (type) {
            case "策略A":
                mStrategy = new ConcreteStrategyA();
                break;
            case "策略B":
                mStrategy = new ConcreteStrategyB();
                break;
        }
    }

    @Override
    public void AlgorithmInterface() {
        mStrategy.AlgorithmInterface();
    }
}
