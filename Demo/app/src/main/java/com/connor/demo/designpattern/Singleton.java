package com.connor.demo.designpattern;

/**
 * 单例模式
 */
public class Singleton {

    private Singleton() {
    }

    // 1懒汉式单例
    private static Singleton Instance;

    public static Singleton getInstance() {
        if (Instance == null) {
            Instance = new Singleton();
        }
        return Instance;
    }

    // 2双重同步锁
    private volatile static Singleton Instance2;

    public static Singleton getInstance2() {
        if (Instance2 == null) {
            synchronized (Singleton.class) {
                if (Instance2 == null) {
                    Instance2 = new Singleton();
                }
            }
        }
        return Instance2;
    }

    // 3静态内部类 既实现线程安全，又避免了同步带来的性能影响
    private static Singleton Instance3;

    private static class InstanceHolder {
        private static Singleton Instance3 = new Singleton();
    }

    public static Singleton getInstance3() {
        return InstanceHolder.Instance3;
    }

}

// 4枚举类型单例
class ClassFactory {

    private enum MyEnumSingleton {
        singletonFactory;

        private MySingleton instance;

        private MyEnumSingleton() {//枚举类的构造方法在类加载是被实例化
            instance = new MySingleton();
        }

        public MySingleton getInstance() {
            return instance;
        }
    }

    public static MySingleton getInstance() {
        return MyEnumSingleton.singletonFactory.getInstance();
    }
}

class MySingleton {//需要获实现单例的类，比如数据库连接Connection

    public MySingleton() {
    }
}
