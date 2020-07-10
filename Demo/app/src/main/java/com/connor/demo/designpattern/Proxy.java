package com.connor.demo.designpattern;

/**
 * 代理模式
 */
public class Proxy {

    public static void main(String[] args) {
        ProxyClass proxyClass = new ProxyClass();
        proxyClass.request();

        // 具体用例
        Image image = new ProxyImage("test_10mb.jpg");
        // 图像将从磁盘加载
        image.display();
        // 图像不需要从磁盘加载
        image.display();
    }

}

abstract class Subject {
    abstract void request();
}

class RealSubject extends Subject {
    @Override
    void request() {
        System.out.println("真实的请求");
    }
}

class ProxyClass extends Subject {
    RealSubject realSubject;

    @Override
    void request() {
        if (realSubject == null) {
            realSubject = new RealSubject();
        }
        realSubject.request();
    }
}

// 具体用例
interface Image {
    void display();
}

class RealImage implements Image {
    private String mFileName;

    public RealImage(String fileName) {
        this.mFileName = fileName;
        loadFromDisk();
    }

    private void loadFromDisk() {
        System.out.println("load image from  disk");
    }

    @Override
    public void display() {
        System.out.println("display image");
    }
}

class ProxyImage implements Image {
    private RealImage mRealImage;
    private String mFileName;

    public ProxyImage(String mFileName) {
        this.mFileName = mFileName;
    }

    @Override
    public void display() {
        if (mRealImage == null) {
            mRealImage = new RealImage(mFileName);
        }
        mRealImage.display();
    }
}
