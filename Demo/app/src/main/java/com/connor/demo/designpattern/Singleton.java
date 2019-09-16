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
    public static Singleton getInstance2() {
        if (Instance == null) {
            synchronized (Singleton.class) {
                if (Instance == null) {
                    Instance = new Singleton();
                }
            }
        }
        return Instance;
    }

    // 静态内部类 既实现线程安全，又避免了同步带来的性能影响
    private static class InstanceHolder {
        private static Singleton Instance = new Singleton();
    }

    public static Singleton getInstance3() {
        return InstanceHolder.Instance;
    }

}
